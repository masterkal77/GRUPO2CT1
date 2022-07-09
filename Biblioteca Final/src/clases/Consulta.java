package clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Consulta {

	Conexion conn = new Conexion();
	Connection usarConexion = null;
	Statement stm = null;
	ResultSet rs = null;
	//ResultSet rs2 = null;
	PreparedStatement ps;

	//constructores
	public Consulta() {
		
	}
	
	//métodos propios
	
	////////////////////////////////////////METODOS PARA LOS LIBROS/////////////////////////////////////////////////////
	
	public void mostrarLibros(int tipoUsuario) {
		//muestra todo el catálogo disponible según el tipo de usuario que consulta
		try {
			usarConexion = conn.conectar(); 
			String sql = "SELECT L.id_Libro AS ID, L.titulo AS Titulo, L.descripcion AS Descripcion, L.paginas AS Paginas, A.nombre AS Autor FROM libro as L INNER JOIN autor as A ON L.id_Autor = A.id_Autor;";
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(sql); 
			ArrayList<Libro> listaLibro = new ArrayList<Libro>();
			
			
		
			if (tipoUsuario == 1 || tipoUsuario == 2) {
				
				while (rs.next()) {
					Libro libro = new Libro();
					libro.setIdLibro(rs.getInt(1));
					libro.setTitulo(rs.getString(2));
					libro.SetDescripcion(rs.getString(3));
					libro.setLinkDescarga(rs.getString(4));
					libro.setCantPaginas(rs.getInt(5));
					//libro.setAutor(rs.getString(6));
					listaLibro.add(libro);
				}
			//System.out.println("ID | Nombre | Descripcion | Cantidad de Páginas | Autor | Link Descarga");
			System.out.printf("|%-20s|%-20s|%-20s,|%-20s|%-20s|%-20s\n","ID", "Nombre", "Descripcion", "Cantidad de Paginas", "Autor", "Link Descarga \n");
			for (int i=0; i < listaLibro.size(); i++) {
				Libro libro = new Libro();
				libro = listaLibro.get(i);
				System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|\n", libro.getIdLibro(),libro.getTitulo(),libro.getDescripcion(),libro.getCantPaginas(),libro.getLinkDescarga());
				}
			} 
		else if (tipoUsuario == 3) {
			ResultSet rs2 = stm.executeQuery(sql);
			rs2 = stm.executeQuery(sql);
						
			  System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
			  System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|\n","ID", "Nombre", "Descripcion", "Pag.", "Autor");
			  System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
			  
			  while (rs2.next()) {
					int id = rs2.getInt("ID");
					String titulo = rs2.getString("Titulo");
					String desc = rs2.getString("Descripcion");
					int pag = rs2.getInt("Paginas");
					String autor = rs2.getString ("Autor");
					System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|\n", id, titulo, desc, pag, autor);
			
					}
			  System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
			 
			
		} 
		else if (tipoUsuario == 4) {
			System.out.println("Usuario de baja o suspendido");
		}
			}catch (Exception e) {
			System.out.println("Ocurrio un error inesperado, intente de nuevo" +e);
		}
	}
	
	public int existeUsuario(String usuario) {
		int resultado = 0;
		usarConexion = conn.conectar();
		String consulta = "SELECT * FROM usuario WHERE nombreUsuario = '"+usuario+"'";
		
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
	
	public void insertarUsuario(Usuario user) {
		//actualiza la BD con la información proporcionada
		try {
			usarConexion = conn.conectar(); 
			String consulta = "INSERT INTO usuario (nombreUsuario, contrasenia, tipoUsuario, nombre, apellido, dni, email, estado) VALUES (?,?,?,?,?,?,?,?)";
			ps = usarConexion.prepareStatement(consulta);
			ps.setObject(1, user.getNombreUsuario());
			ps.setObject(2, user.getPassword());
			ps.setObject(3, user.getTipoUsuario());
			ps.setObject(4, user.getNombre());
			ps.setObject(5, user.getApellido());
			ps.setObject(6, user.getDni());
			ps.setObject(7, user.getMail());
			ps.setObject(8, user.getEstado());
			ps.executeUpdate();
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|            Se agregó correctamente el usuario                    |");
			
		}catch (Exception e) {
			System.out.println("Ocurrio un error inesperado"+ " "+e);
		}
	}
	
	public int determinarTUsuario(String usuario) {
		int resultado = 0;
		usarConexion = conn.conectar();
		String consulta = "SELECT tipoUsuario AS tU FROM usuario WHERE nombreUsuario = '"+usuario+"'";
		
		try {
			
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta);
			if (rs.next()) {
				
				resultado = rs.getInt("tU");
				
			}
		} catch (Exception error) {
			System.out.println("Error inesperado");
			System.out.println(error); 
		}
		return resultado;
	}
}
