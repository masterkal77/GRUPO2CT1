package clases;

import java.util.Scanner;

public class Usuario {

	private String nombre;
	private String apellido;
	private String mail;
	private String nombreUsuario; //nick que se elije en el momento del registro
	private String password; //contraseña generada al momento del registro
	private int tipoUsuario; //1: Admin, 2:Socio, 3:Visitante, 4:Baja
	private int estado; //1 activo, 0 deudor
	private int dni;
	private int idUsuario;
	
	//Constructores
	public Usuario() {
		
	}
	
	public Usuario(String nom, String ape, String mail, String nick, String pass, int tipo, int est, int dni) {
		this.nombre = nom;
		this.apellido = ape;
		this.mail = mail;
		this.nombreUsuario = nick;
		this.password = pass;
		this.tipoUsuario = tipo;
		this.estado = est;
		this.dni = dni;
	}
	
	/*public Usuario(String nom, String ape, String mail, String nick, String pass, int tipo, int dni) {
		this.nombre = nom;
		this.apellido = ape;
		this.mail = mail;
		this.nombreUsuario = nick;
		this.password = pass;
		this.tipoUsuario = tipo;
		this.dni = dni; 
	}*/
	
	public Usuario(Usuario u) {
		this.nombre = u.nombre;
		this.apellido = u.apellido;
		this.mail = u.mail;
		this.nombreUsuario = u.nombreUsuario;
		this.password = u.password;
		this.tipoUsuario = u.tipoUsuario;
		this.estado = u.estado;
		this.dni = u.dni;
	}
	
	//Metodos set y get
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	public void setApellido(String a) {
		this.apellido = a;
	}
	
	public void setMail(String m) {
		this.mail = m;
	}
	
	public void setEstado(int est) {
		this.estado = est;
	}
	
	public void setNombreUsuario(String n) {
		this.nombreUsuario = n;
	}
	
	public void setPassword(String p) {
		this.password = p;
	}
	
	public void setTipoUsuario(int t) {
		this.tipoUsuario = t;
	}
	
	public void setDni(int d) {
		this.dni = d;
	}
	
	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public int getEstado() {
		return this.estado;
	}
	
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getTipoUsuario() {
		return this.tipoUsuario;
	}
	
	public int getDni() {
		return this.dni;
	}
	
	public int getIdUsuario() {
		return this.idUsuario;
	}
	//Metodos propios
	
	public void mostrarDatos() {
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("|                     DATOS DEL USUARIO                            |");
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("|                                                                  |");
		System.out.println("|                                                                  |");
		System.out.println("|   Nombre de Usuario: " +this.nombreUsuario);
		System.out.println("|   Nombre y Apellido: " +this.nombre +" " +this.apellido);
		System.out.println("|   DNI: "+this.dni);
		System.out.println("|   email: "+this.mail);
		System.out.println("|   Usuario de tipo: " +determinarTipoUsuario(this.tipoUsuario));
		System.out.printf("|   Estado: ");
		if(this.estado == 1) System.out.println("Al día");
		else System.out.println("Moroso");
		System.out.println("+------------------------------------------------------------------+");
		
		
	}

	public String determinarTipoUsuario(int t) {
		switch (t){
			case 1: return "Administrador";
			case 2: return "Usuario registrado";
			case 3: return "Usuario de baja / suspendido";
			default : return "Invitado";
		}
	}

	public void cargarUsuario(int tipo) {
		try (Scanner teclado = new Scanner(System.in)) {
			
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                         NUEVO USUARIO                            |");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|                                                                  |");
			System.out.println("|                                                                  |");
			System.out.printf("   Ingrese nombre de Usuario: ");
			this.nombreUsuario = teclado.nextLine();
			System.out.printf("   Ingrese contraseña: ");
			this.password = teclado.nextLine();
			System.out.printf("   Ingrese su Nombre: ");
			this.nombre = teclado.nextLine();
			System.out.printf("   Ingrese su Apellido: ");
			this.apellido = teclado.nextLine();
			System.out.printf("   Ingrese su DNI: ");
			this.dni = teclado.nextInt();
			System.out.printf("   Ingrese su  dirección de correo electronico: ");
			this.mail = teclado.next();
			this.tipoUsuario = tipo;
			this.estado = 1;
			
		}
	}
}
