/*
 * CrearPost.java
 */
package servlets;

import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.GregorianCalendar;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
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
        IAccesoDatosFacade accesoDatos = new AccesoDatosFacade();

        String titulo = request.getParameter("titulo");
        String subtitulo = request.getParameter("subtitulo");
        String tipoPost = request.getParameter("tipo-post");
        String cuerpo = request.getParameter("cuerpo");
        UsuarioDTO usuario = (NormalDTO) request.getSession().getAttribute("usuario");

        if (!titulo.isBlank() && !tipoPost.isBlank()) {
            if (subtitulo.isBlank()) {
                subtitulo = "";
            }

            try {
                ComunDTO postNuevo = new ComunDTO(new GregorianCalendar(), titulo, subtitulo, cuerpo, tipoPost, usuario);
                accesoDatos.publicarPost(postNuevo);
                
                UsuarioDTO usuarioActualizado = accesoDatos.obtenerUsuario(usuario.getCorreo(), usuario.getContrasenia());
                request.getSession().setAttribute("usuario", usuarioActualizado);
            } catch (FacadeException ex) {
                System.out.println("Error al crear la publicación");
            }

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
    }// </editor-fold>

}
