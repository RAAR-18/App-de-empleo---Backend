package com.procol.chat.entidad;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Entidad que representa un mensaje en el sistema de chat
 * Asociado a una postulación específica
 */
@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long id;

    @Column(name = "id_postulacion", nullable = false)
    private Long idPostulacion;

    @Column(name = "id_usuario_responde", nullable = false)
    private Long idUsuarioResponde;

    @Column(name = "texto_mensaje", nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(name = "fecha_mensaje", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant fecha;

    // 1 = no leído, 2 = leído
    @Column(name = "estado_mensaje", nullable = false)
    private Short estado;

    public Mensaje() {}

    public Mensaje(Long idPostulacion, Long idUsuarioResponde, String texto, Instant fecha, Short estado) {
        this.idPostulacion = idPostulacion;
        this.idUsuarioResponde = idUsuarioResponde;
        this.texto = texto;
        this.fecha = fecha;
        this.estado = estado;
    }

    // getters y setters (sin Lombok)
    public Long getId() { return id; }
    
    public Long getIdPostulacion() { return idPostulacion; }
    public void setIdPostulacion(Long idPostulacion) { this.idPostulacion = idPostulacion; }
    
    public Long getIdUsuarioResponde() { return idUsuarioResponde; }
    public void setIdUsuarioResponde(Long idUsuarioResponde) { this.idUsuarioResponde = idUsuarioResponde; }
    
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    
    public Instant getFecha() { return fecha; }
    public void setFecha(Instant fecha) { this.fecha = fecha; }
    
    public Short getEstado() { return estado; }
    public void setEstado(Short estado) { this.estado = estado; }
}



