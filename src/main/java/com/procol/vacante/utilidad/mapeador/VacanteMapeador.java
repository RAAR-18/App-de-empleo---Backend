package com.procol.vacante.utilidad.mapeador;

import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;
import com.procol.vacante.dto.VacanteDTOCrear;
import com.procol.vacante.dto.VacanteDTORespuesta;
import com.procol.vacante.entidad.*;
import com.procol.vacante.entidad.pk.RelUsuarioEmpresaPK;

import org.springframework.stereotype.Component;

@Component
public class VacanteMapeador implements MapeoEntidadDTO<Vacante, VacanteDTORespuesta> {

    @Override
    public VacanteDTORespuesta desdeEntidad(Vacante entidad) {
        if (entidad == null) {
            return null;
        }
        VacanteDTORespuesta dto = new VacanteDTORespuesta();
        dto.setIdVacante(entidad.getIdVacante());
        dto.setTituloVacante(entidad.getTituloVacante());
        return dto;
    }

    @Override
    public Vacante desdeDto(VacanteDTORespuesta dto) {
        if (dto == null) {
            return null;
        }
        Vacante entidad = new Vacante();
        entidad.setIdVacante(dto.getIdVacante());
        entidad.setTituloVacante(dto.getTituloVacante());
        return entidad;
    }

    public Vacante desdeDto(VacanteDTOCrear dto, Object... externos) {
        if (dto == null) {
            return null;
        }
        Vacante entidad = new Vacante();
        entidad.setTituloVacante(dto.getTituloVacante());
        entidad.setDetalleVacante(dto.getDetalleVacante());
        entidad.setFechaInicioVacante(dto.getFechaInicioVacante());
        entidad.setFechaFinVacante(dto.getFechaFinVacante());
        entidad.setEstadoVacante(dto.getEstadoVacante());
        entidad.setMinSalarioVacante(dto.getMinSalarioVacante());
        entidad.setMaxSalarioVacante(dto.getMaxSalarioVacante());

        if (dto.getIdUbicacion() != null) {
            Ubicacion u = new Ubicacion();
            u.setIdUbicacion(dto.getIdUbicacion());
            entidad.setIdUbicacion(u);
        }
        if (dto.getIdJornada() != null) {
            Jornada j = new Jornada();
            j.setIdJornada(dto.getIdJornada());
            entidad.setIdJornada(j);
        }
        if (dto.getIdModalidad() != null) {
            Modalidad m = new Modalidad();
            m.setIdModalidad(dto.getIdModalidad());
            entidad.setIdModalidad(m);
        }
        if (dto.getIdTipoContrato() != null) {
            TipoContrato tc = new TipoContrato();
            tc.setIdTipoContrato(dto.getIdTipoContrato());
            entidad.setIdTipoContrato(tc);
        }

        if (dto.getIdUsuario() != null && dto.getIdEmpresa() != null) {
            Short permisoCreadorVacante = 1;
            RelUsuarioEmpresa rue = new RelUsuarioEmpresa();
            RelUsuarioEmpresaPK pk = new RelUsuarioEmpresaPK(dto.getIdUsuario(), dto.getIdEmpresa());
            rue.setRelUsuarioEmpresaPK(pk);
            rue.setPermisoRelUsuarioEmpresa(permisoCreadorVacante);
            entidad.setRelUsuariosEmpresas(rue);
        }

        return entidad;
    }
}
