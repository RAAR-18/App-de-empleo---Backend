package com.procol.infraestructura.utilidad.archivo;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

import java.nio.file.Path;
import java.nio.file.Files;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.multipart.MultipartFile;

public class GestorArchivoInstancia {

    private static final Logger LOGGER = Logger.getLogger(GestorArchivoInstancia.class.getName());

    private final Path rutaArchivo;
    private final String nombreArchivo;
    private final String nombreRespaldo;
    private final MultipartFile archivoMultipart;

    public GestorArchivoInstancia(
            Path rutaArchivo,
            String nombreArchivo,
            String nombreRespaldo,
            MultipartFile archivoMultipart
    ) {
        this.rutaArchivo = rutaArchivo;
        this.nombreArchivo = nombreArchivo;
        this.nombreRespaldo = nombreRespaldo;
        this.archivoMultipart = archivoMultipart;
    }

    // *************************************************************************
    // Métodos públicos
    // *************************************************************************
    public boolean guardar() {
        if (archivoMultipart == null || archivoMultipart.isEmpty()) {
            LOGGER.warning("Archivo Multipart vacío o nulo");
            return false;
        }

        try {
            archivoMultipart.transferTo(rutaPrincipal().toFile());
            return true;
        } catch (IOException | IllegalStateException ex) {
            LOGGER.log(Level.SEVERE, "Error al guardar archivo en disco", ex);
            return false;
        }
    }

    public byte[] obtenerContenido() {
        Path ruta = rutaPrincipal();

        if (!archivoExiste(ruta)) {
            ruta = rutaRespaldo();
            if (!archivoExiste(ruta)) {
                LOGGER.warning("No se encontró archivo ni respaldo");
                return null;
            }
        }

        try {
            return Files.readAllBytes(ruta);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error al leer archivo desde ruta: " + ruta, ex);
            return null;
        }
    }

    public String obtenerTipoMime() {
        try {
            return Files.probeContentType(rutaPrincipal());
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "No se pudo determinar el tipo MIME", ex);
            return "application/octet-stream";
        }
    }

    public String imagenBase64() {
        return generarBase64(0);
    }

    public String imagenBase64Miniatura(int anchoDeseado) {
        return generarBase64(anchoDeseado);
    }

    public boolean existe() {
        return archivoExiste(rutaPrincipal());
    }

    public String rutaCompleta() {
        return rutaPrincipal().toString();
    }

    public String nombre() {
        return nombreArchivo;
    }

    public String respaldo() {
        return nombreRespaldo;
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private Path rutaPrincipal() {
        return rutaArchivo.resolve(nombreArchivo);
    }

    private Path rutaRespaldo() {
        return (nombreRespaldo != null && !nombreRespaldo.isEmpty())
                ? rutaArchivo.resolve(nombreRespaldo)
                : null;
    }

    private boolean archivoExiste(Path ruta) {
        return ruta != null && Files.exists(ruta);
    }

    private String generarBase64(int anchoDeseado) {
        BufferedImage imagen = obtenerImagen();
        if (imagen == null) {
            return "";
        }

        if (anchoDeseado > 0) {
            imagen = redimensionarImagen(imagen, anchoDeseado);
            if (imagen == null) {
                return "";
            }
        }
        return convertirImagenABase64(imagen);
    }

    private BufferedImage obtenerImagen() {
        File archivo = rutaPrincipal().toFile();

        if (!archivo.exists()) {
            Path respaldo = rutaRespaldo();
            if (!archivoExiste(respaldo)) {
                return null;
            }
            archivo = respaldo.toFile();
        }

        try {
            return ImageIO.read(archivo);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error al leer imagen", e);
            return null;
        }
    }

    private BufferedImage redimensionarImagen(BufferedImage original, int anchoDeseado) {
        int anchoOriginal = original.getWidth();
        int altoOriginal = original.getHeight();

        double escala = (double) anchoDeseado / anchoOriginal;
        int nuevoAlto = (int) (altoOriginal * escala);

        BufferedImage redimensionada = new BufferedImage(anchoDeseado, nuevoAlto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = redimensionada.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(original, 0, 0, anchoDeseado, nuevoAlto, null);
        g.dispose();

        return redimensionada;
    }

    private String convertirImagenABase64(BufferedImage imagen) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(imagen, "jpg", baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error al convertir imagen a Base64", e);
            return "";
        }
    }
}
