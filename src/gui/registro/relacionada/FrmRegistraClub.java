package gui.registro.relacionada;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import entidad.Club;
import entidad.Pais;
import model.ClubModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistraClub extends JFrame implements ActionListener 
							{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables globales
	JLabel lblTitulo, lblNombre, lblFecha;
	JTextField  txtNombre, txtFecha;
	JButton btnRegistrar;
	private JComboBoxBD cboPais;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	
	//Constructor
	public FrmRegistraClub(){
		setTitle("Registro de Jugador");
		setBounds(10,10,544,329);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Registro de Club");
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));            
		lblTitulo.setBounds(0, 10, 528, 25);
		getContentPane().add(lblTitulo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 80, 120, 25);
		getContentPane().add(lblNombre);		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(200, 80, 221, 25);
		getContentPane().add(txtNombre);
		
		lblFecha = new JLabel("Fecha creaci\u00F3n");
		lblFecha.setBounds(30, 116, 120, 25);
		getContentPane().add(lblFecha);		
		
		txtFecha = new JTextField();
		txtFecha.setBounds(200, 116, 120, 25);
		getContentPane().add(txtFecha);	
	
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmRegistraClub.class.getResource("/iconos/save.gif")));
		btnRegistrar.setBounds(210,212,120,33);
		getContentPane().add(btnRegistrar);
		
		JLabel lblPas = new JLabel("Pa\u00EDs");
		lblPas.setBounds(30, 157, 120, 25);
		getContentPane().add(lblPas);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(200, 158, 221, 22);
		getContentPane().add(cboPais);

	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		FrmRegistraClub frm = new FrmRegistraClub();
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
			String nom = txtNombre.getText().trim();
			String fec = txtFecha.getText().trim();
			int posPais = cboPais.getSelectedIndex();
			
			if (!nom.matches(Validaciones.TEXTO)) {
				mensaje("El nombre es de 2 a 20 caracteres");
			}else if (!fec.matches(Validaciones.FECHA)) {
				mensaje("la fecha tiene formato YYYY-MM-dd");
			}else if (posPais == 0) {
				mensaje("Selecciona un País");
			}else {
				String pais = cboPais.getSelectedItem().toString();
				String idPais = pais.split(":")[0];
				
				Pais objPais = new Pais();
				objPais.setIdPais(Integer.parseInt(idPais));
				
				Club objClub = new Club();
				objClub.setNombre(nom);
				objClub.setFechaCreacion(Date.valueOf(fec));
				objClub.setEstado(1);
				objClub.setPais(objPais);
				
				ClubModel model = new ClubModel();
				int salida = model.insertaClub(objClub);
				if (salida > 0) {
					mensaje("Se insertó correctamente");
				}else {
					mensaje("Error en el Registro");
				}
				
			}
	}
}




