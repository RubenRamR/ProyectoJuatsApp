/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author santi
 */
public class RowImageRenderer extends DefaultTableCellRenderer {

    public RowImageRenderer() {
    }
    
    @Override
    
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Create a JLabel to display the image
        JLabel label = new JLabel();
        if (value instanceof BufferedImage) {
            BufferedImage img = (BufferedImage) value;
            ImageIcon icon = new ImageIcon(img);
            label.setIcon(icon);
        }
        return label;
    }
}