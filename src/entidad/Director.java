package entidad;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Director {

	private int idDirector;
	private String nombre;
	private Date fechaNacimiento;
	private Timestamp fechaRegistro;
	private int estado;
	private Grado grado;
	
	//Inicio get para el reporte
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getFormatoEstado() {
		return estado == 1 ? "Activo" : "Inactivo";
	}
	public String getFormatoGrado() {
		return grado.getNombre();
	}
	public String getFormatoFecNacimiento() {
		return sdf1.format(fechaNacimiento);
	}
	public String getFormatoFecRegistro() {
		return sdf2.format(fechaRegistro);
	}
	//Fin
	
	public int getIdDirector() {
		return idDirector;
	}
	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Grado getGrado() {
		return grado;
	}
	public void setGrado(Grado grado) {
		this.grado = grado;
	}
	
	
	
	
	
}
