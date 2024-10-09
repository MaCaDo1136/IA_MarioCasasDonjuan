/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedLocation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedLocation_InFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtMedicineSelect;
    private JComboBox<String> cmbActualLocation;
    private JComboBox<String> cmbNewLocation;
    private JButton btnUpdate;
    private JButton btnCancel;

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

        JLabel lblActualLocation = new JLabel("Actual Location:");
        lblActualLocation.setBounds(67, 250, 150, 50);
        contentPane.add(lblActualLocation);

        cmbActualLocation = new JComboBox<>();
        cmbActualLocation.setBounds(200, 250, 400, 50);
        contentPane.add(cmbActualLocation);

        JLabel lblNewLocation = new JLabel("New Location:");
        lblNewLocation.setBounds(67, 350, 150, 50);
        contentPane.add(lblNewLocation);

        cmbNewLocation = new JComboBox<>();
        cmbNewLocation.setBounds(200, 350, 400, 50);
        contentPane.add(cmbNewLocation);

        //Actions
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(200, 450, 200, 50);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Update location");
            }
        });
        contentPane.add(btnUpdate);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 450, 200, 50);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel action");
            }
        });
        contentPane.add(btnCancel);

        JButton btnBack = new JButton("Regresar");
        btnBack.setBounds(1050, 20, 100, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MedLocation_MainFrame mainFrame = new MedLocation_MainFrame();
                mainFrame.setVisible(true);
            }
        });
        contentPane.add(btnBack);

        setVisible(true);
    }

}
