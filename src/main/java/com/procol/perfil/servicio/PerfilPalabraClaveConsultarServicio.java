package com.procol.perfil.servicio;

import com.procol.perfil.dto.PalabraClaveDTO;
import com.procol.perfil.entidad.PalabraClave;
import com.procol.perfil.repositorio.RelUsuarioPalabraClaveRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("perfil_PerfilPalabraClaveConsultarServicio")
public class PerfilPalabraClaveConsultarServicio {

    private final RelUsuarioPalabraClaveRepositorio  relUsuarioPalabraClaveRepositorio;

    public PerfilPalabraClaveConsultarServicio(RelUsuarioPalabraClaveRepositorio  relUsuarioPalabraClaveRepositorio) {
        this.relUsuarioPalabraClaveRepositorio =  relUsuarioPalabraClaveRepositorio;
    }

    @Transactional(readOnly = true)
    public List<PalabraClaveDTO> obtenerPalabrasClavesPorUsuario(Integer idUsuario) {
        List<PalabraClave> palabras = relUsuarioPalabraClaveRepositorio.findPalabrasClaveByIdUsuario(idUsuario);
        return palabras.stream()
                .map(p -> new PalabraClaveDTO(p.getIdPalabraClave(), p.getTextoPalabraClave()))
                .toList();
    }
}
