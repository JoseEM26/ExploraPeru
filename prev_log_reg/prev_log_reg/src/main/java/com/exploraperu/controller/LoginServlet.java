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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
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

		String correo = request.getParameter("correo");
		String clave = request.getParameter("clave");

		Usuario u = new GestionUsuario().validar(correo, clave);

		if (u != null) {
			request.setAttribute("mensaje", "Bienvenido");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);
			
		} else {
			request.setAttribute("mensaje", "<script>Swal.fire({title: 'Aviso!',text: 'Usuario o clave incorrecto!',icon: 'error'});</script>");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}
}
