package com.procol.correo.servicio;

import com.procol.correo.Api.CorreoServicio;
import com.procol.correo.Api.VerificacionCorreoServicio;
import com.procol.correo.dto.*;
import com.procol.correo.entidad.Acceso;
import com.procol.correo.entidad.CorreoVerificacion;
import com.procol.correo.repositorio.AccesoRepositorio;
import com.procol.correo.repositorio.CorreoVerificacionRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class VerificacionCorreoServicioImpl implements VerificacionCorreoServicio {

    private final CorreoVerificacionRepositorio  correoVerificacionRepositorio;
    private final AccesoRepositorio accesoRepositorio;
    private final CorreoServicio correoServicio;

    public VerificacionCorreoServicioImpl(
            CorreoVerificacionRepositorio correoVerificacionRepositorio,
            AccesoRepositorio accesoRepositorio,
            CorreoServicio correoServicio
    ) {
        this.correoVerificacionRepositorio  = correoVerificacionRepositorio;
        this.accesoRepositorio = accesoRepositorio;
        this.correoServicio = correoServicio;
    }

    @Override
    @Transactional
    public VerificacionCorreo enviarCodigoVerificacion(EnviarCodigoVerficiacion request) {
        Optional<Acceso> accesoOpt = accesoRepositorio.findByCorreoAcceso(request.correoAcceso());

        if (accesoOpt.isEmpty()) {
            return new VerificacionCorreo(
                    false,
                    "No se encontró una cuenta con este correo electrónico",
                    null,
                    null
            );
        }

        Acceso acceso = accesoOpt.get();

        CorreoVerificacion correoVerif = correoVerificacionRepositorio
                .findByIdCorreo(request.correoAcceso())
                .orElseGet(() -> {
                    CorreoVerificacion nuevo = new CorreoVerificacion();
                    nuevo.setIdCorreo(request.correoAcceso());
                    nuevo.setEstadoCorreoVerificado((short) 1);
                    return nuevo;
                });

        if (correoVerif.getEstadoCorreoVerificado() == 3) {
            return new VerificacionCorreo(
                    false,
                    "Este correo ya está verificado",
                    request.correoAcceso(),
                    (short) 3
            );
        }

        // Generar código PIN de 6 dígitos
        String codigoPin = generarCodigoPIN();
        correoVerif.setPinCorreo(codigoPin);
        correoVerif.setEstadoCorreoVerificado((short) 2); // Pendiente
        correoVerificacionRepositorio.save(correoVerif);

        // Construir HTML del correo
        String cuerpoHtml = construirCorreoHtmlConCodigo(
                acceso.getUsuario().getNombresUsuario(),
                codigoPin
        );

        CorreoDtoPeticion correoPeticion = new CorreoDtoPeticion(
                null, // remitente (usa el default configurado)
                request.correoAcceso(),
                null, // conCopia
                null, // conCopiaOculta
                "Código de verificación - Oasis",
                cuerpoHtml,
                null // arregloAdjuntos
        );

        CorreoDtoRespuesta resultado = correoServicio.enviarCorreo(correoPeticion);

        if (resultado.exito()) {
            return new VerificacionCorreo(
                    true,
                    "Se ha enviado un código de verificación a " + request.correoAcceso(),
                    request.correoAcceso(),
                    (short) 2
            );
        } else {
            return new VerificacionCorreo(
                    false,
                    "Error al enviar el correo: " + resultado.mensaje(),
                    null,
                    null
            );
        }
    }

    @Override
    public VerificacionCorreo verificarCodigo(VerificarCodigo request) {
        Optional<CorreoVerificacion> correoVerifOpt = correoVerificacionRepositorio
                .findByIdCorreo(request.correoAcceso());

        if (correoVerifOpt.isEmpty()) {
            return new VerificacionCorreo(
                    false,
                    "No se encontró el correo en el sistema",
                    null,
                    null
            );
        }

        CorreoVerificacion correoVerif = correoVerifOpt.get();

        // Verificar si ya está verificado
        if (correoVerif.getEstadoCorreoVerificado() == 3) {
            return new VerificacionCorreo(
                    true,
                    "Este correo ya fue verificado anteriormente",
                    request.correoAcceso(),
                    (short) 3
            );
        }

        // Verificar si tiene código pendiente
        if (correoVerif.getPinCorreo() == null || correoVerif.getPinCorreo().isBlank()) {
            return new VerificacionCorreo(
                    false,
                    "Debes solicitar un código de verificación primero",
                    null,
                    (short) 1
            );
        }

        // Verificar el código
        if (!correoVerif.getPinCorreo().equals(request.codigo())) {
            return new VerificacionCorreo(
                    false,
                    "El código ingresado es incorrecto",
                    null,
                    (short) 2
            );
        }

        // Código correcto: marcar como verificado
        correoVerif.setEstadoCorreoVerificado((short) 3);
        correoVerif.setPinCorreo(null); // Limpiar el PIN
        correoVerificacionRepositorio.save(correoVerif);

        return new VerificacionCorreo(
                true,
                "¡Correo verificado exitosamente!",
                request.correoAcceso(),
                (short) 3
        );
    }

    @Override
    @Transactional(readOnly = true)
    public VerificacionCorreo obtenerEstadoVerificacion(String correoAcceso) {
        Optional<CorreoVerificacion> correoVerifOpt = correoVerificacionRepositorio
                .findByIdCorreo(correoAcceso);

        if (correoVerifOpt.isEmpty()) {
            // Si no existe registro, el correo no está verificado
            return new VerificacionCorreo(
                    true,
                    "Correo sin verificar",
                    correoAcceso,
                    (short) 1
            );
        }

        CorreoVerificacion correoVerif = correoVerifOpt.get();
        Short estado = correoVerif.getEstadoCorreoVerificado();

        String mensaje = switch (estado) {
            case 1 -> "Correo sin verificar";
            case 2 -> "Verificación pendiente";
            case 3 -> "Correo verificado";
            default -> "Estado desconocido";
        };

        return new VerificacionCorreo(
                true,
                mensaje,
                correoAcceso,
                estado
        );
    }

    private String generarCodigoPIN() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }

    private String construirCorreoHtmlConCodigo(String nombreUsuario, String codigo) {
        return String.format("""
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9fafb;">
                    <div style="background-color: white; border-radius: 10px; padding: 40px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                        <div style="text-align: center; margin-bottom: 30px;">
                            <h1 style="color: #4F46E5; margin: 0; font-size: 32px;">🎯 Oasis App</h1>
                        </div>
                        
                        <h2 style="color: #1f2937; margin-bottom: 20px;">Hola, %s</h2>
                        
                        <p style="color: #4b5563; line-height: 1.6; font-size: 16px; margin-bottom: 15px;">
                            Bienvenido a la comunidad Oasis, volando hacia nuevas oportunidades.
                        </p>
                        
                        <p style="color: #4b5563; line-height: 1.6; font-size: 16px; margin-bottom: 30px;">
                            Para completar tu registro, ingresa el siguiente código de verificación en la aplicación:
                        </p>
                        
                        <div style="background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); 
                                    border-radius: 12px; 
                                    padding: 30px; 
                                    text-align: center; 
                                    margin: 30px 0;
                                    box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);">
                            <p style="color: white; margin: 0 0 10px 0; font-size: 14px; letter-spacing: 1px; text-transform: uppercase;">
                                Tu código de verificación
                            </p>
                            <h1 style="color: white; 
                                       margin: 0; 
                                       font-size: 48px; 
                                       letter-spacing: 8px; 
                                       font-weight: bold;
                                       text-shadow: 2px 2px 4px rgba(0,0,0,0.2);">
                                %s
                            </h1>
                        </div>
                        
                        <div style="background-color: #fef3c7; border-left: 4px solid #f59e0b; padding: 15px; border-radius: 4px; margin: 30px 0;">
                            <p style="color: #92400e; font-size: 14px; margin: 0; line-height: 1.6;">
                                <strong>⚠️ Importante:</strong> Este código expirará pronto. No lo compartas con nadie.
                            </p>
                        </div>
                        
                        <div style="background-color: #f3f4f6; border-left: 4px solid #4F46E5; padding: 15px; border-radius: 4px; margin: 30px 0;">
                            <p style="color: #6b7280; font-size: 14px; margin: 0; line-height: 1.6;">
                                <strong>¿Por qué verificar tu correo?</strong>
                            </p>
                            <ul style="color: #6b7280; font-size: 14px; margin: 10px 0 0 0; padding-left: 20px; line-height: 1.8;">
                                <li>Asegura que las ofertas laborales lleguen directamente a tu bandeja</li>
                                <li>Permite recuperar tu cuenta en caso de olvido de contraseña</li>
                                <li>Protege tu perfil de accesos no autorizados</li>
                                <li>Aumenta la confianza de los empleadores</li>
                            </ul>
                        </div>
                        
                        <div style="margin-top: 40px; padding-top: 30px; border-top: 1px solid #e5e7eb; text-align: center;">
                            <p style="color: #9ca3af; font-size: 12px; margin: 5px 0;">
                                Este correo fue enviado desde Oasis. Si no solicitaste esta verificación, puedes ignorar este mensaje.
                            </p>
                            <p style="color: #9ca3af; font-size: 12px; margin: 5px 0;">
                                Powered by @CIEUnimagdalena-2025
                            </p>
                        </div>
                    </div>
                </div>
                """,
                nombreUsuario,
                codigo
        );
    }
}
