package entidad;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Club {

	private int idClub;
	private String nombre;
	private Date fechaCreacion;
	private Timestamp fechaRegistro;
	private int estado;
	private Pais pais;
	
	//Inicio get para el reporte
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getFormatoEstado() {
		return estado == 1 ? "Activo" : "Inactivo";
	}
	public String getFormatoPais() {
		return pais.getNombre();
	}
	public String getFormatoFecha() {
		return sdf1.format(fechaCreacion);
	}
	public String getFormatoFecRegistro() {
		return sdf2.format(fechaRegistro);
	}
	//Fin 
	
	public int getIdClub() {
		return idClub;
	}
	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}
