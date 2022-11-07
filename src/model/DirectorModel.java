package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Director;
import entidad.Grado;
import util.MySqlDBConexion;

public class DirectorModel {

	private static Logger log = Logger.getLogger(DirectorModel.class.getName());
	
	public int insertaDirector(Director obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "insert into director value(null,?,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, obj.getFechaNacimiento());
			pstm.setInt(3, obj.getEstado());
			pstm.setInt(4, obj.getGrado().getIdGrado());
			log.info(">>> " + pstm);
			
			//3 Ejecutamos a la base de datos
			//Retorna la cantidad de registrados en salida
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public List<Director> listaTodos(){
		ArrayList<Director> salida = new ArrayList<Director>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT c.*, p.nombre FROM Director c inner join Grado p on c.idGrado = p.idGrado";
			psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Director objDirector = null;
			Grado objGrado = null;
			while(rs.next()) {
				objDirector = new Director();
				objDirector.setIdDirector(rs.getInt(1));
				objDirector.setNombre(rs.getString(2));
				objDirector.setFechaNacimiento(rs.getDate(3));
				objDirector.setFechaRegistro(rs.getTimestamp(4));
				objDirector.setEstado(rs.getInt(5));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(6));
				objGrado.setNombre(rs.getString(7));
				objDirector.setGrado(objGrado);
				salida.add(objDirector);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	
	public List<Director> listaPorGrado(int idGrado){
		ArrayList<Director> salida = new ArrayList<Director>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT c.*, p.nombre FROM Director c inner join Grado p on c.idGrado = p.idGrado where c.idGrado = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idGrado);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Director objDirector = null;
			Grado objGrado = null;
			while(rs.next()) {
				objDirector = new Director();
				objDirector.setIdDirector(rs.getInt(1));
				objDirector.setNombre(rs.getString(2));
				objDirector.setFechaNacimiento(rs.getDate(3));
				objDirector.setFechaRegistro(rs.getTimestamp(4));
				objDirector.setEstado(rs.getInt(5));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(6));
				objGrado.setNombre(rs.getString(7));
				objDirector.setGrado(objGrado);
				salida.add(objDirector);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
}







