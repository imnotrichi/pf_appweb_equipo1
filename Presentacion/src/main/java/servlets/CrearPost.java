/*
 * CrearPost.java
 */
package servlets;

import beans.UsuarioBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.GregorianCalendar;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        IAccesoDatosFacade accesoDatos = new AccesoDatosFacade();

        String titulo = request.getParameter("titulo");
        String subtitulo = request.getParameter("subtitulo");
        String tipoPost = request.getParameter("tipo-post");
        String cuerpo = request.getParameter("cuerpo");
        UsuarioDTO usuario = null;
        
        try {
            usuario = accesoDatos.obtenerUsuario(((UsuarioBean) request.getSession().getAttribute("usuario")).getCorreo());
        } catch (FacadeException ex) {
            Logger.getLogger(CrearPost.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el usuario.");
            return;
        }

        if (!titulo.isBlank() && !tipoPost.isBlank()) {
            if (subtitulo.isBlank()) {
                subtitulo = "";
            }

//            try {
//                PostDTO postNuevo = new PostDTO(new GregorianCalendar(), titulo, subtitulo, cuerpo, tipoPost, usuario, rutaRelativa);
//                accesoDatos.publicarPost(postNuevo);
//            } catch (FacadeException ex) {
//                System.out.println("Error al crear la publicacion");
//            }

            HttpSession session = request.getSession();
            String returnTo = (String) session.getAttribute("returnTo");

            if (returnTo != null) {
                session.removeAttribute("returnTo");
                response.sendRedirect(returnTo);
            } else {
                response.sendRedirect("Inicio.jsp");
            }
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
