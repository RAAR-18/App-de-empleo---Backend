package com.procol.vacante.servicio;

import com.procol.vacante.dto.AnuncioDTOMetadato;
import com.procol.vacante.dto.VacanteDTOCrear;
import com.procol.vacante.dto.VacanteDTORespuesta;
import com.procol.vacante.entidad.pk.RelVacantePalabraClavePK;
import com.procol.vacante.utilidad.mapeador.VacanteMapeador;
import com.procol.vacante.entidad.Anuncio;
import com.procol.vacante.entidad.EstadoVacante;
import com.procol.vacante.entidad.HistorialEstadoVacante;
import com.procol.vacante.entidad.PalabraClave;
import com.procol.vacante.entidad.RelVacantePalabraClave;
import com.procol.vacante.entidad.Vacante;
import com.procol.vacante.repositorio.AnuncioRepositorio;
import com.procol.vacante.repositorio.HistorialEstadoVacanteRepositorio;
import com.procol.vacante.repositorio.RelVacantePalabraClaveRepositorio;
import com.procol.vacante.repositorio.VacanteRepositorio;
import com.procol.vacante.repositorio.EstadoVacanteRepositorio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.constante.ConstTipoArchivo;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoInstancia;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoBuilder;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VacanteCrearServicio extends OperacionCrudImple<Vacante, Integer> {

    private final VacanteRepositorio vacanteRepositorio;
    private final RelVacantePalabraClaveRepositorio relVacantePalabraClaveRepositorio;
    private final AnuncioRepositorio anuncioRepositorio;
    private final HistorialEstadoVacanteRepositorio historialEstadoVacanteRepositorio;
    private final EstadoVacanteRepositorio estadoVacanteRepositorio;

    private final AuditoriaServicio auditoriaServicio;
    private final VacanteMapeador vacanteMapeador;

    // Exclusivo para las rutas de las imágenes
    private final GestorArchivoBuilder gestorArchivoBuilder;

    public VacanteCrearServicio(
            VacanteRepositorio vacanteRepositorio,
            RelVacantePalabraClaveRepositorio relVacantePalabraClaveRepositorio,
            AnuncioRepositorio anuncioRepositorio,
            HistorialEstadoVacanteRepositorio historialEstadoVacanteRepositorio,
            EstadoVacanteRepositorio estadoVacanteRepositorio,
            AuditoriaServicio auditoriaServicio,
            VacanteMapeador vacanteMapeador,
            GestorArchivoBuilder gestorArchivoBuilder,
            BusquedaServicio<Vacante, Integer> busquedaServicioVacante
    ) {
        super(busquedaServicioVacante);
        this.vacanteRepositorio = vacanteRepositorio;
        this.relVacantePalabraClaveRepositorio = relVacantePalabraClaveRepositorio;
        this.anuncioRepositorio = anuncioRepositorio;
        this.historialEstadoVacanteRepositorio = historialEstadoVacanteRepositorio;
        this.estadoVacanteRepositorio = estadoVacanteRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.gestorArchivoBuilder = gestorArchivoBuilder;
        this.vacanteMapeador = vacanteMapeador;
    }

    @Override
    protected JpaRepository<Vacante, Integer> getRepositorio() {
        return vacanteRepositorio;
    }

    @Transactional
    public VacanteDTORespuesta crearVacante(
            Integer idEjecutor,
            VacanteDTOCrear dto,
            AnuncioDTOMetadato dtoArchivo
    ) {
        // 1. Crear y guardar la Vacante
        Vacante vacanteNueva = vacanteMapeador.desdeDto(dto);
        vacanteNueva = vacanteRepositorio.save(vacanteNueva);

        // 2. Guardar relaciones con Palabras Clave
        guardarRelacionesPalabrasClave(dto, vacanteNueva);

        // 3. Guardar Anuncio
        guardarAnuncio(dtoArchivo, vacanteNueva);

        // 4. Guardar Historial de Estado
        guardarHistorialEstado(dto, vacanteNueva);

        // 5. Auditoría
        registroAuditoria(idEjecutor, vacanteNueva);

        // 6. Grabar la imagen física
        agregarImagen(dto.getArchivo(), dtoArchivo);

        // 7. Respuesta
        return vacanteMapeador.desdeEntidad(vacanteNueva);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void guardarRelacionesPalabrasClave(VacanteDTOCrear dto, Vacante vacanteNueva) {
        if (dto.getIdsPalabrasClave() == null || dto.getIdsPalabrasClave().isEmpty()) {
            return;
        }

        List<RelVacantePalabraClave> relaciones = dto.getIdsPalabrasClave().stream()
                .map(idPalabra -> {
                    RelVacantePalabraClave rel = new RelVacantePalabraClave();
                    rel.setId(new RelVacantePalabraClavePK(vacanteNueva.getIdVacante(), idPalabra));
                    rel.setVacante(vacanteNueva);
                    rel.setPalabraClave(entityManager.getReference(PalabraClave.class, idPalabra));
                    return rel;
                })
                .toList();

        relVacantePalabraClaveRepositorio.saveAll(relaciones);
    }

    private void guardarAnuncio(AnuncioDTOMetadato dtoArchivo, Vacante vacanteNueva) {
        Anuncio anuncio = new Anuncio();
        anuncio.setVacante(vacanteNueva);
        anuncio.setNombrePublicoAnuncio(dtoArchivo.getNombrePublicoAnuncio());
        anuncio.setNombrePrivadoAnuncio(dtoArchivo.getNombrePrivadoAnuncio());
        anuncio.setTipoAnuncio(dtoArchivo.getTipoAnuncio());
        anuncio.setTamanioAnuncio(dtoArchivo.getTamanioAnuncio());

        anuncioRepositorio.save(anuncio);
    }

    private void guardarHistorialEstado(VacanteDTOCrear dto, Vacante vacanteNueva) {
        EstadoVacante estado = estadoVacanteRepositorio
                .findById((int) dto.getEstadoVacante())
                .orElseThrow(() -> new ExcepcionNegocio("Estado de vacante no encontrado"));

        HistorialEstadoVacante historial = new HistorialEstadoVacante();
        historial.setIdVacante(vacanteNueva);
        historial.setFechaHistorialEstadoVacante(OffsetDateTime.now());

        historial.setIdEstadoVacante(estado);

        historialEstadoVacanteRepositorio.save(historial);
    }

    private void registroAuditoria(Integer idEjecutor, Vacante obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdVacante(),
                TipoCambio.CREAR,
                obj.toString()
        );
    }

    private Boolean agregarImagen(MultipartFile archivo, AnuncioDTOMetadato dtoArchivo) {
        GestorArchivoInstancia instancia = gestorArchivoBuilder.crear(
                ConstTipoArchivo.IMAGEN_VACANTE,
                dtoArchivo.getNombrePrivadoAnuncio(),
                null,
                archivo
        );

        boolean exito = instancia.guardar();
        if (!exito) {
            throw new ExcepcionNegocio("No se puede grabar la imagen de vacante");
        }
        return true;
    }
}
