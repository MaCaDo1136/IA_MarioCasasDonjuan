/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui.MedLogFile;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MedLogFile_OutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MedLogFile_OutFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1330, 856);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
