/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedExpData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ia_mariocasasdonjuan.gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedExpData_OutFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtMedicineName;
    private JTextField txtEstimatedDate;
    private JButton btnSearch;
    private JButton btnCancel;

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

        JLabel lblEstimatedDate = new JLabel("Estimated Date:");
        lblEstimatedDate.setBounds(67, 150, 150, 50);
        contentPane.add(lblEstimatedDate);

        txtEstimatedDate = new JTextField();
        txtEstimatedDate.setBounds(200, 150, 400, 50);
        contentPane.add(txtEstimatedDate);

		//Actions

        btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 250, 200, 50);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String medicineName = txtMedicineName.getText();
                String estimatedDate = txtEstimatedDate.getText();
                showResults(medicineName, estimatedDate);
            }
        });
        contentPane.add(btnSearch);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 250, 200, 50);
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
                MainWindow.init();;
            }
        });
        contentPane.add(btnBack);

        setVisible(true);
    }
	

	//Ventana emergente

    private void showResults(String medicineName, String estimatedDate) {
        String results = "Resultados de búsqueda:\n" +
                "Medicamento: " + medicineName + "\n" +
                "Fecha estimada: " + estimatedDate;

        JFrame resultsFrame = new JFrame("Resultados de Búsqueda");
        resultsFrame.setBounds(100, 100, 600, 400);
        JPanel resultsPanel = new JPanel();
        resultsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        resultsFrame.setContentPane(resultsPanel);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        JTextArea resultsTextArea = new JTextArea(results);
        resultsTextArea.setEditable(false);
        resultsPanel.add(resultsTextArea);

        resultsFrame.setVisible(true);
    }
}
