package com.procol.telefono.servicio;

import com.procol.correo.entidad.CorreoVerificacion;
import com.procol.correo.repositorio.CorreoVerificacionRepositorio;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;

import com.procol.telefono.dto.AccesoDtoRespuesta;
import com.procol.telefono.entidad.Acceso;
import org.springframework.stereotype.Service;
import com.procol.telefono.repositorio.AccesoRepositorio;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccesoInfoServicio {

    private final AccesoRepositorio accesoRepositorio;
    private final CorreoVerificacionRepositorio correoVerificacionRepositorio;

    public AccesoInfoServicio(AccesoRepositorio accesoRepositorio, CorreoVerificacionRepositorio correoVerificacionRepositorio) {
        this.accesoRepositorio = accesoRepositorio;
        this.correoVerificacionRepositorio = correoVerificacionRepositorio;
    }

    @Transactional(readOnly = true)
    public String obtenerTelefonoPorUsuario(Integer idUsuario) {
        Acceso acceso = accesoRepositorio.findById(idUsuario)
                .orElseThrow(() -> new ExcepcionNegocio(
                        "No se encontró información de acceso para el usuario con ID: " + idUsuario
                ));

        return acceso.getTelefonoAcceso();
    }

    @Transactional(readOnly = true)
    public AccesoDtoRespuesta obtenerInformacionAcceso(Integer idUsuario) {
        Acceso acceso = accesoRepositorio.findById(idUsuario)
                .orElseThrow(() -> new ExcepcionNegocio(
                        "No se encontró información de acceso para el usuario con ID: " + idUsuario
                ));


        Short estadoVerificacion = 1;

        Optional<CorreoVerificacion> correoVerifOpt =
                correoVerificacionRepositorio.findByIdCorreo(acceso.getCorreoAcceso());

        if (correoVerifOpt.isPresent()) {
            estadoVerificacion = correoVerifOpt.get().getEstadoCorreoVerificado();
        }

        return new AccesoDtoRespuesta(
                acceso.getIdUsuario(),
                acceso.getTelefonoAcceso(),
                acceso.getCorreoAcceso(),
                estadoVerificacion
        );
    }
}
