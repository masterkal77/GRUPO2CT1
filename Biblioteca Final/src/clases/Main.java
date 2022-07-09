package clases;
import java.sql.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		//Scanner teclado = new Scanner(System.in);
		/*while (opcion != 4 || opcion != 1 || opcion != 2 || opcion != 3) {
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("|                  Bienvenido a Biblioteca 2.0                     |");
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("|                                                                  |");
		System.out.println("| (1) Ingresar al sistema con su usuario                           |");
		System.out.println("| (2) Ingresar al sistema como invitado                            |");
		System.out.println("| (3) Crear nuevo usuario                                          |");
		System.out.println("| (4) Salir                                                        |");
		System.out.println("|                                                                  |");
		System.out.println("+------------------------------------------------------------------+");
		System.out.printf("| Ingrese su opción: ");*/
		//opcion = teclado.nextInt();
		opcion = Menu();
		String user;
		String pass;
		Scanner teclado = new Scanner(System.in);
		switch(opcion) {
		case 1: 
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                          LOGIN                                   |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("|                                                                  |");
			System.out.printf("     USUARIO: ");
			user = teclado.nextLine();
			System.out.printf("     CONTRASEÑA: ");
			pass = teclado.next();
			Login logueo = new Login();
			if (logueo.login(user, pass) == 1) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                       Login exitoso                              |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Bienvenido/a nuevamente                         |");
				System.out.println("+------------------------------------------------------------------+");
			}else {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          ERROR! Usuario o Contraseña incorrectos                 |");
				System.out.println("+------------------------------------------------------------------+");
				
			}
			
			break;
			
		case 2: 
			try {
				Conexion conn = new Conexion();
				Connection con = null;
				Statement stm = null;

				con = conn.conectar();
				stm = con.createStatement();
				String sql;
				sql = "SELECT L.id_Libro AS ID, L.titulo AS Titulo, L.descripcion AS Descripcion, L.paginas AS Paginas, A.nombre AS Autor  FROM libro as L INNER JOIN autor as A ON L.id_Autor = A.id_Autor;";
				ResultSet rs = stm.executeQuery(sql);
					  
				System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
				  System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|\n","ID", "Nombre", "Descripcion", "Pag.", "Autor");
				  System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
				  
				  while (rs.next()) {
						int id = rs.getInt("ID");
						String titulo = rs.getString("Titulo");
						String desc = rs.getString("Descripcion");
						int pag = rs.getInt("Paginas");
						String autor = rs.getString ("Autor");
						System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|\n", id, titulo, desc, pag, autor);
				
						}
				  System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
				  //System.out.println("Tome nota y presione una tecla para salir");
				  //System.in.read();
				  /*try { Thread.sleep(10000); //1000 milliseconds is one second. 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				  */
				  //String aa = teclado.nextLine();
				  //opciones(4);
			} catch (Exception e) {
					System.out.println("error!!");
					//System.out.println(e);
				}
			break;
		
		case 3: 
			//Scanner teclado2 = new Scanner(System.in);
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                         NUEVO USUARIO                            |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("|                                                                  |");
			System.out.printf("   Ingrese nombre de Usuario: ");
			String nick = teclado.nextLine();
			System.out.printf("   Ingrese contraeña: ");
			pass = teclado.nextLine();
			System.out.printf("   Ingrese su Nombre: ");
			String name = teclado.nextLine();
			System.out.printf("   Ingrese su Apellido: ");
			String surname = teclado.nextLine();
			System.out.printf("   Ingrese su DNI: ");
			int dni = teclado.nextInt();
			System.out.printf("   Ingrese su  dirección de correo electronico: ");
			String email = teclado.next();
			int userType = 2;
			int state = 1;
			Consulta existe = new Consulta();
			int resultado = existe.existeUsuario(nick);
			//System.out.println("Existe "+ resultado);
			if (resultado == 0) {
				Usuario newUser = new Usuario(name, surname, email, nick, pass, userType, state, dni);
				existe.insertarUsuario(newUser);
				
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("                  Bienvenido/a "+nick+"");
				System.out.println("+------------------------------------------------------------------+");
			}
			else {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          ERROR! El nombre de usuario ya existe                   |");
				System.out.println("+------------------------------------------------------------------+");
				
				
			}
				
			
		break;
		
		case 4: 
			
			break;
		
		default: 		
			
			break;
		
	}
		
		//opciones(opcion);
		
	
}

	
	public static int Menu() {
		int opcion;
		Scanner teclado = new Scanner(System.in);
		//while (opcion != 4 || opcion != 1 || opcion != 2 || opcion != 3) {
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("|                  Biblioteca 2.0                                  |");
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("|                                                                  |");
		System.out.println("| (1) Ingresar al sistema con su usuario                           |");
		System.out.println("| (2) Ingresar al sistema como invitado                            |");
		System.out.println("| (3) Crear nuevo usuario                                          |");
		System.out.println("| (4) Salir                                                        |");
		System.out.println("|                                                                  |");
		System.out.println("+------------------------------------------------------------------+");
		System.out.printf("| Ingrese su opción: ");
		opcion = teclado.nextInt();
		teclado.close();
		return (opcion);
	}
	public static void opciones(int op) {
		
		//teclado.close();
		
	}
}