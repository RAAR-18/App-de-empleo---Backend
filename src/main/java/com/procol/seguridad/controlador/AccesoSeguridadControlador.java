package com.procol.seguridad.controlador;

import com.procol.seguridad.dto.CredencialesDTO;
import com.procol.seguridad.dto.RespuestaJwtDTO;
import com.procol.seguridad.servicio.TokenJwtServicio;
import com.procol.seguridad.servicio.AccesoFotoServicio;
import com.procol.seguridad.servicio.AutenticacionServicio;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AccesoSeguridadControlador {

    private final TokenJwtServicio tokenJwtServicio;
    private final AuthenticationManager verificarCredenciales;
    private final AutenticacionServicio autenticacionServicio;
    private final AccesoFotoServicio accesoFotoServicio;

    public AccesoSeguridadControlador(
            TokenJwtServicio tokenJwtServicio,
            AuthenticationManager verificarCredenciales,
            AutenticacionServicio autenticacionServicio,
            AccesoFotoServicio accesoFotoServicio
    ) {
        this.tokenJwtServicio = tokenJwtServicio;
        this.verificarCredenciales = verificarCredenciales;
        this.autenticacionServicio = autenticacionServicio;
        this.accesoFotoServicio = accesoFotoServicio;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredencialesDTO credenciales) {
        verificarCredenciales.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credenciales.getCorreoAcceso(),
                        credenciales.getClaveAcceso()
                )
        );

        AutenticacionServicio.DatosAcceso datos = autenticacionServicio
                .obtenerDatosAcceso(credenciales.getCorreoAcceso());

        String token = tokenJwtServicio.generarToken(
                credenciales.getCorreoAcceso(),
                datos.nombres(),
                datos.apellidos(),
                datos.roles(),
                datos.uuid(),
                datos.idUsuario(),
                datos.empresaId()
        );

        long expiraEn = tokenJwtServicio.obtenerExpiracionDesdeToken(token);

        String foto = accesoFotoServicio.obtenerFoto(datos.idUsuario());
        return RespuestaHttp.ok("Autenticación exitosa", new RespuestaJwtDTO(token, foto, expiraEn));
    }
}
