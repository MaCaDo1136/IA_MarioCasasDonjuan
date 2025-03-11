
package pharmacySystem.gui.MedLocation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pharmacySystem.Utils.Constants.DatabaseConstants;
import pharmacySystem.databaseLib.DatabaseManager;
import pharmacySystem.databaseLib.DatabaseManager.MedLocationData;

import java.sql.SQLException;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedLocation_OutFrame extends JFrame {
private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<MedLocationData> locationDataList;

    private int pageSize = 70;
    private int currentPage = 1;
    private JLabel lblPageInfo;

    private final DatabaseManager db = new DatabaseManager(
            DatabaseConstants.url,
            DatabaseConstants.user,
            DatabaseConstants.password
        );

    public MedLocation_OutFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());

        String[] columnNames = {"Nombre de la Medicina", "lote", "Fecha de Expiracion", "Cantidad", "Ubicación actual"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());

        JPanel paginationPanel = new JPanel();
        JButton btnPrevious = new JButton("Anterior");
        JButton btnNext = new JButton("Siguiente");
        paginationPanel.add(btnPrevious);
        paginationPanel.add(btnNext);

        lblPageInfo = new JLabel();
        paginationPanel.add(lblPageInfo);
        buttonPanel.add(paginationPanel, BorderLayout.CENTER);

        JButton btnBack = new JButton("Regresar");
        buttonPanel.add(btnBack, BorderLayout.EAST);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);

        //ACTION LISTENERS

        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTable();
                    updatePageInfo();
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage < getTotalPages()) {
                    currentPage++;
                    updateTable();
                    updatePageInfo();
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                db.libCloseConnection();
                MedLocation_MainFrame mainFrame = new MedLocation_MainFrame();
                mainFrame.setVisible(true);
            }
        });

        loadData();
    }

    //Private methods

    private void loadData() {
        try {
            locationDataList = db.getMedLocation();
            currentPage = 1;
            updateTable();
            updatePageInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, locationDataList.size());

        for (int i = start; i < end; i++) {
            MedLocationData locations = locationDataList.get(i);
            Object[] rowData = {
                locations.getName(),
                locations.getLote(),
                locations.getExpDate(),
                locations.getQuantity(),
                locations.getActualLocation()
            };
            tableModel.addRow(rowData);
        }
    }

    private void updatePageInfo() {
        lblPageInfo.setText("Página " + currentPage + " de " + getTotalPages());
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) locationDataList.size() / pageSize);
    }
}
