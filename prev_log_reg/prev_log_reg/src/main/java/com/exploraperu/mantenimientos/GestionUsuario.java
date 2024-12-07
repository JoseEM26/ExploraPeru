package com.exploraperu.mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.exploraperu.conexion.MySQLConexion;
import com.exploraperu.model.Usuario;

public class GestionUsuario {

	// validar
	public Usuario validar(String usuario, String clave) {

		Usuario u = null; // variable de control

		// proceso
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // almacenará el resultado del select
		try {
			con = MySQLConexion.getConexion();

			String sql = "SELECT * FROM tb_usuarios where correo = ? and clave = ?"; // sentencia

			pst = con.prepareStatement(sql);
			pst.setString(1, usuario);
			pst.setString(2, clave);

			rs = pst.executeQuery();

			if (rs.next()) { // lee fila x fila hasta el final
				u = new Usuario();
				// se trae los datos de la consulta
				u.setIdUsuario(rs.getInt("idUsuario"));
				u.setNombre(rs.getString("nombreUsuario"));
				u.setCorreo(rs.getString("correo"));
				u.setClave(rs.getString("clave"));
				u.setIdRol(rs.getInt("idRol"));
				u.setFechaCreacion(rs.getDate("fechaCreacion"));
				u.setFechaCumple(rs.getDate("fechaCumple"));
				u.setImg(rs.getString("img"));

			}
		} catch (Exception e) {
			System.out.println("Error en validar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}

		return u;
	}

	// registrar
	public int registrar(Usuario objUsuario) throws Exception {
		int ok = 0; // variable de control > 0 (Éxito) / == 0 (Error)
		Connection con = null; // obtener la conexión con la BD
		PreparedStatement pst = null; // prepara las sentencias a ejecutar
		try {
			con = MySQLConexion.getConexion();

			// Sentencia SQL con las columnas explícitamente especificadas
			String sql = "insert into tb_usuarios (nombre, correo, clave, telefono, id_rol, fecha_cumple, img) values (?, ?, ?, ?, ?, ?, ?)";

			// prepara la sentencia
			pst = con.prepareStatement(sql);

			// asigna los valores a los placeholders
			pst.setString(1, objUsuario.getNombre());
			pst.setString(2, objUsuario.getCorreo());
			pst.setString(3, objUsuario.getClave());
			pst.setString(4, objUsuario.getTelefono());
			pst.setInt(5, objUsuario.getIdRol());
			pst.setDate(6, objUsuario.getFechaCumple());
			pst.setString(7, objUsuario.getImg());

			// ejecuta la sentencia y devuelve el resultado
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

}
