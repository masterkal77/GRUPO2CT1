package clases;
import java.sql.Connection;
import java.sql.Date;
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
			String consulta = "SELECT * FROM libro";
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta); 
			ArrayList<Libro> listaLibro = new ArrayList<Libro>();
 			ArrayList<Integer> listaAutor = new ArrayList<Integer>();
 			
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setIdLibro(rs.getInt(1));
				libro.setTitulo(rs.getString(2));
				libro.SetDescripcion(rs.getString(3));
				libro.setLinkDescarga(rs.getString(4));
				libro.setCantPaginas(rs.getInt(5));
				listaAutor.add(rs.getInt(6));
				listaLibro.add(libro);
		} 
			if (tipoUsuario == 1 || tipoUsuario == 2) {
				System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+------------------------------+\n");
				System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|%30s|\n","ID", "Nombre", "Descripcion", "Pag.", "Autor", "Link");
				System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+------------------------------+\n");
			for (int i=0; i < listaLibro.size(); i++) {
				Libro libro = new Libro();
				libro = listaLibro.get(i);
				System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|%30s\n", libro.getIdLibro(),libro.getTitulo(),libro.getDescripcion(),libro.getCantPaginas(),buscarAutor(listaAutor.get(i)),libro.getLinkDescarga());
				}
			System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+-------------------------------+\n");
			} 
		else if (tipoUsuario == 3) {
			System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
			System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|\n","ID", "Nombre", "Descripcion", "Pag.", "Autor");
			System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
			for (int i=0; i < listaLibro.size(); i++) {
				Libro libro = new Libro();
				libro = listaLibro.get(i);
				System.out.printf("|%-3s|%-41s|%-88s|%5s|%30s|\n", libro.getIdLibro(),libro.getTitulo(),libro.getDescripcion(),libro.getCantPaginas(),buscarAutor(listaAutor.get(i)));
				}	
			System.out.printf("+---+-----------------------------------------+----------------------------------------------------------------------------------------+-----+------------------------------+\n");
		} 
		else if (tipoUsuario == 4) {
			System.out.println("Usuario de baja o suspendido");
		}
			}catch (Exception e) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(2500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt();}
		}
	}
	public String buscarAutor(int idAutor) {
		//devuelve el nombre del autor en caso de no encontrarlo devuelve null
		String autor = new String();
		try {
			usarConexion = conn.conectar(); 
			String consulta = "SELECT nombre FROM autor WHERE id_Autor ='"+idAutor+"'";
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta); 
			if (rs.next()) {
				autor = rs.getString(1);
				}
			else autor = null;
			} catch (Exception e) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
				System.out.println("+------------------------------------------------------------------+");
				try { Thread.sleep(2500); 
				  } catch(InterruptedException ex) 
				  { Thread.currentThread().interrupt();}
		}
		return autor;
	}
	
	public int buscarIdAutor(String nombre) {
		//devuelve el id del autor en caso de no encontrarlo devuelve null
		int autor=0;
		try {
			usarConexion = conn.conectar(); 
			String consulta = "SELECT id_Autor FROM autor WHERE nombre ='"+nombre+"'";
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta); 
			if (rs.next()) {
				autor = rs.getInt(1);
				}
			else autor = 0;
			} catch (Exception e) {
				System.out.println("Ocurrio un error inesperado" + " " +e);
		}
		return autor;
	}
	
	public void crearLibro(Libro book, int idAutor) {
		try {
		usarConexion = conn.conectar();
		String sql = "INSERT INTO libro (titulo, descripcion, link, paginas, id_Autor) VALUES (?,?,?,?,?)";
		ps = usarConexion.prepareStatement(sql);
		ps.executeUpdate();
		ps.setObject(1, book.getTitulo());
		ps.setObject(2, book.getDescripcion());
		ps.setObject(3, book.getLinkDescarga());
		ps.setObject(4, book.getCantPaginas());
		ps.setObject(5, idAutor);
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("|          Se AGREGO correctamente el Libro                       |");
		} catch (Exception error) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + error);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt();}
		}
		
	}
	public void eliminarLibro(int id) {
		try{
			usarConexion = conn.conectar();
			String sql = "DELETE FROM libro WHERE `libro`.`id_Libro` = "+id;
			ps = usarConexion.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|          Se ELIMINO correctamente el Libro                       |");

		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt();}
		}
		}
	

