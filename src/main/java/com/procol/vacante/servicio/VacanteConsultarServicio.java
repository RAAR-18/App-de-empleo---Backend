package com.procol.vacante.servicio;

import com.procol.vacante.entidad.Vacante;
import com.procol.vacante.dto.VacanteDTOConsulta;
import com.procol.vacante.repositorio.VacanteRepositorio;
import com.procol.vacante.utilidad.mapeador.VacanteMapeadorNativo;
import com.procol.vacante.utilidad.querybuilder.SqlBuilderVacante;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Tuple;
import jakarta.persistence.Query;

import java.util.List;

@Service
public class VacanteConsultarServicio extends OperacionCrudImple<Vacante, Integer> {

    @Value("${procol.url.base-imagenes}")
    private String baseUrlImagenes;

    private final VacanteRepositorio vacanteRepositorio;
    private final VacanteMapeadorNativo vacanteMapeadorNativo;

    public VacanteConsultarServicio(
            VacanteRepositorio vacanteRepositorio,
            VacanteMapeadorNativo vacanteMapeadorNativo,
            BusquedaServicio<Vacante, Integer> busquedaServicioVacante) {

        super(busquedaServicioVacante);
        this.vacanteRepositorio = vacanteRepositorio;
        this.vacanteMapeadorNativo = vacanteMapeadorNativo;
    }

    @Override
    protected JpaRepository<Vacante, Integer> getRepositorio() {
        return vacanteRepositorio;
    }

    @Transactional(readOnly = true)
    public List<VacanteDTOConsulta> consultaVacantes(String campoOrden, String orden) {
        String ordenNormalizado = (orden != null && orden.equalsIgnoreCase("ASC")) ? "ASC" : "DESC";
        String campoOrdenNormalizado = (campoOrden != null && !campoOrden.isBlank())
                ? campoOrden
                : "v.fecha_inicio_vacante";

        String sql = SqlBuilderVacante.consulta(campoOrdenNormalizado, ordenNormalizado);

        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        List<Tuple> resultados = consulta.getResultList();

        return resultados.stream()
                .map(tupla -> {
                    VacanteDTOConsulta dto = vacanteMapeadorNativo.mapearDesdeTupla(tupla);
                    String imagenUrl = baseUrlImagenes + "/" + dto.getNombrePrivadoAnuncio();
                    return dto.withImagenUrl(imagenUrl);
                })
                .toList();

    }
}
