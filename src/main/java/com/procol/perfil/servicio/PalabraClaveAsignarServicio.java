package com.procol.perfil.servicio;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.perfil.dto.PalabraClaveDTOAsignar;
import com.procol.perfil.entidad.PalabraClave;
import com.procol.perfil.entidad.RelUsuarioPalabraClave;
import com.procol.perfil.entidad.Usuario;
import com.procol.perfil.entidad.pk.RelUsuarioPalabraClavePK;
import com.procol.perfil.repositorio.RelUsuarioPalabraClaveRepositorio;
import com.procol.perfil.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("perfil_PalabraClaveAsignarServicio")
public class PalabraClaveAsignarServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final RelUsuarioPalabraClaveRepositorio relUsuarioPalabraClaveRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    @PersistenceContext
    private EntityManager entityManager;

    public PalabraClaveAsignarServicio(RelUsuarioPalabraClaveRepositorio relUsuarioPalabraClaveRepositorio, UsuarioRepositorio usuarioRepositorio, BusquedaServicio<Usuario, Integer> busquedaServicioUsuario, EntityManager entityManager) {
        this.relUsuarioPalabraClaveRepositorio = relUsuarioPalabraClaveRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
        this.entityManager = entityManager;
    }

    @Transactional
    public void asignarPalabrasClave(PalabraClaveDTOAsignar palabraClaveDTO) {
        Usuario usuario = busquedaServicioUsuario.PorId(usuarioRepositorio, palabraClaveDTO.getIdUsuario());

        relUsuarioPalabraClaveRepositorio.deleteByUsuarioIdUsuario(palabraClaveDTO.getIdUsuario());
        entityManager.flush();

        if (palabraClaveDTO.getIdsPalabrasClave() != null && !palabraClaveDTO.getIdsPalabrasClave().isEmpty()) {
            List<RelUsuarioPalabraClave> relaciones = palabraClaveDTO.getIdsPalabrasClave().stream()
                    .map(idPalabra -> {
                        RelUsuarioPalabraClave rel = new RelUsuarioPalabraClave();
                        rel.setId(new RelUsuarioPalabraClavePK(usuario.getIdUsuario(), idPalabra));
                        rel.setUsuario(usuario);
                        rel.setPalabraClave(entityManager.getReference(PalabraClave.class, idPalabra));
                        return rel;
                    })
                    .toList();

            relUsuarioPalabraClaveRepositorio.saveAll(relaciones);
        }
    }
}
