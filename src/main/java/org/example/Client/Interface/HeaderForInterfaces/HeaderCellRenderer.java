package org.example.Client.Interface.HeaderForInterfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Clasa ce configureaza header-ul tabelelor din paginile "Doctori" si "Programari pacienti"
 */
public class HeaderCellRenderer extends DefaultTableCellRenderer {
    /**
     * Constructor ce seteaza configurarile pentru header
     */
    public HeaderCellRenderer() {
        setBackground(new Color(77, 210, 255));
        setForeground(Color.white);
        setHorizontalAlignment(SwingConstants.CENTER); // Center align the header text
        setPreferredSize(new Dimension(100, 50)); // Increase the height of the header cell
        setFont(getFont().deriveFont(Font.BOLD, 18f)); // Increase the font size of the header text
    }
}