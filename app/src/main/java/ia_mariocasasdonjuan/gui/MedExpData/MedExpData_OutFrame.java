/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedExpData;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MedExpData_OutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MedExpData_OutFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1330, 856);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
