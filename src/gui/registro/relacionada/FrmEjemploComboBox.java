package gui.registro.relacionada;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import util.JComboBoxBD;


public class FrmEjemploComboBox extends JFrame	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables globales
	JLabel lblGrado, lblAuspiciador;
	private JComboBoxBD cboGrado;
	private JComboBoxBD cboAuspiciador;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	
	//Constructor
	public FrmEjemploComboBox(){
		setBounds(10,10,556,350);
		getContentPane().setLayout(null);
		
		lblGrado = new JLabel("Grado");
		lblGrado.setBounds(30, 80, 120, 25);
		getContentPane().add(lblGrado);

		lblAuspiciador = new JLabel("Auspiciador");
		lblAuspiciador.setBounds(30, 130, 120, 25);
		getContentPane().add(lblAuspiciador);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"));
		cboGrado.setBounds(185, 81, 296, 22);
		getContentPane().add(cboGrado);
		
		cboAuspiciador = new JComboBoxBD(rb.getString("SQL_AUSPICIADOR"));
		cboAuspiciador.setBounds(185, 131, 296, 22);
		getContentPane().add(cboAuspiciador);

	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		FrmEjemploComboBox frm = new FrmEjemploComboBox();
		frm.setVisible(true);
	}


	
	
	

	
}




