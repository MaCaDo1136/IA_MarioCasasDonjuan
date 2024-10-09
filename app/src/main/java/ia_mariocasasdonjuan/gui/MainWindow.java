/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ia_mariocasasdonjuan.gui.MedExpData.MedExpData_OutFrame;
import ia_mariocasasdonjuan.gui.MedInventory.MedInventory_MainFrame;
import ia_mariocasasdonjuan.gui.MedLocation.MedLocation_MainFrame;
import ia_mariocasasdonjuan.gui.MedLogFile.MedLogFile_OutFrame;
import ia_mariocasasdonjuan.gui.MedRegister.MedRegister_InFrame;

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
		btnMedRegister.setBounds(185, 222, 156, 127);
		frame.getContentPane().add(btnMedRegister);
		
		JButton btnMedLocation = new JButton("Ubicacion");
		btnMedLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedLocation_MainFrame location = new MedLocation_MainFrame();
				location.setVisible(true);
				frame.dispose();
			}
		});
		btnMedLocation.setBounds(401, 222, 156, 127);
		frame.getContentPane().add(btnMedLocation);
		
		JButton btnMedInventory = new JButton("Inventario");
		btnMedInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedInventory_MainFrame inventory = new MedInventory_MainFrame();
				inventory.setVisible(true);
				frame.dispose();
			}
		});
		btnMedInventory.setBounds(657, 222, 156, 127);
		frame.getContentPane().add(btnMedInventory);
		
		JButton medExpData = new JButton("Datos de Expiracion");
		medExpData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedExpData_OutFrame expData = new MedExpData_OutFrame();
				expData.setVisible(true);
				frame.dispose();
			}
		});
		medExpData.setBounds(296, 392, 156, 127);
		frame.getContentPane().add(medExpData);
		
		JButton btnMedLogFile = new JButton("Historial de movimientos");
		btnMedLogFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedLogFile_OutFrame  logfile = new MedLogFile_OutFrame();
				logfile.setVisible(true);
				frame.dispose();
			}
		});
		btnMedLogFile.setBounds(530, 392, 156, 127);
		frame.getContentPane().add(btnMedLogFile);
	}
}