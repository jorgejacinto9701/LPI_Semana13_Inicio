package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Club;
import entidad.Pais;
import util.MySqlDBConexion;

public class ClubModel {

	private static Logger log = Logger.getLogger(ClubModel.class.getName());
	
	public int insertaClub(Club obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			//String sql = "insert into club value(null,?,?,curtime(),?,?)";
			//pstm = conn.prepareStatement(sql);
			String sql = "call sp_club_inserta(?,?,?,?)";
			pstm = conn.prepareCall(sql);
			
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, obj.getFechaCreacion());
			pstm.setInt(3, obj.getEstado());
			pstm.setInt(4, obj.getPais().getIdPais());
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
	
	public int eliminaClub(int idClub) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			//String sql = "delete from club where idclub = ?";
			//pstm = conn.prepareStatement(sql);
			String sql = "call sp_club_elimina(?)";
			pstm = conn.prepareCall(sql);
			pstm.setInt(1, idClub);
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
	
	public int actualizaClub(Club obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			//String sql = "update club set nombre=?, fechaCreacion=?, estado=?, idPais=? where idclub = ?";
			//pstm = conn.prepareStatement(sql);
			String sql = "call sp_club_actualiza(?,?,?,?,?)";
			pstm = conn.prepareCall(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, obj.getFechaCreacion());
			pstm.setInt(3, obj.getEstado());
			pstm.setInt(4, obj.getPais().getIdPais());
			pstm.setInt(5, obj.getIdClub());
			
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
	
	public List<Club> listaTodos(){
		ArrayList<Club> salida = new ArrayList<Club>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT c.*, p.nombre FROM club c inner join pais p on c.idPais = p.idPais";
			psmt = conn.prepareStatement(sql);
			
			//String sql = "call sp_club_lista()";
			//psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Club objClub = null;
			Pais objPais = null;
			while(rs.next()) {
				objClub = new Club();
				objClub.setIdClub(rs.getInt(1));
				objClub.setNombre(rs.getString(2));
				objClub.setFechaCreacion(rs.getDate(3));
				objClub.setFechaRegistro(rs.getTimestamp(4));
				objClub.setEstado(rs.getInt(5));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(6));
				objPais.setNombre(rs.getString(7));
				objClub.setPais(objPais);
				salida.add(objClub);
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
	
	
	public List<Club> listaPorPais(int idPais){
		ArrayList<Club> salida = new ArrayList<Club>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT c.*, p.nombre FROM club c inner join pais p on c.idPais = p.idPais where c.idPais = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idPais);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Club objClub = null;
			Pais objPais = null;
			while(rs.next()) {
				objClub = new Club();
				objClub.setIdClub(rs.getInt(1));
				objClub.setNombre(rs.getString(2));
				objClub.setFechaCreacion(rs.getDate(3));
				objClub.setFechaRegistro(rs.getTimestamp(4));
				objClub.setEstado(rs.getInt(5));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(6));
				objPais.setNombre(rs.getString(7));
				objClub.setPais(objPais);
				salida.add(objClub);
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
