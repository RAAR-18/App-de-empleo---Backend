package com.procol.seguridad.servicio;

import java.util.List;

import org.springframework.stereotype.Service;
import com.procol.seguridad.dto.RespuestaJwtDTO;

@Service
public class AutenticacionManualServicio {

    private final TokenJwtServicio tokenJwtServicio;
    private final AccesoFotoServicio accesoFotoServicio;

    public AutenticacionManualServicio(
            TokenJwtServicio tokenJwtServicio,
            AccesoFotoServicio accesoFotoServicio
    ) {
        this.tokenJwtServicio = tokenJwtServicio;
        this.accesoFotoServicio = accesoFotoServicio;
    }

    public RespuestaJwtDTO autenticar(
            Integer idUsuario, String correo, String nombres, String apellidos,
            List<String> roles, String uuidAcceso, Integer empresaId
    ) {
        String token = tokenJwtServicio.generarToken(correo, nombres, apellidos, roles, uuidAcceso, idUsuario, empresaId);
        long expiraEn = tokenJwtServicio.obtenerExpiracionDesdeToken(token);
        String fotoApp = accesoFotoServicio.obtenerFoto(idUsuario);
        return new RespuestaJwtDTO(token, fotoApp, expiraEn);
    }
}
