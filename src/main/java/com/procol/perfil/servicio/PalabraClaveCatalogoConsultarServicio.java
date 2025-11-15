package com.procol.perfil.servicio;

import com.procol.perfil.dto.PalabraClaveDTO;
import com.procol.perfil.entidad.PalabraClave;
import com.procol.perfil.repositorio.PalabraClaveRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("perfil_PalabraClaveCatalogoConsultarServicio")
public class PalabraClaveCatalogoConsultarServicio {

    private final PalabraClaveRepositorio  palabraClaveRepositorio;

    public PalabraClaveCatalogoConsultarServicio(PalabraClaveRepositorio palabraClaveRepositorio) {
        this.palabraClaveRepositorio = palabraClaveRepositorio;
    }

    @Transactional(readOnly = true)
    public List<PalabraClaveDTO> obtenerCatalogo() {
        List<PalabraClave> palabras = palabraClaveRepositorio.findAll();
        return palabras.stream()
                .map(p -> new PalabraClaveDTO(p.getIdPalabraClave(), p.getTextoPalabraClave()))
                .toList();
    }
}
