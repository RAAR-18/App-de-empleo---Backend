package com.procol.vacante.utilidad.mapeador;

import com.procol.vacante.dto.VacanteDTOConsulta;
import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class VacanteMapeadorNativo implements MapeoNativoDTO<VacanteDTOConsulta> {

    @Override
    public VacanteDTOConsulta mapearDesdeTupla(Tuple tupla) {
        String palabrasClaveRaw = tupla.get("palabras_clave", String.class);

        List<String> palabrasClave = (palabrasClaveRaw == null || palabrasClaveRaw.isBlank())
                ? Collections.emptyList()
                : Arrays.stream(palabrasClaveRaw.split(","))
                        .map(String::trim)
                        .toList();

        // ✅ Mapear como Instant y convertir a OffsetDateTime
        Instant instant = tupla.get("fecha_inicio_vacante", Instant.class);
        OffsetDateTime fechaInicio = instant != null
                ? instant.atZone(ZoneId.systemDefault()).toOffsetDateTime()
                : null;

        return new VacanteDTOConsulta(
                tupla.get("id_vacante", Integer.class),
                tupla.get("titulo_vacante", String.class),
                fechaInicio,
                tupla.get("min_salario_vacante", String.class),
                tupla.get("max_salario_vacante", String.class),
                tupla.get("nombre_ubicacion", String.class),
                tupla.get("nombre_empresa", String.class),
                tupla.get("nombre_jornada", String.class),
                tupla.get("nombre_modalidad", String.class),
                tupla.get("nombre_tipo_contrato", String.class),
                tupla.get("nombre_privado_anuncio", String.class),
                tupla.get("nombre_estado_vacante", String.class),
                palabrasClave
        );
    }
}
