package com.ExploraPeru.Mantenimiento;

import java.lang.annotation.Retention;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ExploraPeru.Connection.MySQLConnection;
import com.ExploraPeru.Interfaz.MantenimientoCrud;
import com.ExploraPeru.model.Roles;
import com.ExploraPeru.model.Usuario;

public class MantenimientoUsuario {
	public Usuario ValidacionLogin(String email,String clave) {
		Usuario u = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConnection();

			String sql = "SELECT * FROM Usuarios where email = ? and contraseña = ?"; // sentencia

			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, clave);

			rs = pst.executeQuery();

			if (rs.next()) { // if hay datos en la consulta
				u = new Usuario();
				// se trae los datos de la consulta
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setEmail(rs.getString("email"));
				u.setContraseña(rs.getString("contraseña"));
				u.setIdRol(rs.getInt("id_rol"));
				u.setFechaCreacion(rs.getString("fecha_creacion"));
				u.setFechaCumpleaños(rs.getString("fecha_cumpleaños"));
				u.setImg(rs.getString("img"));
				u.setTelefono(rs.getString("telefono"));
				
			}
		} catch (Exception e) {
			System.out.println("Error en validar : " + e.getMessage());
		} finally {
			MySQLConnection.CloseConection(con);
		}
		return u;
	}
	public int registrar(Usuario objUsuario) throws Exception {
		int ok = 0; // variable de control > 0 (Éxito) / == 0 (Error)
		Connection con = null; // obtener la conexión con la BD
		PreparedStatement pst = null; // prepara las sentencias a ejecutar
		try {
			con =MySQLConnection.getConnection();

			// Sentencia SQL con las columnas explícitamente especificadas
			String sql = "insert into Usuarios (nombre_usuario, email, contraseña, telefono, id_rol, fecha_cumpleaños, img) values (?, ?, ?, ?, ?, ?, ?)";

			// prepara la sentencia
			pst = con.prepareStatement(sql);

			// asigna los valores a los placeholders
			pst.setString(1, objUsuario.getNombreUsuario());
			pst.setString(2, objUsuario.getEmail());
			pst.setString(3, objUsuario.getContraseña());
			pst.setString(4, objUsuario.getTelefono());
			pst.setInt(5, objUsuario.getIdRol());
			pst.setString(6, objUsuario.getFechaCumpleaños());
			pst.setString(7, objUsuario.getImg());

			// ejecuta la sentencia y devuelve el resultado
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar: " + e.getMessage());
		} finally {
			MySQLConnection.CloseConection(con);
		}
		return ok;
	}

}
