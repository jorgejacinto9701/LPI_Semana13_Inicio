package gui.registro.simple;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import entidad.Jugador;
import model.JugadorModel;
import util.Conversiones;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistraJugador extends JFrame implements ActionListener 
							{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables globales
	JLabel lblTitulo, lblNombre, lblAnno, lblFecha;
	JTextField  txtNombre, txtApellido, txtFecha;
	JButton btnRegistrar;
	
	//Constructor
	public FrmRegistraJugador(){
		setTitle("Registro de Jugador");
		setBounds(10,10,400,350);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Registro de Jugador");
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));            
		lblTitulo.setBounds(0, 10, 390, 25);
		getContentPane().add(lblTitulo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 80, 120, 25);
		getContentPane().add(lblNombre);		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(200, 80, 120, 25);
		getContentPane().add(txtNombre);

		lblAnno = new JLabel("Apellido");
		lblAnno.setBounds(30, 130, 120, 25);
		getContentPane().add(lblAnno);		
		
		txtApellido = new JTextField();
		txtApellido.setBounds(200, 130, 120, 25);
		getContentPane().add(txtApellido);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(30, 180, 120, 25);
		getContentPane().add(lblFecha);		
		
		txtFecha = new JTextField();
		txtFecha.setBounds(200, 180, 120, 25);
		getContentPane().add(txtFecha);	
	
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmRegistraJugador.class.getResource("/iconos/save.gif")));
		btnRegistrar.setBounds(143,248,120,33);
		getContentPane().add(btnRegistrar);

	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		FrmRegistraJugador frm = new FrmRegistraJugador();
		frm.setVisible(true);
	}


	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
		String nom = txtNombre.getText();
		String ape = txtApellido.getText();
		String fec = txtFecha.getText();
		
		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else {
			Jugador obj = new Jugador();
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setFechaNacimiento(Conversiones.toFecha(fec));
			obj.setEstado(1);
			
			JugadorModel model = new JugadorModel();
			int salida = model.insertaJugador(obj);
			
			if (salida > 0) {
				mensaje("Se insertó correctamente");
			}else {
				mensaje("Error en el Registro");
			}
			
		}
	}
}




