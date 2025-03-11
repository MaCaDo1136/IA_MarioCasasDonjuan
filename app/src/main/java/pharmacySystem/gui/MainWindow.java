
package pharmacySystem.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import pharmacySystem.gui.MedExpData.MedExpData_OutFrame;
import pharmacySystem.gui.MedInventory.MedInventory_MainFrame;
import pharmacySystem.gui.MedLocation.MedLocation_MainFrame;
import pharmacySystem.gui.MedLogFile.MedLogFile_OutFrame;
import pharmacySystem.gui.MedRegister.MedRegister_InFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1330, 856);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMedRegister = new JButton("Registrar Medicamentos");
		btnMedRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedRegister_InFrame register = new MedRegister_InFrame();
				register.setVisible(true);
				frame.dispose();
			}
		});
		btnMedRegister.setBounds(150, 200, 200, 100);
		frame.getContentPane().add(btnMedRegister);
		
		JButton btnMedLocation = new JButton("Ubicacion");
		btnMedLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedLocation_MainFrame location = new MedLocation_MainFrame();
				location.setVisible(true);
				frame.dispose();
			}
		});
		btnMedLocation.setBounds(400, 200, 200, 100);
		frame.getContentPane().add(btnMedLocation);
		
		JButton btnMedInventory = new JButton("Inventario");
		btnMedInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedInventory_MainFrame inventory = new MedInventory_MainFrame();
				inventory.setVisible(true);
				frame.dispose();
			}
		});
		btnMedInventory.setBounds(650, 200, 200, 100);
		frame.getContentPane().add(btnMedInventory);
		
		JButton medExpData = new JButton("Datos de Expiracion");
		medExpData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedExpData_OutFrame expData = new MedExpData_OutFrame();
				expData.setVisible(true);
				frame.dispose();
			}
		});
		medExpData.setBounds(275, 350, 200, 100);
		frame.getContentPane().add(medExpData);
		
		JButton btnMedLogFile = new JButton("Historial de movimientos");
		btnMedLogFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedLogFile_OutFrame  logfile = new MedLogFile_OutFrame();
				logfile.setVisible(true);
				frame.dispose();
			}
		});
		btnMedLogFile.setBounds(525, 350, 200, 100);
		frame.getContentPane().add(btnMedLogFile);
	}
}
