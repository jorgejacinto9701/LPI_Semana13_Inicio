package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

	private static Logger log = Logger.getLogger(ConcursoModel.class.getName());
	
	public List<Concurso> listaConcursoPorPremio(double preIni, double preFin){
		List<Concurso> salida = new ArrayList<Concurso>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "select * from concurso where premio between ? and ?";
			pstm = conn.prepareStatement(sql);
			pstm.setDouble(1, preIni);
			pstm.setDouble(2, preFin);
			log.info("SQL >> " + pstm);
		
			//3 Se ejecuta el SQL
			rs = pstm.executeQuery();
	
			//4 Se consume la data para llenar el arrayList
			Concurso obj = null;
			while(rs.next()) {
				obj = new Concurso();
				obj.setIdConcurso(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setPremio(rs.getDouble(3));
				obj.setPrecio(rs.getDouble(4));
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
