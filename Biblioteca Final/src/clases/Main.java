package clases;
import java.sql.*;
import java.util.Scanner;

public class Main {

	static String nombre;
	static String password;
	
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
		System.out.printf("| Ingrese su opción: ");
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
			System.out.printf("     CONTRASEÑA: ");
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
				System.out.println("|          ERROR! Usuario o Contraseña incorrectos                 |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(2500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				System.out.println("|        Desea intentarlo nuevamente...?                           |");
				System.out.println("| (1) SI                                                           |");
				System.out.println("| (2) NO (volver al menu anterior)                                 |");
				System.out.printf("|  Ingrese su opción: ");
				if (teclado.nextInt() == 1)
					opciones(1);
				else opciones(Menu());
			}
			
			break;
			
		case 2: 
			Consulta consul = new Consulta();
			consul.mostrarLibros(3);
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
			System.out.printf("   Ingrese contraseña: ");
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
			if (resultado == 0) {
				Usuario newUser = new Usuario(name, surname, email, nick, pass, userType, state, dni);
				existe.insertarUsuario(newUser);
				
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("                  Bienvenido/a "+nick+"");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(2);
				
			}
			else {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          ERROR! El nombre de usuario ya existe                   |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|        Desea intentarlo nuevamente...?                           |");
				System.out.println("| (1) SI                                                           |");
				System.out.println("| (2) NO (volver al menu anterior)                                 |");
				System.out.printf("|  Ingrese su opción: ");
				int opc = teclado.nextInt();
				if ( opc == 1)
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
			System.out.println("| (3) Ver, Actualizar o Dar de Baja un Socio                       |");
			System.out.println("| (4) Ver libros descargados por un Socio                          |");
			System.out.println("| (5) Crear, Actualizar o Borrar un Libro                          |");
			System.out.println("| (6) Buscar un libro                                              |");
			System.out.println("| (7) Salir                                                        |");
			System.out.println("|                                                                  |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.printf("| Ingrese su opción: ");
			opcion = teclado.nextInt();
			if (opcion == 7) {
				try { Thread.sleep(1000); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				opciones(Menu());
			}
				
			else accionesAdmin(opcion);
						
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
			System.out.printf("| Ingrese su opción: ");
			opcion = teclado.nextInt();
			if (opcion == 6)
				opciones(Menu());
			else accionesUser(opcion);
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
			System.out.println("|         ERROR!! Regresando al menú anterior...                   |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			opciones(Menu());
			break;
		}
	}
	
	public static void accionesAdmin(int op) {
		Scanner teclado = new Scanner(System.in);
		Consulta cons = new Consulta();
		Usuario user = new Usuario();
		int opcion;
		
		switch(op) {
		case 1:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Administradores - Biblioteca 2.0                |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("| (1) Crear un nuevo usuario administrador                         |");
			System.out.println("| (2) Ver datos de un Administrador                                |");
			System.out.println("| (3) Actualizar datos de un Administrador                         |");
			System.out.println("| (4) Borrar un usuario Administrador                              |");
			System.out.println("| (5) Salir                                                        |");
			System.out.println("|                                                                  |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.printf("| Ingrese su opción: ");
			opcion = teclado.nextInt();
			crudUsuarios(opcion, 1);
			
			break;
		case 2:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Administradores - Biblioteca 2.0                |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("| (1) Ver cuotas impagas                                           |");
			System.out.println("| (2) Registrar cuota pagada                                       |");
			System.out.println("| (3) Salir                                                        |");
			System.out.println("|                                                                  |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.printf("| Ingrese su opción: ");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre de USUARIO a buscar: ");
				user = cons.buscarUsuario(teclado.next());
				if (user == null) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE USUARIO ERRÓNEO O INEXISTENTE                       |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					menuUsuario(1);
				} else {
					cons.moraCuotas(user.getIdUsuario());
					System.out.println("+------------------------------------------------------------------+");
				}
				
			}else if (opcion == 2) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre de USUARIO a buscar: ");
				user = cons.buscarUsuario(teclado.next());
				if (user == null) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE USUARIO ERRÓNEO O INEXISTENTE                       |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					menuUsuario(1);
				} else {System.out.println("|                                                                  |");
						System.out.printf("|  Ingrese el MONTO de la cuota: ");
						int cuota = teclado.nextInt();
						cons.registrarCuota(user.getIdUsuario(), cuota);
						System.out.println("+------------------------------------------------------------------+");
						System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
						System.out.println("+------------------------------------------------------------------+");
						try { Thread.sleep(1000); 
						  } catch(InterruptedException ex) 
						  { Thread.currentThread().interrupt(); }
						menuUsuario(1);
					}
			}else menuUsuario(1);
			
			
			break;
		case 3:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Administradores - Biblioteca 2.0                |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("| (1) Ver datos de un Socio                                        |");
			System.out.println("| (2) Actualizar datos de un Socio                                 |");
			System.out.println("| (3) Dar de baja un Socio										   |");
			System.out.println("| (4) Salir                                                        |");
			System.out.println("|                                                                  |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.printf("| Ingrese su opción: ");
			opcion = teclado.nextInt();
			crudUsuarios(opcion, 2);
			break;
		case 4:
			
			break;
		case 5:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Administradores - Biblioteca 2.0                |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("| (1) Crear un libro nuevo                                         |");
			System.out.println("| (2) Actualizar datos de un Libro                                 |");
			System.out.println("| (3) Borrar un libro   										   |");
			System.out.println("| (4) Salir                                                        |");
			System.out.println("|                                                                  |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.printf("| Ingrese su opción: ");
			opcion = teclado.nextInt();
			if (opcion == 4)
				menuUsuario(1);
			else
				crudLibros(opcion);
			break;
		case 6:
			crudLibros(4);
			break;
		case 7:
			menuUsuario(1);
			break;
		default:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			menuUsuario(1);
			
		
		}
		
		
	}
	public static void accionesUser(int op) {
		Scanner teclado = new Scanner(System.in);
		int opcion;
		Consulta consul = new Consulta();
		int idUsuario = consul.buscarIdUsuario(nombre);
		switch(op) {
		case 1:
			consul.mostrarLibros(2);
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|        Desea realizar otra consulta...?                          |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("| (1) SI                                                           |");
			System.out.println("| (2) NO (sale del sistema)                                        |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.print("|  Ingrese su opción:                                               ");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				menuUsuario(2);
			} else
				Menu();
			break;
		case 2:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                   Búsqueda de Libros                             |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("| (1) Buscar por nombre de libro                                   |");
			System.out.println("| (2) Buscar por ID de libro                                       |");
			System.out.println("| (3) Volver al menú anterior                                      |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.print("|   Ingrese su opción: ");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				System.out.print("|  Ingrese el nombre del libro: ");
				Scanner nombre = new Scanner(System.in);
				String nombreLibro = nombre.nextLine();
				Libro libroBuscado = consul.buscarLibro(nombreLibro);
				libroBuscado.mostrarDatos();
				} else if (opcion == 2) {
					System.out.print("|  Ingrese el ID del libro:                                        ");
					int idLibro = teclado.nextInt();
					Libro libroBuscado = consul.buscarLibro(idLibro);
					libroBuscado.mostrarDatos();
				} else {
					accionesUser(2);
				}
			break;
		case 3:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                   Historial de Cuotas                            |");
			System.out.println("+------------------------------------------------------------------+");
			consul.historialCuotas(idUsuario);
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|        Desea realizar otra consulta...?                          |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("| (1) SI                                                           |");
			System.out.println("| (2) NO (sale del sistema)                                        |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.print("|  Ingrese su opción:                                               ");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				menuUsuario(2);
			} else
				Menu();
			break;
		case 4:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                Historial de Descargas                            |");
			System.out.println("+------------------------------------------------------------------+");
			consul.historialDescargas(idUsuario);
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|        Desea realizar otra consulta...?                          |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("| (1) SI                                                           |");
			System.out.println("| (2) NO (sale del sistema)                                        |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.print("|  Ingrese su opción:                                               ");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				menuUsuario(2);
			} else
				Menu();
			break;
		case 5:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                Baja de Usuario                                   |");
			System.out.println("+------------------------------------------------------------------+");
			consul.bajaUsuario(idUsuario);
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|        Desea realizar otra consulta...?                          |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("| (1) SI                                                           |");
			System.out.println("| (2) NO (sale del sistema)                                        |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.print("|  Ingrese su opción:                                               ");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				menuUsuario(2);
			} else
				Menu();
			break;
		default:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			menuUsuario(2);
			break;
		}
	}
	public static void crudUsuarios(int op, int tipo) {
		Scanner teclado = new Scanner(System.in);
		Usuario user = new Usuario();
		Consulta consul = new Consulta();
	
		if (tipo == 1) {
			switch(op) {
			case 1:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                         NUEVO ADMINISTRADOR                      |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.println("|                                                                  |");
				System.out.printf("   Ingrese nombre de Usuario: ");
				String nick = teclado.nextLine();
				System.out.printf("   Ingrese contraseña: ");
				String pass = teclado.nextLine();
				System.out.printf("   Ingrese Nombre: ");
				String name = teclado.nextLine();
				System.out.printf("   Ingrese Apellido: ");
				String surname = teclado.nextLine();
				System.out.printf("   Ingrese DNI: ");
				int dni = teclado.nextInt();
				System.out.printf("   Ingrese dirección de correo electronico: ");
				String email = teclado.next();
				int userType = 1;
				int state = 1;
				Consulta existe = new Consulta();
				int resultado = existe.existeUsuario(nick);
				if (resultado == 0) {
					Usuario newUser = new Usuario(name, surname, email, nick, pass, userType, state, dni);
					existe.insertarUsuario(newUser);
					System.out.println("+------------------------------------------------------------------+");			
				}
				accionesAdmin(1);
				break;
			case 2:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre de USUARIO a buscar: ");
				user = consul.buscarUsuario(teclado.next());
				if (user == null || user.getTipoUsuario() != 1) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE USUARIO ADMINISTRADOR ERRÓNEO O INEXISTENTE         |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(1);
				} else {
					user.mostrarDatos();
					System.out.println("|  TOME NOTA Y PRESIONE UNA TECLA + 'ENTER' PARA CONTINUAR         |");
					System.out.println("+------------------------------------------------------------------+");
					String a = teclado.next();
					accionesAdmin(1);
					
				}
				
				break;
			case 3:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre de USUARIO a MODIFICAR: ");
				user = consul.buscarUsuario(teclado.next());
				if (user == null || user.getTipoUsuario() != 1) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE USUARIO ADMINISTRADOR ERRÓNEO O INEXISTENTE         |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(1);
				} else {
					user.cargarUsuario(1);
					consul.actualizarUsuario(user, user.getIdUsuario());
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(1500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(1);
				}
				
				break;
			case 4:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre de USUARIO a ELIMINAR: ");
				user = consul.buscarUsuario(teclado.next());
				if (user == null || user.getTipoUsuario() != 1) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE USUARIO ADMINISTRADOR ERRÓNEO O INEXISTENTE         |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(1);
				} else {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|  Se ELIMINARA el usuario (1) Confirmar  (2) Cancelar             |");
					System.out.println("|  Ingrese su opción: ");
					int conf = teclado.nextInt();
					if (conf == 1)
						consul.borrarUsuario(user.getIdUsuario());
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(1500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(1);
				}
				break;
			default:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(1);
				
			}
		}else { if (tipo == 2) {
			switch(op) {
			case 1:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre de USUARIO a buscar: ");
				user = consul.buscarUsuario(teclado.next());
				if (user == null) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE SOCIO ERRÓNEO O INEXISTENTE 					       |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(3);
				} else {
					user.mostrarDatos();
					System.out.println("|  TOME NOTA Y PRESIONE UNA TECLA + 'ENTER' PARA CONTINUAR         |");
					System.out.println("+------------------------------------------------------------------+");
					String a = teclado.next();
					accionesAdmin(3);
				}
				break;
			case 2:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre del SOCIO a MODIFICAR: ");
				user = consul.buscarUsuario(teclado.next());
				if (user == null || user.getTipoUsuario() !=2) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE SOCIO ERRÓNEO O INEXISTENTE				           |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2000); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(3);
				} else {
					user.cargarUsuario(user.getTipoUsuario());
					consul.actualizarUsuario(user, user.getIdUsuario());
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(1500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(3);
				}
				break;
			case 3:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                  Administradores - Biblioteca 2.0                |");
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.printf("|  Ingrese el nombre del SOCIO a DAR DE BAJA: ");
				user = consul.buscarUsuario(teclado.next());
				if (user == null || user.getTipoUsuario() != 2) {
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|    NOMBRE DE SOCIO ERRÓNEO, INEXISTENTE O DE BAJA                |");
					System.out.println("|    Regresando al menu anterior...                                |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(2500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(3);
				} else {
					consul.bajaUsuario(user.getIdUsuario());
					System.out.println("+------------------------------------------------------------------+");
					System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
					System.out.println("+------------------------------------------------------------------+");
					try { Thread.sleep(1500); 
					  } catch(InterruptedException ex) 
					  { Thread.currentThread().interrupt(); }
					accionesAdmin(3);
				}
				break;
			case 4:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(1);
				break;
			default:
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          OPCION NO DISPONIBLE, INTENTE NUEVAMENTE                |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(1);
			}
		}
			
		}
		
		
	}
	
	public static void crudLibros(int op) {
		Scanner teclado = new Scanner(System.in);
		Libro book = new Libro();
		Consulta cons = new Consulta();
		
		switch(op) {
		case 1:
			book = book.crearLibro();
			if (cons.buscarLibro(book.getTitulo()) == null) {
				int id = cons.buscarIdAutor(book.getAutor());
				cons.crearLibro(book, id);
				
			}else {
				
			}
			accionesAdmin(5);
			break;
		case 2:
			
			break;
		case 3:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Administradores - Biblioteca 2.0                |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.printf("|  Ingrese el nombre del LIBRO a ELIMINAR: ");
			book = cons.buscarLibro(teclado.next());
			if (book == null) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|    NOMBRE DE LIBRO ERRÓNEO O INEXISTENTE         |");
				System.out.println("|    Regresando al menu anterior...                                |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(2500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(1);
			}else {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|  Se ELIMINARA el Libro (1) Confirmar  (2) Cancelar             |");
				System.out.println("|  Ingrese su opción: ");
				int conf = teclado.nextInt();
				if (conf == 1)
					cons.eliminarLibro(book.getIdLibro());
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|          REGRESANDO AL MENU ANTERIOR...                          |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(1);
			}
			
			break;
		case 4:
			
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                  Administradores - Biblioteca 2.0                |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.printf("|  Ingrese el nombre del LIBRO a BUSCAR: ");
			book = cons.buscarLibro(teclado.next());
			if (book == null) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|    NOMBRE DE LIBRO ERRÓNEO O INEXISTENTE         |");
				System.out.println("|    Regresando al menu anterior...                                |");
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(2500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(1);
			} else {
				book.mostrarDatos(book);
				System.out.println("|  TOME NOTA Y PRESIONE UNA TECLA + 'ENTER' PARA CONTINUAR         |");
				System.out.println("+------------------------------------------------------------------+");
				String a = teclado.next();
				try { Thread.sleep(1500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt(); }
				menuUsuario(1);
			}
			break;
		default:
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|          OPCION NO DISPONIBLE, INTENTE NUEVAMENTE                |");
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
			menuUsuario(1);
		}
		
	}
}