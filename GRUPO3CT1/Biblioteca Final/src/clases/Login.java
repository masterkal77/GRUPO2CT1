package clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
	Conexion conn = new Conexion();
	Connection usarConexion = null;
	Statement stm = null;
	ResultSet rs = null;
	PreparedStatement ps;
	
	
	
	public Login() {
		
	}
	
	
	public int login(String usuario, String contrasenia) {
		int resultado = 0;
		usarConexion = conn.conectar();
		String consulta = "SELECT * FROM usuario WHERE nombreUsuario = '"+usuario+"' AND contraseña = '"+contrasenia+"'";
		
		try {
			
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta);
			if (rs.next()) {
				resultado = 1;
			}
		} catch (Exception error) {
			System.out.println("Error inesperado");
			System.out.println(error); 
		}
		return resultado;
	}
	
	

}
