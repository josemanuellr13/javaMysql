package empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ej3 {
	public static void main(String[] args) {
		// Atributos
		String apellido = "Ramos";
		String oficio = "Pro";
		String error = "";
		float salario = 1400;
		float comision = 15;
		int dir = 3444;
		int emp_no = 13;
		int dept_no = 10;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "userjdbc", "userjdbc");
			Statement sentencia = conexion.createStatement();
			
			// Comprobamos que el dpt exista
			String sql = "SELECT dept_no from departamentos WHERE dept_no = " + dept_no;
			ResultSet rs = sentencia.executeQuery(sql);
			
			if(rs.next() == false) {
				error += "ERROR: Departamento no existe \n";
			}
			
			rs.close();
				
			// Comprobamos que NO existe numEmpleado
			sql = "SELECT emp_no from empleados WHERE emp_no = " + emp_no;
			rs = sentencia.executeQuery(sql);
			
			if(rs.next()) {
				error += "ERROR: EMP_NO "  + emp_no + " ya existe \n";
			}
			rs.close();
			
			// Comprobamos Salario > 0
			if(salario <= 0) {
				error += "ERROR: Salario " + salario + "inferior a 0 \n";
			}
					
			// Comprobamos que DIR exista		
			sql = "SELECT dir from empleados WHERE dir = " + dir;
			rs = sentencia.executeQuery(sql);
								
			if(rs.next() == false) {
				error += "ERROR: DIR " + dir +" no existe \n";
			}
				
			rs.close();
							
			// Insertamos SQL
			sql = "INSERT INTO empleados VALUES ( " + emp_no + ",'" + apellido + "','" + oficio + "'," + dir + ",NOW()," + salario + "," + comision + "," + dept_no + ");";
			int r = sentencia.executeUpdate(sql);
			
			if(r == 1) {
				System.out.println("Registro realizado");
			}
			rs.close();
			
			//Cerramos
			sentencia.close();
			conexion.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(error);
		}
	}

}
