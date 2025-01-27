/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedExpData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import ia_mariocasasdonjuan.Utils.Constants.DatabaseConstants;
import ia_mariocasasdonjuan.databaseLib.DatabaseManager;
import ia_mariocasasdonjuan.databaseLib.DatabaseManager.MedExpData;
import ia_mariocasasdonjuan.gui.MainWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MedExpData_OutFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtMedicineName;
    private JButton btnSearch;
    private JButton btnCancel;
    private int pageSize = 10;
    private int currentPage = 1;
    private DefaultTableModel tableModel;
    private List<MedExpData> medicineData;
    private JPopupMenu popupMenu;
    private Timer timer;

    private final DatabaseManager db = new DatabaseManager(
            DatabaseConstants.url,
            DatabaseConstants.user,
            DatabaseConstants.password
        );

    public MedExpData_OutFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMedicineName = new JLabel("Medicine Name:");
        lblMedicineName.setBounds(67, 50, 150, 50);
        contentPane.add(lblMedicineName);

        txtMedicineName = new JTextField();
        txtMedicineName.setBounds(200, 50, 400, 50);
        contentPane.add(txtMedicineName);

        popupMenu = new JPopupMenu();
        contentPane.add(popupMenu);

        // timer
        timer = new Timer(300, (ActionEvent e) -> triggerSuggestionUpdate());
        timer.setRepeats(false);

        txtMedicineName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                timer.restart();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                timer.restart();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                timer.restart();
            }
        });

        btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 150, 200, 50);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String medicineName = txtMedicineName.getText().toLowerCase();
                if (medicineName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a medicine name.");
                } else {
                    showResults(medicineName);
                }
            }
        });
        contentPane.add(btnSearch);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 150, 200, 50);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtMedicineName.setText("");
                revalidate();
                repaint();
            }
        });
        contentPane.add(btnCancel);

        JButton btnBack = new JButton("Regresar");
        btnBack.setBounds(1050, 20, 100, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                db.libCloseConnection();
                MainWindow.init();
            }
        });
        contentPane.add(btnBack);

        setVisible(true);
    }

    private void showResults(String medicineName) {
        medicineData = db.getMedicineDataByName(medicineName);

        if (medicineData.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No data found for this medicine.");
            return;
        }

        JFrame resultsFrame = new JFrame("Search Results");
        resultsFrame.setBounds(100, 100, 800, 600);
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsFrame.setContentPane(resultsPanel);

        String[] columnNames = {"Name", "Lote", "ExpDate", "Quantity", "Location"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel paginationPanel = new JPanel();

        JButton btnPrevious = new JButton("Previous");
        JButton btnNext = new JButton("Next");
        JLabel lblPageInfo = new JLabel();
        paginationPanel.add(btnPrevious);
        paginationPanel.add(btnNext);
        paginationPanel.add(lblPageInfo);

        buttonPanel.add(paginationPanel, BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        buttonPanel.add(btnClose, BorderLayout.EAST);
        resultsPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTable();
                    updatePageInfo(lblPageInfo);
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage < getTotalPages()) {
                    currentPage++;
                    updateTable();
                    updatePageInfo(lblPageInfo);
                }
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultsFrame.dispose();
            }
        });

        updateTable();
        updatePageInfo(lblPageInfo);
        resultsFrame.setVisible(true);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, medicineData.size());
        boolean existence;
        for (int i = start; i < end; i++) {
            MedExpData data = medicineData.get(i);
            existence = data.getQuantity() > 0;
            if (existence) {
            Object[] rowData = {
                data.getName(),
                data.getLote(),
                data.getExpDate(),
                data.getQuantity(),
                data.getLocation()
            };
            tableModel.addRow(rowData);
            }
        }
    }

    private void updatePageInfo(JLabel lblPageInfo) {
        lblPageInfo.setText("Page " + currentPage + " of " + getTotalPages());
    }

    // Método para calcular el total de páginas
    private int getTotalPages() {
        return (int) Math.ceil((double) medicineData.size() / pageSize);
    }

    private void triggerSuggestionUpdate() {
        String searchString = txtMedicineName.getText();
        if (!searchString.isEmpty()) {
            new SwingWorker<List<String>, Void>() {
                protected List<String> doInBackground() {
                    return db.getMedicineNames(searchString);
                }

                protected void done() {
                    try {
                        List<String> suggestions = get();
                        showSuggestions(suggestions);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        } else {
            popupMenu.setVisible(false);
        }
    }

    private void showSuggestions(List<String> suggestions) {
        if (popupMenu.getComponentCount() == suggestions.size()) {
            boolean same = true;
            for (int i = 0; i < suggestions.size(); i++) {
                if (!((JMenuItem) popupMenu.getComponent(i)).getText().equals(suggestions.get(i))) {
                    same = false;
                    break;
                }
            }
            if (same) return;
        }

        popupMenu.removeAll();

        for (String suggestion : suggestions) {
            JMenuItem item = new JMenuItem(suggestion);
            item.addActionListener(e -> txtMedicineName.setText(suggestion));
            popupMenu.add(item);
        }

        if (!suggestions.isEmpty()) {
            popupMenu.show(txtMedicineName, 0, txtMedicineName.getHeight());
        } else {
            popupMenu.setVisible(false);
        }
    }
}
