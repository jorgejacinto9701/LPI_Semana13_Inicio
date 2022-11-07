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

import entidad.Campeonato;
import model.CampeonatoModel;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistraCampeonato extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables globales
	JLabel lblTitulo, lblNombre, lblAnno;
	JTextField txtNombre, txtAnno;
	JButton btnRegistrar;

	// Constructor
	public FrmRegistraCampeonato() {
		setTitle("CAMPEONATO");
		setBounds(10, 10, 500, 300);
		getContentPane().setLayout(null);

		lblTitulo = new JLabel("Registro de Campeonato");
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));
		lblTitulo.setBounds(0, 10, 490, 25);
		getContentPane().add(lblTitulo);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(100, 80, 120, 25);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(270, 80, 120, 25);
		getContentPane().add(txtNombre);

		lblAnno = new JLabel("Año");
		lblAnno.setBounds(100, 130, 120, 25);
		getContentPane().add(lblAnno);

		txtAnno = new JTextField();
		txtAnno.setBounds(270, 130, 120, 25);
		getContentPane().add(txtAnno);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmRegistraCampeonato.class.getResource("/iconos/save.gif")));
		btnRegistrar.setBounds(198, 185, 120, 33);
		getContentPane().add(btnRegistrar);

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		FrmRegistraCampeonato frm = new FrmRegistraCampeonato();
		frm.setVisible(true);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
		String nom = txtNombre.getText();
		String annio = txtAnno.getText();
		
		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 1 a 20 caracteres");
		}else if (!annio.matches(Validaciones.ANHO)) {
			mensaje("El año es de 4 dígitos");
		}else {
			Campeonato obj = new Campeonato();
			obj.setNombre(nom);
			obj.setAnnio(Integer.parseInt(annio));
			obj.setEstado(1);
			
			CampeonatoModel model = new CampeonatoModel();
			int salida = model.insertaCampeonato(obj);
			
			if (salida > 0) {
				mensaje("Se insertó correctamente");
			}else {
				mensaje("Error en el Registro");
			}
			
		}
		
	}
}







