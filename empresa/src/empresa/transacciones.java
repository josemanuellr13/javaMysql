package empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class transacciones {
	public static void main(String[] args) {
		
		String[] sqls = {"INSERT INTO departamentos VALUES (50, 'Programacion', 'Valencia')","INSERT INTO departamentos VALUES (60, 'Disenyo', 'Murcia')"};
		String[] sqlsConError = {"INSERT INTO departamentos VALUES (70, Marketing, 'Vigo')","INSERT INTO departamentos VALUES ('80', 'Disenyo', 'Malaga')"};

		try {
			// Indicamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Creamos conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "userjdbc", "userjdbc");
			
			conexion.setAutoCommit(false);
			Statement sentencia = conexion.createStatement();
			
			try {
				for(int i = 0 ; i < sqls.length ; i++) {
					sentencia.executeUpdate(sqlsConError[i]);
				}
				
				
				
				conexion.commit();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				conexion.close();
			}
			
			// Cerramos
			sentencia.close();
			conexion.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
