package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Docente;
import util.MySqlDBConexion;

public class DocenteModel {

	private static Logger log = Logger.getLogger(DocenteModel.class.getName());
	
	public List<Docente> listaDocentePorFecha(Date fecIni, Date fecFin){
		List<Docente> salida = new ArrayList<Docente>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "select * from Docente where fechaNacimiento between ? and ?";
			pstm = conn.prepareStatement(sql);
			pstm.setDate(1, fecIni);
			pstm.setDate(2, fecFin);
			log.info("SQL >> " + pstm);
		
			//3 Se ejecuta el SQL
			rs = pstm.executeQuery();
	
			//4 Se consume la data para llenar el arrayList
			Docente obj = null;
			while(rs.next()) {
				obj = new Docente();
				obj.setIdDocente(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setDni(rs.getString(3));
				obj.setFechaNacimiento(rs.getDate(4));
				salida.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return salida;
	}
	
	
}












