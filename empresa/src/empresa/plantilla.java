package empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class plantilla {
	public static void main(String[] args) {
		try {
			// Indicamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Creamos conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "userjdbc", "userjdbc");
			
			// Creamos sentencia y ejecutamos
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet rs = sentencia.executeQuery(sql);
			
			
			// Recorremos resultado
			while(rs.next()) {
				System.out.println(rs.getInt(1)+ " " + rs.getString(2)+ " " + rs.getString(3));
			}
			
			//Cerramos
			rs.close();
			sentencia.close();
			conexion.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
