/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utilerias;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer2 extends DefaultTableCellRenderer {
    private byte[] imageBytes;

    public ImageRenderer2(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream((byte[]) value));
            Image scaledImage = img.getScaledInstance(130, 105, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return label;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }
}
