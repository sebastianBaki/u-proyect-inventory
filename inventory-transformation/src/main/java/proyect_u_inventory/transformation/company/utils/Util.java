package proyect_u_inventory.transformation.company.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * util para el cargue y descargue de imagenes
 */
@Slf4j
public class Util {
    public static byte[] compressZlib(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            log.info("Error en compression de la imagen");
        }
        log.info("comprimiendo imgen byte size - {}", outputStream.toByteArray());

        return outputStream.toByteArray();
    }

    public static byte[] decompressZLib(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);

            }
        } catch (DataFormatException ex) {
            log.info("Error en decompressZLib imgen");
        }
        return outputStream.toByteArray();
    }
}
