package clases;
import java.sql.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		opciones(Menu());
		
		
	}

	//Funciones 
	public static int Menu() {
		int opcion;
		Scanner teclado = new Scanner(System.in);
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
		System.out.printf("| Ingrese su opci�n: ");
		opcion = teclado.nextInt();
		return (opcion);

	}
	public static void opciones(int op) {
		
		String user;
		String pass;
		Scanner teclado = new Scanner(System.in);
		switch(op) {
		case 1: 
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                          LOGIN                                   |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("|                                                                  |");
			System.out.printf("     USUARIO: ");
			user = teclado.nextLine();
			System.out.printf("     CONTRASE�A: ");
			pass = teclado.next();
			Login logueo = new Login();
			if (logueo.login(user, pass) == 1) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                       Login exitoso                              |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Bienvenido/a nuevamente                         |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				Consulta tipoU =new Consulta();
				menuUsuario(tipoU.determinarTUsuario(user));
				
				
			}else {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          ERROR! Usuario o Contrase�a incorrectos                 |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(2500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				System.out.println("|        Desea intentarlo nuevamente...?                           |");
				System.out.println("| (1) SI                                                           |");
				System.out.println("| (2) NO (volver al menu anterior)                                 |");
				System.out.printf("|  Ingrese su opci�n: ");
				if (teclado.nextInt() == 1)
					opciones(1);
				else opciones(Menu());
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
					  
					} catch (Exception e) {
						System.out.println("error!!");
						System.out.println(e);
					}
				
			System.out.printf("Presione una tecla + 'Enter' para volver al menu anterior");
			teclado.next();
			opciones(Menu());
			
			break;
		
		case 3: 
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                         NUEVO USUARIO                            |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("|                                                                  |");
			System.out.printf("   Ingrese nombre de Usuario: ");
			String nick = teclado.nextLine();
			System.out.printf("   Ingrese contrase�a: ");
			pass = teclado.nextLine();
			System.out.printf("   Ingrese su Nombre: ");
			String name = teclado.nextLine();
			System.out.printf("   Ingrese su Apellido: ");
			String surname = teclado.nextLine();
			System.out.printf("   Ingrese su DNI: ");
			int dni = teclado.nextInt();
			System.out.printf("   Ingrese su  direcci�n de correo electronico: ");
			String email = teclado.next();
			int userType = 2;
			int state = 1;
			Consulta existe = new Consulta();
			int resultado = existe.existeUsuario(nick);
			if (resultado == 0) {
				Usuario newUser = new Usuario(name, surname, email, nick, pass, userType, state, dni);
				existe.insertarUsuario(newUser);
				
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("                  Bienvenido/a "+nick+"");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				
			}
			else {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          ERROR! El nombre de usuario ya existe                   |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|        Desea intentarlo nuevamente...?                           |");
				System.out.println("| (1) SI                                                           |");
				System.out.println("| (2) NO (volver al menu anterior)                                 |");
				System.out.printf("|  Ingrese su opci�n: ");
				if (teclado.nextInt() == 1)
					opciones(3);
				else opciones(Menu());
				
			}
				
			
		break;
		
		case 4:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                         HASTA LUEGO!!                            |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			break;
		
		default: 		
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|          OPCION NO DISPONIBLE, INTENTE NUEVAMENTE                |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			opciones(Menu());
			break;
		
		}
		teclado.close();
		
	}
	
	public static void menuUsuario(int tipo) {
		int opcion;
		Scanner teclado = new Scanner(System.in);
		
		switch(tipo) {
		case 1:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Administradores - Biblioteca 2.0                |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("| (1) Crear, Ver, Actualizar o Borrar un usuario administrador     |");
			System.out.println("| (2) Acciones con cuotas de Socios                                |");
			System.out.println("| (3) Ver registro de multas de un Socio                           |");
			System.out.println("| (4) Ver, Actualizar o Dar de Baja un Socio                       |");
			System.out.println("| (5) Ver libros descargados por un Socio                          |");
			System.out.println("| (6) Crear, Actualizar o Borrar un Libro                          |");
			System.out.println("| (7) Buscar un libro                                              |");
			System.out.println("| (8) Salir                                                        |");
			System.out.println("|                                                                  |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.printf("| Ingrese su opci�n: ");
			opcion = teclado.nextInt();
			break;
		case 2:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Usuarios - Biblioteca 2.0                       |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("| (1) Ver libros disponibles                                       |");
			System.out.println("| (2) Buscar un libro                                              |");
			System.out.println("| (3) Ver estado de cuotas                                         |");
			System.out.println("| (4) Ver historico de descargas                                   |");
			System.out.println("| (5) Darse de baja                                                |");
			System.out.println("| (6) Salir                                                        |");
			System.out.println("|                                                                  |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.printf("| Ingrese su opci�n: ");
			opcion = teclado.nextInt();
			break;
		case 4:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("| ERROR!! Usuario de baja o suspendido. Contacte un administrador  |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			opciones(Menu());
			break;
		default:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|         ERROR!! Regresando al men� anterior...                   |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			opciones(Menu());
			break;
		}
	}
}