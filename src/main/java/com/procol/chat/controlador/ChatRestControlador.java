package com.procol.chat.controlador;


import com.procol.infraestructura.dto.ApiResponse;
import com.procol.chat.dto.*;
import com.procol.chat.servicio.ChatServicio;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para el módulo de chat
 * Proporciona endpoints HTTP para enviar mensajes, listar chats, y gestionar lectura de mensajes
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ChatRestControlador {

    private final ChatServicio servicio;

    public ChatRestControlador(ChatServicio servicio) {
        this.servicio = servicio;
    }

    // fallback HTTP para enviar (útil en pruebas o si no usas WS en un punto)
    @PostMapping("/messages")
    public ResponseEntity<MensajeResponse> enviar(@Valid @RequestBody EnviarMensajeRequest req) {
        return ResponseEntity.ok(servicio.enviar(req));
    }

    // **Lista de chats** del usuario (candidato o reclutador)
    @GetMapping("/chats")
    public ResponseEntity<ApiResponse<List<ChatResumen>>> listar(@RequestParam("userId") Long userId, 
                                                    @RequestParam(value = "vacanteId", defaultValue = "-1", required = false) Long vacanteId,
                                                    @RequestParam(value = "empresaId", required = false) Long empresaId) {
        var lista = (servicio.listarChats(userId, vacanteId, empresaId));
        return ResponseEntity.ok(ApiResponse.ok(lista, "Listado de chats"));
    }

    // **Lista de chats paginada** con búsqueda
    @GetMapping("/chats/paginado")
    public ResponseEntity<ApiResponse<ChatResumenPagina>> listarPaginado(
            @RequestParam("userId") Long userId,
            @RequestParam(value = "search", defaultValue = "", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        var resultado = servicio.listarChatsPaginado(userId, searchTerm, page, size);
        return ResponseEntity.ok(ApiResponse.ok(resultado, "Listado de chats paginado"));
    }

    // marcar como leídos
    @PostMapping("/chats/{postulacionId}/read")
    public ResponseEntity<ApiResponse<Integer>> marcarLeidos(@PathVariable Long postulacionId,
                                                @RequestParam("userId") Long userId) {
        try {
            if (postulacionId == null || postulacionId <= 0 || userId == null || userId <= 0) {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, "INVALID_ID", "postulacionId y userId deben ser mayores a 0"));
            }
            int actualizados = servicio.marcarLeidos(postulacionId, userId);
            return ResponseEntity.ok(ApiResponse.ok(actualizados, "Mensajes marcados como leídos"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.error(500, "INTERNAL_ERROR", "Error al marcar mensajes como leídos: " + e.getMessage()));
        }
    }

    @GetMapping("/chats/{postId}/messages")
    public ResponseEntity<ApiResponse<List<MensajeResponse>>> historialOffset(
    @PathVariable Long postId,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size
    ){
    var msgs = servicio.paginaOffset(postId, page, size);
    return ResponseEntity.ok(ApiResponse.ok(msgs, "Historial"));
    }

}



