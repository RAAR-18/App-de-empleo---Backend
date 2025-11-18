package com.procol.perfil.servicio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.perfil.dto.AccesoDTO;
import com.procol.perfil.dto.CambiarContrasenaDTO;
import com.procol.perfil.entidad.Acceso;
import com.procol.perfil.repositorio.AccesoRepositorio;
import com.procol.perfil.utilidad.mapeador.AccesoMapeador;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service("perfil_AccesoCambiarContrasenaServicio")
public class AccesoCambiarContrasenaServicio {

    private final AccesoRepositorio accesoRepositorio;
    private final AccesoMapeador accesoMapeador;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuditoriaServicio auditoriaServicio;

    public AccesoCambiarContrasenaServicio(
            AccesoRepositorio accesoRepositorio,
            AccesoMapeador accesoMapeador,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AuditoriaServicio auditoriaServicio
    ) {
        this.accesoRepositorio = accesoRepositorio;
        this.accesoMapeador = accesoMapeador;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.auditoriaServicio = auditoriaServicio;
    }

    @Transactional
    public AccesoDTO cambiarContrasena(Integer idUsuario, CambiarContrasenaDTO dto) {
        Acceso acceso = accesoRepositorio.findById(idUsuario)
                .orElseThrow(() -> new ExcepcionNegocio("Usuario sin cuenta de acceso: " + idUsuario));

        if (!bCryptPasswordEncoder.matches(dto.getContrasenaActual(), acceso.getClaveAcceso())) {
            throw new ExcepcionNegocio("La contraseña actual es incorrecta");
        }

        if (bCryptPasswordEncoder.matches(dto.getContrasenaNueva(), acceso.getClaveAcceso())) {
            throw new ExcepcionNegocio("La nueva contraseña debe ser diferente a la actual");
        }

        acceso.setClaveAcceso(bCryptPasswordEncoder.encode(dto.getContrasenaNueva()));
        acceso.setUuidAcceso(UUID.randomUUID().toString());

        Acceso accesoActualizado = accesoRepositorio.save(acceso);

        registrarAuditoria(idUsuario, accesoActualizado);

        return accesoMapeador.desdeEntidad(accesoActualizado);
    }

    private void registrarAuditoria(Integer idEjecutor, Acceso acceso) {
        auditoriaServicio.registrar(
                idEjecutor,
                acceso.getClass().getName(),
                acceso.getIdUsuario(),
                TipoCambio.ACTUALIZAR,
                "Cambio de contraseña - UUID: " + acceso.getUuidAcceso()
        );
    }

}
