/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author santi
 */
public class imagenABytes {

    public imagenABytes() {
    }
    
        public byte[] convertirImagenABytes(File file) throws IOException {
        // Leer el archivo de imagen en un InputStream
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();
        return bytes;
    }
    
}
