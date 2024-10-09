/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedInventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedInventory_InFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtSelectMed;
    private JTextField txtNewQuantity;
    private JLabel lblActualQuantity;
    private JButton btnUpdate;
    private JButton btnCancel;

    public MedInventory_InFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtSelectMed = new JTextField();
        txtSelectMed.setBounds(200, 150, 400, 50);
        contentPane.add(txtSelectMed);

        txtNewQuantity = new JTextField();
        txtNewQuantity.setBounds(200, 250, 400, 50);
        contentPane.add(txtNewQuantity);

        lblActualQuantity = new JLabel("Actual quantity: ");
        lblActualQuantity.setBounds(200, 450, 87, 50);
        contentPane.add(lblActualQuantity);
        
        JLabel lblSelectMed = new JLabel("Select Med:");
        lblSelectMed.setBounds(67, 150, 87, 50);
        contentPane.add(lblSelectMed);
        
        JLabel lblNewQuantity_Num = new JLabel("");
        lblNewQuantity_Num.setBounds(297, 450, 87, 50);
        contentPane.add(lblNewQuantity_Num);
        
        JLabel lblNewQuantity = new JLabel("New Quantity:");
        lblNewQuantity.setBounds(67, 250, 87, 50);
        contentPane.add(lblNewQuantity);

        //Actions
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(200, 350, 200, 50);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Actualizar cantidad");
            }
        });
        contentPane.add(btnUpdate);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 350, 200, 50);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancelar acción");
            }
        });
        contentPane.add(btnCancel);

        setVisible(true);
    }
}
