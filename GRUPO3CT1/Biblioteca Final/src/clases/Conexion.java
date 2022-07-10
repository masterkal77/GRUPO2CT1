package clases;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private Connection con;
	private String DRIVER = "com.mysql.cj.jdbc.Driver";
	private String BD_URL= "jdbc:mysql://localhost:3306/biblioteca";
	private String USER = "root";
	private String PASS = "";

	public Connection conectar() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(BD_URL,USER,PASS); 
			//System.out.println("Conexion exitosa");
		}catch(Exception e) {
			System.out.println("Problemas al intentar conectar a la base de datos" +e);
		}
		return con;
	}
}
