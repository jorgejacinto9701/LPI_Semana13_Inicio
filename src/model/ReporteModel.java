package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.ReporteEntidad;
import util.MySqlDBConexion;

public class ReporteModel {

	private static Logger log = Logger.getLogger(ReporteModel.class.getName());
	
	public List<ReporteEntidad> listaPorRangoFechas(Date fecIni, Date fecFin){
		ArrayList<ReporteEntidad> salida = new ArrayList<ReporteEntidad>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "select g.nombre, count(*) from director d inner join grado g on d.idgrado = g.idgrado where d.fechaNacimiento between ? and ? group by g.nombre order by 1 asc"; 
			psmt = conn.prepareStatement(sql);
			psmt.setDate(1, fecIni);
			psmt.setDate(2, fecFin);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			ReporteEntidad obj = null;
			while(rs.next()) {
				obj = new ReporteEntidad();
				obj.setCategoria(rs.getString(1));
				obj.setCantidad(rs.getInt(2));
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
