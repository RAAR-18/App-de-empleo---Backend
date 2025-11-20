package com.procol.telefono.servicio;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;

import com.procol.telefono.entidad.Acceso;
import org.springframework.stereotype.Service;
import com.procol.telefono.repositorio.AccesoRepositorio;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccesoInfoServicio {

    private final AccesoRepositorio accesoRepositorio;

    public AccesoInfoServicio(AccesoRepositorio accesoRepositorio) {
        this.accesoRepositorio = accesoRepositorio;
    }

    @Transactional(readOnly = true)
    public String obtenerTelefonoPorUsuario(Integer idUsuario) {
        Acceso acceso = accesoRepositorio.findById(idUsuario)
                .orElseThrow(() -> new ExcepcionNegocio(
                        "No se encontró información de acceso para el usuario con ID: " + idUsuario
                ));

        return acceso.getTelefonoAcceso();
    }
}
