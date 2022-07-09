package clases;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	Connection con; 
	private String DRIVER = "com.mysql.cj.jdbc.Driver";
	private String BD_URL= "jdbc:mysql://localhost:3306/biblioteca_f";
	private String USER = "root";
	private String PASS = "";
	
	public Connection conectar() {
		try {
			Class.forName(DRIVER); //solamente vamos a decirle que utilice un driver(linea6)
			con = DriverManager.getConnection(BD_URL,USER,PASS); //ayuda a hacer el vinculo
			//System.out.println("Conexion exitosa");
		}catch(Exception error) { //exepciones son tipos de errores
			System.out.println("Problemas al intentar conectar a la bd" );
			System.out.println(error);
		}
		return con;
	}
}