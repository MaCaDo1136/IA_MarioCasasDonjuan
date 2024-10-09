/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedLocation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MedLocation_MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MedLocation_MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1330, 856);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInput = new JButton("Input");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedLocation_InFrame input = new MedLocation_InFrame();
				input.setVisible(true);
				dispose();
			}
		});
		btnInput.setBounds(326, 245, 211, 167);
		contentPane.add(btnInput);
		
		JButton btnOutput = new JButton("Output");
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedLocation_OutFrame output = new MedLocation_OutFrame();
				output.setVisible(true);
				dispose();
			}
		});
		btnOutput.setBounds(575, 245, 211, 167);
		contentPane.add(btnOutput);
	}

}
