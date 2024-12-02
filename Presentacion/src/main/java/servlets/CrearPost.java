/*
 * CrearPost.java
 */
package servlets;

import beans.UsuarioBean;
import com.google.gson.Gson;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class CrearPost extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        IAccesoDatosFacade accesoDatos = new AccesoDatosFacade();

        Gson gson = new Gson();

        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        PostDTO post = gson.fromJson(jsonBuilder.toString(), PostDTO.class);

        UsuarioDTO usuario = null;
        try {
            usuario = accesoDatos.obtenerUsuario(((UsuarioBean) request.getSession().getAttribute("usuario")).getCorreo());
        } catch (FacadeException ex) {
            Logger.getLogger(CrearPost.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el usuario.");
            return;
        }

        String rutaRelativa = "imagenesPost/" + post.getImagen();

        Map<String, String> responseBody = new HashMap<>();

        if (!post.getTitulo().isBlank() && !post.getTipoPost().isBlank()) {
            try {
                PostDTO postNuevo = new PostDTO(
                        new GregorianCalendar(),
                        post.getTitulo(),
                        post.getSubtitulo(),
                        post.getContenido(),
                        post.getTipoPost(),
                        usuario,
                        rutaRelativa
                );

                accesoDatos.publicarPost(postNuevo);

                responseBody.put("status", "success");
                responseBody.put("message", "Si se pudo crear el post");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(gson.toJson(responseBody));
            } catch (FacadeException ex) {
                responseBody.put("status", "error");
                responseBody.put("message", "No se pudo crear el post");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(gson.toJson(responseBody));
            }
        } else {
            responseBody.put("status", "error");
            responseBody.put("message", "Datos de post incompletos");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson(responseBody));
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
