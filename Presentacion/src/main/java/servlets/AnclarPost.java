/*
 * AnclarPost.java
 */
package servlets;

import beans.UsuarioBean;
import com.mycompany.dto.AncladoDTO;
import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.PostDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
public class AnclarPost extends HttpServlet {

    private IAccesoDatosFacade accesoDatos;

    @Override
    public void init() throws ServletException {
        accesoDatos = new AccesoDatosFacade();
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

        String idPost = request.getParameter("idPost");
        Long id = Long.valueOf(idPost);
        HttpSession sesion = request.getSession();

        UsuarioBean usuario = (UsuarioBean) sesion.getAttribute("usuario");
        try {
            PostDTO post = accesoDatos.obtenerPostID(id);
            if (post instanceof ComunDTO) {
                accesoDatos.anclarPost(id, usuario.getCorreo());
            } else if (post instanceof AncladoDTO) {
                accesoDatos.desanclarPost(Long.valueOf(idPost));
            }
            // Actualizamos el post
            post = accesoDatos.obtenerPostID(Long.valueOf(idPost));
            request.setAttribute("id", idPost);
            request.setAttribute("titulo", post.getTitulo());
            request.setAttribute("nombreUsuario", post.getUsuario().getNombreUsuario());
            request.setAttribute("subtitulo", post.getSubtitulo());
            request.setAttribute("contenido", post.getContenido());
            request.setAttribute("imagenPost", post.getImagen());
            request.setAttribute("tipoPost", post.getTipoPost());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = dateFormat.format(post.getFechaHoraCreacion().getTime());
            request.setAttribute("fechaPublicacion", fechaFormateada);
            request.getRequestDispatcher("/Post.jsp").forward(request, response);
        } catch (FacadeException ex) {
            Logger.getLogger(AnclarPost.class.getName()).log(Level.SEVERE, null, ex);
        }
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
