/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedRegister;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MedRegister_InFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MedRegister_InFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1330, 856);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
