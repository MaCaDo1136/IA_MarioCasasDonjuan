/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedLocation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedLocation_OutFrame extends JFrame {

    private JPanel contentPane;
    private JTextArea txtResults;

    public MedLocation_OutFrame() {
		String results = "Results";
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1330, 856);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());

        txtResults = new JTextArea();
        txtResults.setText(results);
        txtResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResults);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JButton btnBack = new JButton("Regresar");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnBack);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }
}
