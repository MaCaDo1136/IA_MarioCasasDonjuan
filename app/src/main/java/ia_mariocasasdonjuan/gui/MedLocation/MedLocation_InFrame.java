/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedLocation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ia_mariocasasdonjuan.Utils.Constants.DbConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MedLocation_InFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtMedicineSelect;
    private JComboBox<String> cmbActualLocation;
    private JTextField txtNewLocation;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JButton btnSearch;
    private JPopupMenu popupMenu;
    private Timer timer;

    public MedLocation_InFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMedicineSelect = new JLabel("Select Medicine:");
        lblMedicineSelect.setBounds(67, 150, 150, 50);
        contentPane.add(lblMedicineSelect);

        txtMedicineSelect = new JTextField();
        txtMedicineSelect.setBounds(200, 150, 400, 50);
        contentPane.add(txtMedicineSelect);

        popupMenu = new JPopupMenu();
        contentPane.add(popupMenu);

        // timer
        timer = new Timer(300, (ActionEvent e) -> triggerSuggestionUpdate());
        timer.setRepeats(false);

        txtMedicineSelect.getDocument().addDocumentListener(new DocumentListener() {
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

        JLabel lblActualLocation = new JLabel("Actual Location:");
        lblActualLocation.setBounds(67, 250, 150, 50);
        contentPane.add(lblActualLocation);

        cmbActualLocation = new JComboBox<>();
        cmbActualLocation.setBounds(200, 250, 400, 50);
        contentPane.add(cmbActualLocation);

        JLabel lblNewLocation = new JLabel("New Location:");
        lblNewLocation.setBounds(67, 350, 150, 50);
        contentPane.add(lblNewLocation);

        txtNewLocation = new JTextField();
        txtNewLocation.setBounds(200, 350, 400, 50);
        contentPane.add(txtNewLocation);

        btnUpdate = new JButton("Update"); //FALTA HACER ESTE BOTON
        btnUpdate.setBounds(200, 450, 180, 50);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        contentPane.add(btnUpdate);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 450, 180, 50);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtMedicineSelect.setText("");
                cmbActualLocation.removeAllItems();
                txtNewLocation.setText("");
                repaint();
            }
        });
        contentPane.add(btnCancel);

        btnSearch = new JButton("Search"); //HAY UN ERROR AQUI (SE CIERRA LA CONEXION)
        btnSearch.setBounds(600, 450, 180, 50);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedMedicine = txtMedicineSelect.getText();
                if (selectedMedicine.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a medicine.");
                    return;
                }

                cmbActualLocation.removeAllItems();

                List<String> locationsWithLots = DbConnection.db.getMedicineLocationsWithLots(selectedMedicine);
        
                for (String locationWithLot : locationsWithLots) {
                    cmbActualLocation.addItem(locationWithLot);
                }
            }
        });
        contentPane.add(btnSearch);


        JButton btnBack = new JButton("Regresar");
        btnBack.setBounds(1050, 20, 100, 30);
        btnBack.addActionListener(e -> {
            dispose();
            MedLocation_MainFrame mainFrame = new MedLocation_MainFrame();
            mainFrame.setVisible(true);
        });
        contentPane.add(btnBack);

        setVisible(true);
    }

    private void triggerSuggestionUpdate() {
        String searchString = txtMedicineSelect.getText();
        if (!searchString.isEmpty()) {
            new SwingWorker<List<String>, Void>() {
                protected List<String> doInBackground() {
                    return DbConnection.db.getMedicineNames(searchString);
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
            item.addActionListener(e -> txtMedicineSelect.setText(suggestion));
            popupMenu.add(item);
        }

        if (!suggestions.isEmpty()) {
            popupMenu.show(txtMedicineSelect, 0, txtMedicineSelect.getHeight());
        } else {
            popupMenu.setVisible(false);
        }
    }
}
