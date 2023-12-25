package org.example.Client.Interface.DoctorsInterface;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.HeaderForInterfaces.HeaderCellRenderer;
import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * clasa responsabila cu construirea panel-ului ce contine lista doctorilor
 */
public class DoctorsListPanel extends JPanel {

    private ApplicationClient applicationClient;
    private PrintWriter output;

    private String inputLine;

    private BufferedReader input;

    private MainFrame mainFrame;

    private int numberOfRows = -1;


    private JTable table = new JTable();

    private JTableHeader tableHeader;

    private int status = 0;

    private Object[][] rows;

    private Object[] header = {"ID", "Nume", "Prenume", "Telefon", "Specializare", "Cabinet"};

    /**
     * constructor pentru obiecte de tipul DoctorsListPanel
     *
     * @param mainFrame
     * @param applicationClient
     */
    public DoctorsListPanel(MainFrame mainFrame, ApplicationClient applicationClient) throws IOException {
        this.applicationClient = applicationClient;
        this.mainFrame = mainFrame;
        output = new PrintWriter(this.applicationClient.getSocket().getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(this.applicationClient.getSocket().getInputStream()));

        setLayout(new BorderLayout());
        this.status = 0;
        output.println(mainFrame.getQuery());
        output.println(mainFrame.getSpecializare());
        output.println(mainFrame.getCabinet());

        init();
    }

    public void setRows(Object[][] table) throws IOException {

        rows = table;
        setTable();
        status = 1;
    }

    public void init() {
        this.setBackground(new Color(128, 219, 255));
    }

    /**
     * metoda responsabila de construirea panel-ului
     */
    public void setTable() {

        table = new JTable(rows, header);
        table.setRowHeight(50);

        Font cellFont = table.getFont().deriveFont(Font.BOLD, 16f);
        table.setFont(cellFont);
        table.setBackground(new Color(230, 249, 255));

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new HeaderCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        add(scrollPane, BorderLayout.CENTER);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, 5 * (screenSize.height / 10));
    }
}
