package com.ExploraPeru.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ExploraPeru.Mantenimiento.MantenimientoDestinos;
import com.ExploraPeru.Mantenimiento.MantenimientoVuelos;
import com.ExploraPeru.model.Destinos;
import com.ExploraPeru.model.Vuelos;

@WebServlet(name = "crudvuelos", urlPatterns = { "/crudvuelos" })
public class CrudVuelosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CrudVuelosServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener la lista de vuelos
		List<Vuelos> lstVuelos = new MantenimientoVuelos().listado();

		// Obtener la lista de destinos
		List<Destinos> lstDestinos = new MantenimientoDestinos().listado(); // Suponiendo que tienes una clase
																			// MantenimientoDestinos

		// Pasar ambos atributos a la JSP
		request.setAttribute("lstVuelos", lstVuelos);
		request.setAttribute("lstDestinos", lstDestinos); // Agregar la lista de destinos

		// Redirigir a la JSP
		request.getRequestDispatcher("crudvuelos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("btnOpcion");

		switch (opcion) {
		case "reg":
			registrar(request, response);
			break;
		case "act":
			actualizar(request, response);
			break;
		case "eli":
			eliminar(request, response);
			break;
		default:
			break;
		}
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Vuelos v = new Vuelos();
			v.setIdVuelo(Integer.parseInt(request.getParameter("idVuelo"))); // Asignar ID del vuelo
			v.setIdDestino(Integer.parseInt(request.getParameter("idDestino"))); // Asignar ID de destino
			v.setFechaSalida(request.getParameter("fechaSalida"));
			v.setFechaLlegada(request.getParameter("fechaLlegada"));
			v.setPrecio(Double.parseDouble(request.getParameter("precio")));
			v.setPlazasDisponibles(Integer.parseInt(request.getParameter("plazasDisponibles")));
			v.setImagenUrl(request.getParameter("imagenUrl"));
			v.setLugar(request.getParameter("lugar"));

			int ok = new MantenimientoVuelos().registrar(v);

			if (ok != 0) {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Vuelo registrado!',icon: 'success'});</script>");
			} else {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Error al registrar!',icon: 'error'});</script>");
			}
		} catch (Exception e) {
			request.setAttribute("mensaje",
					"<script>Swal.fire({title: 'Aviso!',text: 'Valores vacíos o erróneos!',icon: 'error'});</script>");
		}
		request.getRequestDispatcher("crudvuelos.jsp").forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Vuelos v = new Vuelos();
			v.setIdVuelo(Integer.parseInt(request.getParameter("idVuelo"))); // Asignar ID del vuelo
			v.setIdDestino(Integer.parseInt(request.getParameter("idDestino"))); // Asignar ID de destino
			v.setFechaSalida(request.getParameter("fechaSalida"));
			v.setFechaLlegada(request.getParameter("fechaLlegada"));
			v.setPrecio(Double.parseDouble(request.getParameter("precio")));
			v.setPlazasDisponibles(Integer.parseInt(request.getParameter("plazasDisponibles")));
			v.setImagenUrl(request.getParameter("imagenUrl"));
			v.setLugar(request.getParameter("lugar"));

			int ok = new MantenimientoVuelos().actualizar(v);

			if (ok != 0) {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Vuelo actualizado!',icon: 'success'});</script>");
			} else {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Error al actualizar!',icon: 'error'});</script>");
			}
		} catch (Exception e) {
			request.setAttribute("mensaje",
					"<script>Swal.fire({title: 'Aviso!',text: 'Valores vacíos o erróneos!',icon: 'error'});</script>");
		}
		request.getRequestDispatcher("crudvuelos.jsp").forward(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Obtén el idVuelo desde la solicitud (parámetro del formulario o URL)
			int idVuelo = Integer.parseInt(request.getParameter("idVuelo")); // ID del vuelo a eliminar

			// Llamar al método eliminar de MantenimientoVuelos pasando el idVuelo
			int ok = new MantenimientoVuelos().eliminar(idVuelo);

			// Comprobar el resultado y enviar el mensaje correspondiente
			if (ok != 0) {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Vuelo eliminado!',icon: 'success'});</script>");
			} else {
				request.setAttribute("mensaje",
						"<script>Swal.fire({title: 'Aviso!',text: 'Error al eliminar!',icon: 'error'});</script>");
			}
		} catch (Exception e) {
			// Manejo de errores
			request.setAttribute("mensaje",
					"<script>Swal.fire({title: 'Aviso!',text: 'Valores vacíos o erróneos!',icon: 'error'});</script>");
		}
		// Redirigir al JSP
		request.getRequestDispatcher("crudvuelos.jsp").forward(request, response);
	}

}
