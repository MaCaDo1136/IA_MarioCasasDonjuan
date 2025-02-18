/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedInventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ia_mariocasasdonjuan.Utils.Constants.DatabaseConstants;
import ia_mariocasasdonjuan.databaseLib.DatabaseManager;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedInventory_InFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtInsertBarcode;
    private JTextField txtNewQuantity;
    private JLabel lblActualQuantity;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JButton btnSearch;
    private JComboBox<String> comboBoxLote;

    private String barcode;
    private List<String> lotes = new ArrayList<String>();

    private final DatabaseManager db = new DatabaseManager(
            DatabaseConstants.url,
            DatabaseConstants.user,
            DatabaseConstants.password
        );

    public MedInventory_InFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtInsertBarcode = new JTextField();
        txtInsertBarcode.setBounds(200, 150, 400, 50);
        contentPane.add(txtInsertBarcode);

        txtNewQuantity = new JTextField();
        txtNewQuantity.setBounds(200, 250, 400, 50);
        contentPane.add(txtNewQuantity);

        lblActualQuantity = new JLabel("Cantidad Actual: ");
        lblActualQuantity.setBounds(200, 450, 143, 50);
        contentPane.add(lblActualQuantity);
        
        JLabel lblInsertBarcode = new JLabel("Ingresa codigo de barras:");
        lblInsertBarcode.setBounds(67, 150, 87, 50);
        contentPane.add(lblInsertBarcode);
        
        JLabel lblNewQuantity_Num = new JLabel("");
        lblNewQuantity_Num.setBounds(297, 450, 87, 50);
        contentPane.add(lblNewQuantity_Num);
        
        JLabel lblNewQuantity = new JLabel("Nueva Cantidad:");
        lblNewQuantity.setBounds(67, 250, 87, 50);
        contentPane.add(lblNewQuantity);

        
        JLabel lblSelectLote = new JLabel("Selecciona el lote:");
        lblSelectLote.setBounds(720, 150, 87, 50);

        //Actions
        
        btnUpdate = new JButton("actualizar");
        btnUpdate.setBounds(200, 350, 180, 50);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newQuantity = txtNewQuantity.getText();

                try {
                    if (contentPane.isAncestorOf(comboBoxLote) && contentPane.isAncestorOf(lblSelectLote) && contentPane.isAncestorOf(lblSelectLote) && contentPane.isAncestorOf(lblNewQuantity_Num)) {
                        String selectedLote = (String) comboBoxLote.getSelectedItem();
                        db.updateQuantity(barcode, selectedLote, newQuantity);
                        JOptionPane.showMessageDialog(null, "Se actualizo la cantidad correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                        contentPane.remove(comboBoxLote);
                        contentPane.remove(lblSelectLote);
                        contentPane.remove(lblSelectLote);
                        contentPane.remove(lblNewQuantity_Num);
                    }  
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnUpdate);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(400, 350, 180, 50);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtInsertBarcode.setText("");
                txtNewQuantity.setText("");

                if (contentPane.isAncestorOf(comboBoxLote) && contentPane.isAncestorOf(lblSelectLote) && contentPane.isAncestorOf(lblSelectLote) && contentPane.isAncestorOf(lblNewQuantity_Num)) {
                    contentPane.remove(comboBoxLote);
                
                    contentPane.remove(lblSelectLote);
                    contentPane.remove(lblSelectLote);
                    contentPane.remove(lblNewQuantity_Num);

                    contentPane.revalidate();
                    contentPane.repaint();
                }  
            }
        });
        contentPane.add(btnCancel);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(600, 350, 180, 50);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!lotes.isEmpty()) {
                    lotes.clear();
                }
                if (contentPane.isAncestorOf(comboBoxLote) && contentPane.isAncestorOf(lblSelectLote) && contentPane.isAncestorOf(lblSelectLote) && contentPane.isAncestorOf(lblNewQuantity_Num)) {
                    contentPane.remove(comboBoxLote);
                
                    contentPane.remove(lblSelectLote);
                    contentPane.remove(lblSelectLote);
                    contentPane.remove(lblNewQuantity_Num);

                    contentPane.revalidate();
                    contentPane.repaint();
                }  
                barcode = txtInsertBarcode.getText();

                comboBoxLote = new JComboBox<>();
                comboBoxLote.setBounds(850, 150, 400, 50);
        
            try {
                lotes = db.getLotesByBarcode(barcode);
                for (String lote : lotes) {
                    comboBoxLote.addItem(lote);
                }
                if (lotes.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontro ningun lote con ese codigo de barras", "Error", JOptionPane.ERROR_MESSAGE);
                    contentPane.remove(comboBoxLote);
                
                    contentPane.remove(lblSelectLote);
                    contentPane.remove(lblSelectLote);

                    contentPane.revalidate();
                    contentPane.repaint();
                    return;
                }
                contentPane.add(comboBoxLote);

                contentPane.add(lblSelectLote);
                contentPane.add(lblNewQuantity_Num);

                contentPane.revalidate();
                contentPane.repaint();

                comboBoxLote.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String selectedLote = (String) comboBoxLote.getSelectedItem();
                        try {
                            lblNewQuantity_Num.setText(String.valueOf(db.getQuantityByBarcodeAndLote(barcode, selectedLote)));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            }
        });
        contentPane.add(btnSearch);

        JButton btnBack = new JButton("Regresar");
        btnBack.setBounds(1050, 20, 100, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                db.libCloseConnection();
                MedInventory_MainFrame mainFrame = new MedInventory_MainFrame();
                mainFrame.setVisible(true);
            }
        });
        contentPane.add(btnBack);

        setVisible(true);
    }
}
