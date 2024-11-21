/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.ComentarioBean;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;

/**
 *
 * @author Abe
 */
public class Post extends HttpServlet {

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
        String id = request.getParameter("id");
        try {
            System.out.println("HOLA DESDE SERVLET POST");
            PostDTO post = accesoDatos.obtenerPostID(Long.valueOf(id));
            post = sanitizarPost(post);
            request.setAttribute("post", post);

            request.getRequestDispatcher("/Post.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el post.");
        }

    }

    private PostDTO sanitizarPost(PostDTO post) {
        UsuarioDTO usuario = post.getUsuario();
        PostDTO postSanitizado = new PostDTO(
                post.getId(),
                post.getFechaHoraCreacion(),
                post.getTitulo(),
                post.getSubtitulo(),
                post.getContenido(),
                post.getImagen(),
                sanitizarComentarios(post.getComentarios()),
                sanitizarUsuario(usuario));
        return postSanitizado;
    }

    private NormalDTO sanitizarUsuario(UsuarioDTO usuario) {
        NormalDTO usuarioSanitizado = new NormalDTO(
                usuario.getCorreo(),
                usuario.getNombreUsuario());

        return usuarioSanitizado;
    }

    private List<ComentarioDTO> sanitizarComentarios(List<ComentarioDTO> comentarios) {
        List<ComentarioDTO> comentariosSanitizados = new ArrayList<>();
        if (comentarios == null || comentarios.isEmpty()) {
            return comentariosSanitizados;
        }
        for (ComentarioDTO comentario : comentarios) {
            comentariosSanitizados.add(sanitizarComentario(comentario));
        }

        return comentariosSanitizados;
    }

    private ComentarioDTO sanitizarComentario(ComentarioDTO comentario) {
        if (comentario == null) {
            return null;
        }
        ComentarioDTO comentarioDTO;
        comentarioDTO = new ComentarioDTO(
                comentario.getId(),
                comentario.getFechaHora(),
                comentario.getContenido(),
                sanitizarComentarios(comentario.getRespuestas()),
                (NormalDTO) sanitizarUsuario(comentario.getUsuario()));
        return comentarioDTO;
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
