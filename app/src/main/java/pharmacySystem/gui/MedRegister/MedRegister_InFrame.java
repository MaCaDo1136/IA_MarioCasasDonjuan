
package pharmacySystem.gui.MedRegister;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pharmacySystem.Utils.Constants.DatabaseConstants;
import pharmacySystem.databaseLib.DatabaseManager;
import pharmacySystem.gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MedRegister_InFrame extends JFrame {

    
    private JPanel contentPane;
    private JTextField txtBarcode;
    private JTextField txtName;
    private JTextField txtLote;
    private JTextField txtExpDate;
    private JTextField txtInitQuantity;
    private JTextField txtLocation;
    private JTextField txtDescription;
    private JButton btnRegister;
    private JButton btnCancel;

    private String barcode;
    private String name;
    private String lote;
    private String expDate;
    private String quantity;
    private String location;
    private String description;

    private final DatabaseManager db = new DatabaseManager(
            DatabaseConstants.url,
            DatabaseConstants.user,
            DatabaseConstants.password
        );

    public MedRegister_InFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBarcode = new JLabel("Barcode:");
        lblBarcode.setBounds(67, 50, 100, 50);
        contentPane.add(lblBarcode);

        txtBarcode = new JTextField();
        txtBarcode.setBounds(200, 50, 400, 50);
        contentPane.add(txtBarcode);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(67, 150, 100, 50);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 150, 400, 50);
        contentPane.add(txtName);

        JLabel lblLote = new JLabel("Lote:");
        lblLote.setBounds(67, 250, 100, 50);
        contentPane.add(lblLote);

        txtLote = new JTextField();
        txtLote.setBounds(200, 250, 400, 50);
        contentPane.add(txtLote);

        JLabel lblExpDate = new JLabel("Expiration Date:");
        lblExpDate.setBounds(67, 350, 150, 50);
        contentPane.add(lblExpDate);

        txtExpDate = new JTextField(); 
        txtExpDate.setBounds(200, 350, 400, 50);
        contentPane.add(txtExpDate);

        JLabel lblInitQuantity = new JLabel("Quantity:");
        lblInitQuantity.setBounds(67, 450, 150, 50);
        contentPane.add(lblInitQuantity);

        txtInitQuantity = new JTextField();
        txtInitQuantity.setBounds(200, 450, 400, 50);
        contentPane.add(txtInitQuantity);

        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(67, 550, 150, 50);
        contentPane.add(lblLocation);

        txtLocation = new JTextField();
        txtLocation.setBounds(200, 550, 400, 50);
        contentPane.add(txtLocation);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(67, 650, 150, 50);
        contentPane.add(lblDescription);

        txtDescription = new JTextField();
        txtDescription.setBounds(200, 650, 400, 50);
        contentPane.add(txtDescription);

        //Actions
        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 726, 200, 50);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                barcode = txtBarcode.getText();
                name = txtName.getText();
                lote = txtLote.getText();
                expDate = txtExpDate.getText();
                quantity = txtInitQuantity.getText();
                location = txtLocation.getText();
                description = txtDescription.getText();

                try {
                    if (db.checkIfMedicineExists_Medicines(barcode)) {
                        if (barcode.isEmpty()|| lote.isEmpty() || expDate.isEmpty() || quantity.isEmpty() || location.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill all the fields");
                            return;
                        } else {
                            db.registerOrUpdateMedicine(barcode, name, lote, expDate, quantity, location, description);
                            JOptionPane.showMessageDialog(null, "Inventory updated successfully");
                        }
                    } else {
                        if (barcode.isEmpty() || name.isEmpty() || lote.isEmpty() || expDate.isEmpty() || quantity.isEmpty() || location.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill all the fields");
                            return;
                        } else {
                            db.registerOrUpdateMedicine(barcode, name, lote, expDate, quantity, location, description);
                            JOptionPane.showMessageDialog(null, "Medicine registered successfully");
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        contentPane.add(btnRegister);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 726, 200, 50);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        contentPane.add(btnCancel);

        JButton btnBack = new JButton("Regresar");
        btnBack.setBounds(1050, 20, 100, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                db.libCloseConnection();
                MainWindow.init();;
            }
        });
        contentPane.add(btnBack);

        setVisible(true);
    }
}