public Libro buscarLibro(String titulo) {
	//devuelve los datos del libro que se busca en base al título ingresado en caso de no encontrarlo devuelve null
	Libro nuevoLibro = new Libro();
	try {
		usarConexion = conn.conectar(); 
		String consulta = "SELECT * FROM libro WHERE titulo='"+titulo+"'";
		stm = usarConexion.createStatement();
		rs = stm.executeQuery(consulta); 
		if (rs.next()) {
			nuevoLibro.setIdLibro(rs.getInt(1));
			nuevoLibro.setTitulo(rs.getString(2));
			nuevoLibro.SetDescripcion(rs.getString(3));
			nuevoLibro.setLinkDescarga(rs.getString(4));
			nuevoLibro.setCantPaginas(rs.getInt(5));
			nuevoLibro.setAutor(buscarAutor(rs.getInt(6)));
			} else nuevoLibro = null;
	} catch (Exception e) {
		System.out.println("+------------------------------------------------------------------+");
		System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
		System.out.println("+------------------------------------------------------------------+");
		try { Thread.sleep(2500); 
		  } catch(InterruptedException ex) 
		  { Thread.currentThread().interrupt();}
	} 
	return nuevoLibro;
}
public Libro buscarLibro(int id) {
	//devuelve los datos del libro que se busca en base del id del libro en caso de no encontrarlo devuelve null
	Libro nuevoLibro = new Libro();
	try {
		usarConexion = conn.conectar(); 
		String consulta = "SELECT * FROM libro WHERE id_libro='"+id+"'";
		stm = usarConexion.createStatement();
		rs = stm.executeQuery(consulta); 
		if (rs.next()) {
			nuevoLibro.setIdLibro(rs.getInt(1));
			nuevoLibro.setTitulo(rs.getString(2));
			nuevoLibro.SetDescripcion(rs.getString(3));
			nuevoLibro.setLinkDescarga(rs.getString(4));
			nuevoLibro.setCantPaginas(rs.getInt(5));
			nuevoLibro.setAutor(buscarAutor(rs.getInt(6)));
			} else nuevoLibro = null;
	} catch (Exception e) {
		System.out.println("Ocurrio un error inesperado" + " " + e);
	} 
	return nuevoLibro;
}

