package empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ej4 {
	public static void main(String[] args) {
		// Atributos
		int dept_no = 144;
		String sql = "";
		PreparedStatement sentenciaPreparada = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "userjdbc", "userjdbc");
			
			// Comprobamos que el dpt exista
			sql = "SELECT dept_no from departamentos WHERE dept_no = ?" ;
			
			sentenciaPreparada = conexion.prepareStatement(sql);
			sentenciaPreparada.setInt(1,dept_no);
			
			rs = sentenciaPreparada.executeQuery();
						
			if(rs.next() == false) {
				System.out.println("ERROR: Departamento no existe \n");
			}
						
			rs.close();
			
			
			// Sacamos apellido, salario y oficio por departamento
			sql = "SELECT APELLIDO, SALARIO, OFICIO FROM `empleados` WHERE dept_no = ?";
			
			sentenciaPreparada = conexion.prepareStatement(sql);
			sentenciaPreparada.setInt(1, dept_no);
			
			rs = sentenciaPreparada.executeQuery();
			
			while(rs.next()) {
				System.out.println("Apellido: " + rs.getString("apellido") + " Salario: " + rs.getString("salario") + " Oficio: " + rs.getString("oficio"));
			}
			rs.close();
			
			System.out.println();
			
			// Visualizamos nombre de departamento
			sql = "SELECT dnombre FROM `departamentos` WHERE dept_no = ?";
			sentenciaPreparada = conexion.prepareStatement(sql);
			
			sentenciaPreparada.setInt(1, dept_no);
			
			rs = sentenciaPreparada.executeQuery();
			
			while(rs.next()) {
				System.out.println("Nombre Dept: " + rs.getString("dnombre"));
			}
			
			rs.close();
			
			System.out.println();

			// Visualizamos salario medio
			sql = "SELECT AVG(salario) as salarioMedio, COUNT(1) as Cantidad FROM `empleados` WHERE dept_no = ?";
			sentenciaPreparada = conexion.prepareStatement(sql);
			
			sentenciaPreparada.setInt(1, dept_no);
			
			rs = sentenciaPreparada.executeQuery();
			
			while(rs.next()) {
				System.out.println("Salario Medio: " + rs.getString("salarioMedio") + " Cant. Empleados: " + rs.getInt("Cantidad"));
			}
			
			rs.close();
				
			
			//Cerramos
			sentenciaPreparada.close();
			conexion.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
