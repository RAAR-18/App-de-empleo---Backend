package com.procol.correo.servicio;

import com.procol.correo.Api.CorreoServicio;
import com.procol.correo.dto.CorreoDtoAdjunto;
import com.procol.correo.dto.CorreoDtoPeticion;
import com.procol.correo.dto.CorreoDtoRespuesta;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service("correo_CorreoServicioImple")
public class CorreoServicioImpl implements CorreoServicio {

    private final JavaMailSender cartero;

    @Value("Notificaciones Swallow")
    private String defaultFromName;

    @Value("correo@msgcoreunimag.com")
    private String defaultFrom;

    public CorreoServicioImpl(JavaMailSender cartero) {
        this.cartero = cartero;
    }

    @Override
    public CorreoDtoRespuesta enviarCorreo(CorreoDtoPeticion dto) {
        try {
            MimeMessage correoCompleto = cartero.createMimeMessage();

            boolean esMultipart = dto.arregloAdjunto() != null && !dto.arregloAdjunto().isEmpty();

            MimeMessageHelper asistente = new MimeMessageHelper(correoCompleto, esMultipart, StandardCharsets.UTF_8.name());

            asistente.setFrom(obtenerRemitente(dto.remitente()));
            asistente.setTo(dto.para());
            asistente.setSubject(dto.asunto());
            asistente.setText(dto.cuerpoHtml(), true);
            asistente.setCc(normalizarDirecciones(dto.conCopia()));
            asistente.setBcc(normalizarDirecciones(dto.conCopiaOculta()));
            adjuntarArchivos(asistente, dto.arregloAdjunto(), esMultipart);

            cartero.send(correoCompleto);
            return new CorreoDtoRespuesta(true, "Correo enviado correctamente a: " + dto.para());
        } catch (MessagingException | MailException e) {
            return new CorreoDtoRespuesta(false, "Error al enviar mensaje" + e.getMessage());
        }
    }

    private String obtenerRemitente(String fromDto) {
        if (fromDto != null && !fromDto.isBlank()) {
            return fromDto.trim();
        }
        return String.format("%s <%s>", defaultFromName, defaultFrom);
    }

    private String[] normalizarDirecciones(List<String> direcciones) {
        if (direcciones == null || direcciones.isEmpty()) {
            return new String[0];
        }
        return direcciones.stream()
                .filter(d -> d != null && !d.isBlank())
                .map(String::trim)
                .toArray(String[]::new);
    }

    private void adjuntarArchivos(MimeMessageHelper helper, List<CorreoDtoAdjunto> adjuntos, boolean esMultipart)
            throws MessagingException {
        if (!esMultipart || adjuntos == null) {
            return;
        }
        for (CorreoDtoAdjunto adjunto : adjuntos) {
            File file = new File(adjunto.ruta());
            if (file.exists()) {
                FileSystemResource resource = new FileSystemResource(file);
                helper.addAttachment(adjunto.nombre(), resource, adjunto.tipo());
            }
        }
    }
}
