
package pharmacySystem.gui.MedInventory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pharmacySystem.gui.MainWindow;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedInventory_MainFrame extends JFrame {

	private JPanel contentPane;

	public MedInventory_MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1330, 856);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInput = new JButton("Ingreso de datos");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedInventory_InFrame input = new MedInventory_InFrame();
				input.setVisible(true);
				dispose();
			}
		});
		btnInput.setBounds(326, 245, 211, 167);
		contentPane.add(btnInput);
		
		JButton btnOutput = new JButton("Output");
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedInventory_OutFrame output = new MedInventory_OutFrame();
				output.setVisible(true);
				dispose();
			}
		});
		btnOutput.setBounds(575, 245, 211, 167);
		contentPane.add(btnOutput);

		JButton btnBack = new JButton("Regresar");
        btnBack.setBounds(1050, 20, 100, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainWindow.init();;
            }
        });
        contentPane.add(btnBack);
	}

}
