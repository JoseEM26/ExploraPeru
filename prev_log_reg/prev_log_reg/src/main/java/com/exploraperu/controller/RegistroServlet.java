package com.exploraperu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exploraperu.mantenimientos.GestionUsuario;
import com.exploraperu.model.Usuario;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet(name = "registro", urlPatterns = { "/registro" })
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Crear un objeto Usuario basado en los parámetros recibidos del formulario
			Usuario u = new Usuario();
			u.setNombre(request.getParameter("nombre"));
			u.setCorreo(request.getParameter("correo"));
			u.setClave(request.getParameter("clave"));
			u.setTelefono(request.getParameter("telefono"));
			u.setFechaCumple(java.sql.Date.valueOf(request.getParameter("cumple")));
			u.setIdRol(1);

			// Llamar a la clase de gestión para registrar al usuario
			int ok = new GestionUsuario().registrar(u);

			// Verificar si el registro fue exitoso y enviar el mensaje correspondiente
			if (ok != 0) {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Usuario registrado exitosamente!',icon: 'success'});</script>");
			} else {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Error al registrar el usuario!',icon: 'error'});</script>");
			}
		} catch (Exception e) {
			// Capturar errores y mostrar un mensaje de error
			request.setAttribute("mensaje",
					"<script>Swal.fire({title: 'Aviso!',text: 'Valores vacíos o erróneos!',icon: 'error'});</script>");
		}

		request.getRequestDispatcher("inicio.jsp").forward(request, response);
	}
}
