/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.ComentarioBean;
import beans.PostBean;
import beans.UsuarioBean;
import com.mycompany.dto.AncladoDTO;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;


/**
 *
 * @author Equipo1
 */
@WebServlet(name = "General", urlPatterns = {"/Reviews"})
public class Reviews extends HttpServlet {

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
        System.out.println("HOLA DESDE SERVLET REVIEWS");
        try {
            List<PostDTO> posts = accesoDatos.obtenerPostsPorCategoria(CategoriaPost.REVIEWS);
            
            List<PostBean> postBeans = posts.stream()
                    .map(this::toBean)
                    .collect(Collectors.toList());

            request.setAttribute("posts", postBeans);
            System.out.println("SERVLET POSTS ");
            request.getRequestDispatcher("/Reviews.jsp").forward(request, response);
        } catch (FacadeException ex) {
            Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar los posts.");
        }
    }
    
    private PostBean toBean(PostDTO dto) {
        if (dto == null) {
            return null;
        }

        // Convierte comentarios
        List<ComentarioBean> comentarios = dto.getComentarios() != null
                ? dto.getComentarios().stream().map(this::toBean).collect(Collectors.toList())
                : null;

        String tipo = "";
        if (dto instanceof ComunDTO) {
            tipo = "comun";
        } else if (dto instanceof AncladoDTO) {
            tipo = "anclado";
        }
        return new PostBean(
                dto.getId(),
                toBean(dto.getUsuario()),
                dto.getFechaHoraCreacion(),
                dto.getTitulo(),
                dto.getSubtitulo(),
                dto.getContenido(),
                dto.getCategoria(),
                dto.getImagen(),
                comentarios,tipo
        );
    }

    /**
     * Convierte un ComentarioDTO a un ComentarioBean.
     */
    private ComentarioBean toBean(ComentarioDTO dto) {
        if (dto == null) {
            return null;
        }
        return new ComentarioBean(
                dto.getId(),
                dto.getUsuario().getNombreUsuario(),
                dto.getFechaHora().getTime().toString(),
                dto.getContenido(),
                null //Aun no se pueden poner respustas saluditos
        );
    }

    /**
     * Convierte un UsuarioDTO a un UsuarioBean.
     */
    private UsuarioBean toBean(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        return new UsuarioBean(
                dto.getNombreUsuario(),
                dto.getCorreo(),
                dto.getCiudad(),
                dto.getAvatar(),
                dto.getGenero()
        );
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
    }

}
