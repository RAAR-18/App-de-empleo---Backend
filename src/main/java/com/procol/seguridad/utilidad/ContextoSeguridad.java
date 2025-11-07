package com.procol.seguridad.utilidad;

import java.util.List;

public interface ContextoSeguridad {

    Integer getIdUsuario();

    String getCorreo();

    String getNombreCompleto();

    String getUuidAcceso();

    List<String> getRoles();
}
