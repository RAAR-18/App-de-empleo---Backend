package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.AccesoDTO;
import com.procol.perfil.dto.CambiarContrasenaDTO;
import com.procol.perfil.servicio.AccesoCambiarContrasenaServicio;
import com.procol.seguridad.utilidad.ContextoSeguridad;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/acceso")
public class AccesoControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final AccesoCambiarContrasenaServicio cambiarContrasenaServicio;

    public AccesoControlador(
            ContextoSeguridad contextoSeguridad,
            AccesoCambiarContrasenaServicio cambiarContrasenaServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.cambiarContrasenaServicio = cambiarContrasenaServicio;
    }

    @PutMapping("/cambiar-contrasena")
    public ResponseEntity<?> cambiarContrasena(@Valid @RequestBody CambiarContrasenaDTO dto) {
        // Obtener ID del usuario autenticado desde el contexto de seguridad
        Integer idUsuario = contextoSeguridad.getIdUsuario();

        // Ejecutar cambio de contraseña
        AccesoDTO respuesta = cambiarContrasenaServicio.cambiarContrasena(idUsuario, dto);

        return RespuestaHttp.ok("Contraseña actualizada exitosamente", respuesta);
    }
}