public String buscarTituloLibro(int id) {
	String titulo="";
	try {
		usarConexion = conn.conectar(); 
		String consulta = "SELECT titulo FROM libro WHERE id_libro='"+id+"'";
		stm = usarConexion.createStatement();
		rs = stm.executeQuery(consulta); 
		if (rs.next()) {
			titulo = rs.getString(1);
		}
	} catch (Exception e) {
		System.out.println("Ocurrio un error inesperado" + " " + e);
	} 
	return titulo;
}


	// ---------------------------- METODOS PARA USUARIOS --------------------------------
	
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
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + error);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt();}
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
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
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
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + error);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
		}
		return resultado;
	}
	
	public Usuario buscarUsuario(String nombre) {
		//devuelve el usuario buscado en base al nombreUsuario en caso de no encontrar devuelve null
		Usuario nuevoUsuario = new Usuario();
		
		try {
			usarConexion = conn.conectar(); 
			String consulta = "SELECT * FROM usuario WHERE nombreUsuario='"+nombre+"'";
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta); 
			if (rs.next()) {
				nuevoUsuario.setIdUsuario(rs.getInt(1));
				nuevoUsuario.setNombreUsuario(rs.getString(2));
				nuevoUsuario.setPassword(rs.getString(3));
				nuevoUsuario.setTipoUsuario(rs.getInt(4));
				nuevoUsuario.setNombre(rs.getString(5));
				nuevoUsuario.setApellido(rs.getString(6));
				nuevoUsuario.setDni(rs.getInt(7));
				nuevoUsuario.setMail(rs.getString(8));
				nuevoUsuario.setEstado(rs.getInt(9));
				} else nuevoUsuario = null;
		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt();}
		}		
		return nuevoUsuario;
	}
	public Usuario buscarUsuario(int id) {
		//devuelve el usuario buscado en base al id_Usuario en caso de no encontrar devuelve null
		Usuario nuevoUsuario = new Usuario();
		
		try {
			usarConexion = conn.conectar(); 
			String consulta = "SELECT * FROM usuario WHERE id_Usuario="+id;
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta); 
			if (rs.next()) {
				nuevoUsuario.setIdUsuario(rs.getInt(1));
				nuevoUsuario.setNombreUsuario(rs.getString(2));
				nuevoUsuario.setPassword(rs.getString(3));
				nuevoUsuario.setTipoUsuario(rs.getInt(4));
				nuevoUsuario.setNombre(rs.getString(5));
				nuevoUsuario.setApellido(rs.getString(6));
				nuevoUsuario.setDni(rs.getInt(7));
				nuevoUsuario.setMail(rs.getString(8));
				} else nuevoUsuario = null;
		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt();}
		}		
		return nuevoUsuario;
	}
	public void actualizarUsuario(Usuario user, int id) {
		//actualiza los datos del usuario en base a su id con los datos de user
		try {
			usarConexion = conn.conectar();
			String consulta ="UPDATE usuario SET nombreUsuario=?, contrasenia=?, tipoUsuario=?, nombre=?, apellido=?, dni=?, email=? WHERE id_Usuario=?";
			ps = usarConexion.prepareStatement(consulta);
			ps.setObject(1, user.getNombreUsuario());
			ps.setObject(2, user.getPassword());
			ps.setObject(3, user.getTipoUsuario());
			ps.setObject(4, user.getNombre());
			ps.setObject(5, user.getApellido());
			ps.setObject(6, user.getDni());
			ps.setObject(7, user.getMail());
			ps.setObject(8, id);
			ps.executeUpdate();
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("  Se actualizó correctamente el usuario "+user.getNombreUsuario());
		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
		}
	}
	
	public void borrarUsuario(int id) {
		//elimina el usuario cuya id sea conincidente
		try {
			String consulta = "DELETE FROM usuario WHERE Id_Usuario="+id;
			usarConexion = conn.conectar();
			ps = usarConexion.prepareStatement(consulta);
			ps.executeUpdate();
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|          Se ELIMINO correctamente el usuario                     |");

		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt();}
		}
	}
	
	public void bajaUsuario(int id) {
		try {
			String sql = "UPDATE usuario SET tipoUsuario = '4' WHERE `usuario`.`id_Usuario` ="+id;
			usarConexion = conn.conectar();
			ps = usarConexion.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|          Se DIO DE BAJA correctamente al Socio                   |");
			
		} catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("| OCURRIO UN ERROR INESPERADO ");
			System.out.println(e);
		}
	}
	
	public void historialDescargas(int idUsuario) {
		//muestra el historial de descargas en base al idUsuario
		try {
			String consulta ="SELECT * FROM descarga WHERE id_Usuario=" +idUsuario;
			Usuario user = buscarUsuario(idUsuario);
			
			System.out.printf("+--------------------+--------------------+--------------------+--------------------------------------------------+\n");
			System.out.printf("|%-20s|%-20s|%-20s|%-50s|\n","ID Descarga", "Fecha", "Usuario", "Libro");
			System.out.printf("+--------------------+--------------------+--------------------+--------------------------------------------------+\n");
			ArrayList<Integer> listaIdLibro = new ArrayList<Integer>();
			ArrayList<Integer> listaDescarga = new ArrayList<Integer>();
			ArrayList<String> listaFecha = new ArrayList<String>();
 			usarConexion = conn.conectar();
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta);
			while (rs.next()) {
				listaDescarga.add(rs.getInt(1));
				listaFecha.add(rs.getString(2));
				listaIdLibro.add(rs.getInt(4));
				}
			for (int i=0;i<listaIdLibro.size();i++) {
				String titulo = buscarTituloLibro(i);
				System.out.printf("|%-20s|%-20s|%-20s|%-50s|\n",listaDescarga.get(i), listaFecha.get(i), user.getNombreUsuario(), titulo);
			}
			System.out.printf("+--------------------+--------------------+--------------------+--------------------------------------------------+\n");
		}catch (Exception e) {
			System.out.println("Ocurrio un error inesperado"+ " "+e);
		}
	}
	
	
	public void historialCuotas(int idUsuario) {
		//muestra el historial de las cuotas en base al idUsuario
		try {
			Usuario usuario = buscarUsuario(idUsuario);
			String consulta="SELECT * FROM cuota WHERE id_Usuario=" +idUsuario;
			usarConexion = conn.conectar();
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta);
					
			System.out.println("|                                                                         |");
			System.out.println("+-------+-------+-------+---------+-------------------+-------------------+");
			System.out.printf("|%-8s|%-8s|%-8s|%-8s|%-10s|%-20s|%20s\n","ID Cuota", "Estado", "Monto", "Dia","Mes","Usuario","Nombre Usuario");
			while(rs.next()) {
				System.out.printf("|%-8s|%-8d|%-8d|%-8s|%-10s",rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(5),nombreMes(rs.getInt(6)));
				System.out.printf("|%-20s\n", usuario.getNombre() +" " + usuario.getApellido(), usuario.getNombreUsuario());
			}
		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
		}
	}
	public void moraCuotas(int idUsuario) {
		//muestra el historial de las cuotas en base al idUsuario
		try {
			Usuario usuario = buscarUsuario(idUsuario);
			String consulta="SELECT * FROM cuota WHERE id_Usuario=" +idUsuario;
			usarConexion = conn.conectar();
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta);
			int i = 0;
					
			System.out.println("|                                                                   |");
			System.out.println("+--------+--------+--------+--------+----------+--------------------+--------------------+");
			System.out.printf("|%-8s|%-8s|%-8s|%-8s|%-10s|%-20s|%20s|\n","ID Cuota", "Estado", "Monto", "Dia","Mes","Usuario","Nombre Usuario");
			System.out.println("+--------+--------+--------+--------+----------+--------------------+--------------------+");
			
			while(rs.next()) {
				if (rs.getInt(2) == 0) {
				System.out.printf("|%-8s|%-8d|%-8d|%-8s|%-10s",rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(5),nombreMes(rs.getInt(6)));
				System.out.printf("|%-20s|\n", usuario.getNombre() +" " + usuario.getApellido(), usuario.getNombreUsuario());
				i++;
				}
				if (i == 0) {
					System.out.println("   EL USUARIO '"+usuario.getNombreUsuario()+"' NO ADEUDA CUOTAS");
					}
			}
		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
		}
	}
	
	public String nombreMes(int nroMes) {
		String nombre = new String();
		
		switch (nroMes) {
			case 1:
				nombre = "Enero";
				break;
			case 2:
				nombre = "Febrero";
				break;
			case 3:
				nombre = "Marzo";
				break;
			case 4:
				nombre = "Abril";
				break;
			case 5:
				nombre = "Mayo";
				break;
			case 6:
				nombre = "Junio";
				break;
			case 7:
				nombre = "Julio";
				break;
			case 8:
				nombre = "Agosto";
				break;
			case 9:
				nombre = "Septiembre";
				break;
			case 10:
				nombre = "Octubre";
				break;
			case 11:
				nombre = "Noviembre";
				break;
			case 12:
				nombre = "Diciembre";
				break;
		}
		return nombre;
	}
	
	public boolean determinarEstadoCuota(int idUsuario) {
		//funcion que determina si un usuario está al día o no con las cuotas, true=al día, false=mora en base al estado 1= pago 0=debe
		boolean estado=false;
		try {
			String consulta ="SELECT estado FROM cuota WHERE id_Usuario=" +idUsuario +" AND mes = MONTH(CURDATE())";
			usarConexion = conn.conectar();
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta);
			if (rs.next()) {
				if (rs.getInt(1) == 1) {
					estado = true;
				}
			}
		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
		}
		return estado;
	}
	
	public void registrarCuota(int idUsuario, int monto) {
		//registra el pago de la cuota en la BD
		try {
			String consulta = "INSERT INTO cuota (estado, monto, id_Usuario, dia, mes) VALUES (?,?,?,?,?)";
			usarConexion = conn.conectar();
			ps = usarConexion.prepareStatement(consulta);
			if (!determinarEstadoCuota(idUsuario)) { //primervo vemos que no esté pagado el mes en curso
				long mili = System.currentTimeMillis();
				Date fecha = new Date(mili); 
				ps.setObject(1, 1);
				ps.setObject(2, monto);
				ps.setObject(3, idUsuario);
				ps.setObject(4, fecha.getDay() );
				ps.setObject(5, fecha.getMonth());
				ps.executeUpdate();
			}
			Usuario user = buscarUsuario(idUsuario);
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("  Se registró correctamente la cuota del usuario: " + user.getNombre() + " " +user.getApellido());
			try { Thread.sleep(1500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }

		}catch (Exception e) {
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("         OCURRIO UN ERROR INESPERADO " + e);
			System.out.println("+------------------------------------------------------------------+");
			try { Thread.sleep(2500); 
			  } catch(InterruptedException ex) 
			  { Thread.currentThread().interrupt(); }
		}
	}
	
	public int buscarIdUsuario(String usuario) {
		//busca el id de usuario en base al nombre del mismo y devuelve su id en caso de no encontrar devuelve -1
		int id=-1;
		try {
			usarConexion = conn.conectar();
			String consulta ="SELECT id_Usuario FROM usuario WHERE nombreUsuario='" +usuario +"'";
			stm = usarConexion.createStatement();
			rs = stm.executeQuery(consulta); 
			if (rs.next()) {
				id = rs.getInt(1);
			}
		}catch (Exception e) {
			System.out.println("Ocurrio un error inesperado"+ " "+e);
		}
		return id;
	}
	
}

