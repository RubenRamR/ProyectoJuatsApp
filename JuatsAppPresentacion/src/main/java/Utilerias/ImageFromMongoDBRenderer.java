/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rramirez
 */
public class ImageFromMongoDBRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Aquí deberías tener la lógica para obtener la imagen desde MongoDB basada en el nombre del usuario
            String nombreUsuario = (String) table.getValueAt(row, 0); // Obtener el nombre del usuario de la columna 0
            
            // Aquí deberías tener la lógica para obtener la imagen desde MongoDB basada en el nombre del usuario
            byte[] imagenBytes = obtenerImagenDesdeMongoDB(nombreUsuario); // Implementa este método para obtener la imagen desde MongoDB

            // Convertir el arreglo de bytes a una imagen
            ImageIcon imagen = new ImageIcon(imagenBytes);

            // Configurar la imagen en el renderizador
            setIcon(imagen);
            setText(""); // Limpiar el texto para que solo se muestre la imagen

            return this;
        }

        // Método para obtener la imagen desde MongoDB basado en el nombre del usuario
        private byte[] obtenerImagenDesdeMongoDB(String nombreUsuario) {
            // Aquí implementa la lógica para consultar MongoDB y obtener la imagen como byte[]
            // Debes manejar la conexión a MongoDB y la consulta adecuada para obtener los datos correctos
            // Retorna el arreglo de bytes de la imagen
            return null; // Implementa esta parte según tu estructura de datos y conexión a MongoDB
        }
    }