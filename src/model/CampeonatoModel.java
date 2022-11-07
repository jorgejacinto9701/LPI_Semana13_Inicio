package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Campeonato;
import util.MySqlDBConexion;

public class CampeonatoModel {

	private static Logger log = Logger.getLogger(CampeonatoModel.class.getName());

	public int insertaCampeonato(Campeonato obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "insert into campeonato value(null,?,?,curtime(),1)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setInt(2, obj.getAnnio());
			
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
	
	public List<Campeonato> listaCampeonatoPorNombreLike(String filtro){
		ArrayList<Campeonato> salida = new ArrayList<Campeonato>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "select * from campeonato where nombre like ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, filtro + "%");
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Campeonato obj = null;
			while(rs.next()) {
				obj = new Campeonato();
				obj.setIdCampeonato(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setAnnio(rs.getInt(3));
				obj.setFechaRegistro(rs.getDate(4));
				obj.setEstado(rs.getInt(5));
				salida.add(obj);
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
	
	public int eliminaCampeonato(int idCampeonato) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "delete from campeonato where idCampeonato = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idCampeonato);
			
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
	
	public int actualizaCampeonato(Campeonato obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "update campeonato set nombre=?, año=?, estado=? where idCampeonato=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setInt(2, obj.getAnnio());
			pstm.setInt(3, obj.getEstado());
			pstm.setInt(4, obj.getIdCampeonato());
			
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
	
	
	public List<Campeonato> listaTodos(){
		ArrayList<Campeonato> salida = new ArrayList<Campeonato>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "select * from campeonato";
			psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Campeonato obj = null;
			while(rs.next()) {
				obj = new Campeonato();
				obj.setIdCampeonato(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setAnnio(rs.getInt(3));
				obj.setFechaRegistro(rs.getDate(4));
				obj.setEstado(rs.getInt(5));
				salida.add(obj);
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
