package com.ExploraPeru.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ExploraPeru.Mantenimiento.MantenimientoUsuario;
import com.ExploraPeru.model.Usuario;

/**
 * Servlet implementation class FormActUsuario
 */
@WebServlet(name = "ActualizarUsuario", urlPatterns = { "/ActualizarUsuario" })
public class FormActUsuarioServlt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormActUsuarioServlt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().getAttribute("u");

		request.getRequestDispatcher("FormActUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Usuario usuarioNew = new Usuario();
			usuarioNew.setIdUsuario(Integer.parseInt(request.getParameter("id")));
			usuarioNew.setNombreUsuario(request.getParameter("nombre"));
			usuarioNew.setEmail(request.getParameter("email"));
			usuarioNew.setContraseña(request.getParameter("contrasena"));
			usuarioNew.setTelefono(request.getParameter("telefono"));
			usuarioNew.setFechaCumpleaños(request.getParameter("fecha"));
			usuarioNew.setImg(request.getParameter("img"));

			int ok = new MantenimientoUsuario().actualizar(usuarioNew);

			if (ok != 0) {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Usuario Actualizado exitosamente!',icon: 'success'});</script>");
				request.getRequestDispatcher("Perfil.jsp").forward(request, response);
			} else {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Error al Actualizado el usuario!',icon: 'error'});</script>");
				request.getRequestDispatcher("FormActUsuario.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Error al ingresar datos en actualizar usuario");
		}

	}

}
