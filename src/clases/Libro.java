package clases;

import java.util.Scanner;

public class Libro {
	private String titulo;
	private String descripcion;
	private String autor;
	private String linkDescarga;
	private int idLibro;
	private int cantPag;
	
	//constructores
	public Libro() {
		
	}
	
	public Libro(String nom, String desc, String aut, String link, int id, int pag) {
		this.titulo = nom;
		this.descripcion = desc;
		this.autor = aut;
		this.linkDescarga = link;
		this.idLibro = id;
		this.cantPag = pag;
	}
	
	public Libro(String nom, String desc, String aut, String link, int pag) {
		this.titulo = nom;
		this.descripcion = desc;
		this.autor = aut;
		this.linkDescarga = link;
		this.cantPag = pag;
	}
	
	public Libro(Libro l) {
		this.titulo = l.titulo;
		this.descripcion = l.descripcion;
		this.autor = l.autor;
		this.linkDescarga = l.linkDescarga;
		this.idLibro = l.idLibro;
		this.cantPag = l.cantPag;
	}
	
	//metodos set y get
	
	public void setTitulo(String n) {
		this.titulo = n;
	}
	
	public void SetDescripcion(String d) {
		this.descripcion = d;
	}
	
	public void setAutor(String a) {
		this.autor = a;
	}
	
	public void setLinkDescarga(String l) {
		this.linkDescarga = l;
	}
	
	public void setIdLibro (int i) {
		this.idLibro = i;
	}
	
	public void setCantPaginas(int p) {
		this.cantPag = p;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public String getLinkDescarga() {
		return this.linkDescarga;
	}
	
	public int getIdLibro() {
		return this.idLibro;
	}
	
	public int getCantPaginas() {
		return this.cantPag;
	}
	
	//metodos propios
	
	public void mostrarDatos() {
		System.out.println("El nombre del libro es: " +this.titulo);
		System.out.println("El autor es: " +this.autor);
		System.out.println("Descripcion: " +this.descripcion);
		System.out.println("El link de descarga es: " +this.linkDescarga);
		System.out.println("El id es: "+this.idLibro);
		System.out.println("La cantidad de páginas es: "+this.cantPag);
	}
	
	public Libro crearLibro() {
		Libro nuevoLibro = new Libro();
		try (Scanner teclado = new Scanner(System.in)) {
			System.out.println("Ingrese el título del libro");
			nuevoLibro.titulo = teclado.nextLine();
			System.out.println("Ingrese una descripcion");
			nuevoLibro.descripcion = teclado.nextLine();
			System.out.println("Ingrese el auto del libro");
			nuevoLibro.autor = teclado.nextLine();
			System.out.println("Ingrese la cantidad de páginas");
			nuevoLibro.cantPag = teclado.nextInt();
		}
		return nuevoLibro;
	}
}
