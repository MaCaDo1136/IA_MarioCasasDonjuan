/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedRegister;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedRegister_InFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBarcode;
    private JTextField txtName;
    private JTextField txtLote;
    private JTextField txtInitQuantity;
    private JTextField txtDescription;
    private JButton btnRegister;
    private JButton btnCancel;
    private JDatePickerImpl datePicker;

    public MedRegister_InFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Barcode label and text field
        JLabel lblBarcode = new JLabel("Barcode:");
        lblBarcode.setBounds(67, 50, 100, 50);
        contentPane.add(lblBarcode);

        txtBarcode = new JTextField();
        txtBarcode.setBounds(200, 50, 400, 50);
        contentPane.add(txtBarcode);

        // Name label and text field
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(67, 150, 100, 50);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 150, 400, 50);
        contentPane.add(txtName);

        // Lote label and text field
        JLabel lblLote = new JLabel("Lote:");
        lblLote.setBounds(67, 250, 100, 50);
        contentPane.add(lblLote);

        txtLote = new JTextField();
        txtLote.setBounds(200, 250, 400, 50);
        contentPane.add(txtLote);

        // Expiration Date label and date picker
        JLabel lblExpDate = new JLabel("Expiration Date:");
        lblExpDate.setBounds(67, 350, 150, 50);
        contentPane.add(lblExpDate);

        // Date picker setup
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(200, 350, 400, 50);
        contentPane.add(datePicker);

        // Initial Quantity label and text field
        JLabel lblInitQuantity = new JLabel("Initial Quantity:");
        lblInitQuantity.setBounds(67, 450, 150, 50);
        contentPane.add(lblInitQuantity);

        txtInitQuantity = new JTextField();
        txtInitQuantity.setBounds(200, 450, 400, 50);
        contentPane.add(txtInitQuantity);

        // Description label and text field
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(67, 550, 150, 50);
        contentPane.add(lblDescription);

        txtDescription = new JTextField();
        txtDescription.setBounds(200, 550, 400, 50);
        contentPane.add(txtDescription);

        // Register button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 650, 200, 50);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register new medicine");
            }
        });
        contentPane.add(btnRegister);

        // Cancel button
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 650, 200, 50);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel action");
            }
        });
        contentPane.add(btnCancel);

        setVisible(true);
    }
}
