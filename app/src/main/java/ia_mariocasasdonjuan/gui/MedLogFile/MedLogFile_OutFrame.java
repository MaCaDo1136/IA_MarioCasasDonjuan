/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedLogFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ia_mariocasasdonjuan.gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedLogFile_OutFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtEstimatedDate;
    private JButton btnSearch;
    private JButton btnCancel;
    private JTextArea txtResults;

    public MedLogFile_OutFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblEstimatedDate = new JLabel("Estimated Date:");
        lblEstimatedDate.setBounds(67, 50, 150, 50);
        contentPane.add(lblEstimatedDate);

        txtEstimatedDate = new JTextField();
        txtEstimatedDate.setBounds(200, 50, 400, 50);
        contentPane.add(txtEstimatedDate);

        //Actions
        btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 150, 200, 50);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search for movements on date: " + txtEstimatedDate.getText());
            }
        });
        contentPane.add(btnSearch);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 150, 200, 50);
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
                MainWindow.init();;
            }
        });
        contentPane.add(btnBack);

        // Results area
        txtResults = new JTextArea();
        txtResults.setBounds(67, 250, 1200, 500);
        txtResults.setEditable(false);
        contentPane.add(txtResults);

        setVisible(true);
    }
}