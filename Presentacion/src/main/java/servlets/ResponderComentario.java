/*
 * ResponderComentario.java
 */
package servlets;

import beans.UsuarioBean;
import com.google.gson.Gson;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.NormalDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;

/**
 * @author Equipo1
 */
@WebServlet("/ResponderComentario")
public class ResponderComentario extends HttpServlet {

    private IAccesoDatosFacade accesoDatos;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        accesoDatos = new AccesoDatosFacade();
        gson = new Gson();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Map<String, String> respuesta = new HashMap<>();

        try {
            // Obtener usuario de la sesión
            HttpSession session = request.getSession(false);
            UsuarioBean usuarioSesion = (UsuarioBean) session.getAttribute("usuario");

            NormalDTO usuarioActual = (NormalDTO) accesoDatos.obtenerUsuario(usuarioSesion.getCorreo());

            if (usuarioActual == null) {
                throw new Exception("Usuario no autenticado");
            }

            // Leer JSON del cuerpo de la solicitud
            BufferedReader reader = request.getReader();
            ComentarioDTO comentarioDTO = gson.fromJson(reader, ComentarioDTO.class);

            // Validaciones
            if (comentarioDTO == null) {
                throw new Exception("Datos de comentario inválidos");
            }

            if (comentarioDTO.getContenido() == null
                    || comentarioDTO.getContenido().trim().isEmpty()) {
                throw new Exception("El contenido del comentario no puede estar vacío");
            }

            // Completar datos del comentario
            comentarioDTO.setUsuario(usuarioActual);
            comentarioDTO.setFechaHora(Calendar.getInstance());

            // Guardar comentario
            accesoDatos.responderComentario(comentarioDTO, comentarioDTO.getRespuesta());

            // Respuesta de éxito
            respuesta.put("status", "success");
            respuesta.put("message", "Comentario agregado correctamenteeeeeeeeeeeeeeee");
            response.setStatus(HttpServletResponse.SC_CREATED);

        } catch (Exception e) {
            // Manejo de errores
            respuesta.put("status", "error");
            respuesta.put("message", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        // Enviar respuesta
        out.print(gson.toJson(respuesta));
        out.flush();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
