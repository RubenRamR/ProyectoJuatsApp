/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author santi
 */

public class ImageRenderer extends DefaultTableCellRenderer {
    private JLabel label = new JLabel();
    private BufferedImage image;

    public ImageRenderer(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            // Get the desired row height and column width
            int rowHeight = table.getRowHeight(row);
            int columnWidth = table.getColumnModel().getColumn(column).getWidth();

            // Resize the image to fit the row height and column width
            Image scaledImage = image.getScaledInstance(columnWidth, rowHeight, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));


        return label;
    }
}