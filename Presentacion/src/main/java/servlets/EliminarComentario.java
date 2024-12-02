/*
 * EliminarComentario.java
 */
package servlets;

import beans.ComentarioBean;
import beans.UsuarioBean;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;
import static servlets.Post.contador;

/**
 * @author Equipo1
 */
public class EliminarComentario extends HttpServlet {

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
        String idComentario = request.getParameter("idComentario");
        String idPost = request.getParameter("idPost");
        HttpSession sesion = request.getSession();

        UsuarioBean usuario = (UsuarioBean) sesion.getAttribute("usuario");

        try {
            UsuarioDTO usuarioObtenido = accesoDatos.obtenerUsuario(usuario.getCorreo());

            ComentarioDTO comentario = accesoDatos.obtenerComentarioID(Long.valueOf(idComentario));

            accesoDatos.eliminarComentario(comentario, usuarioObtenido);

            PostDTO post = accesoDatos.obtenerPostID(Long.valueOf(idPost));

            System.out.println(post.getComentarios().size() + "en la vuelta" + contador);
            request.setAttribute("id", idPost);
            request.setAttribute("titulo", post.getTitulo());
            request.setAttribute("nombreUsuario", post.getUsuario().getNombreUsuario());
            request.setAttribute("subtitulo", post.getSubtitulo());
            request.setAttribute("contenido", post.getContenido());
            request.setAttribute("imagenPost", post.getImagen());
            request.setAttribute("tipoPost", post.getTipoPost());
            request.removeAttribute("idComentario");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = dateFormat.format(post.getFechaHoraCreacion().getTime());
            request.setAttribute("fechaPublicacion", fechaFormateada);

            if (post.getComentarios() != null && !post.getComentarios().isEmpty()) {
                request.setAttribute("cantidadComentarios", post.getComentarios().size());
                System.out.println("SE OBTUVO COMENTARIOS");

                // Procesar comentarios
                List<ComentarioBean> comentariosPrincipales = procesarComentarios(post.getComentarios(), dateFormat);
                System.out.println("COMENTARIOS CON BEAN");
                for (ComentarioBean comentariosPrincipale : comentariosPrincipales) {
                    System.out.println("ID comentario padre:" + comentariosPrincipale.getId());
                    for (ComentarioBean comentarioBean : comentariosPrincipale.getRespuesta()) {
                        System.out.println("-----ID comentario hijo" + comentarioBean.getId());
                    }
                }
                request.setAttribute("comentarios", comentariosPrincipales);
            }

            request.getRequestDispatcher("/Post.jsp").forward(request, response);
        } catch (FacadeException ex) {
            Logger.getLogger(EliminarPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procesa la lista de comentarios, separando comentarios principales y
     * respuestas.
     */
    private List<ComentarioBean> procesarComentarios(List<ComentarioDTO> comentariosDTO, SimpleDateFormat dateFormat) {
        List<ComentarioBean> comentariosPrincipales = new LinkedList<>();
        Map<Long, ComentarioBean> mapaComentarios = new HashMap<>();

        for (ComentarioDTO comentario : comentariosDTO) {
            if (comentario.getRespuesta() == null) {
                ComentarioBean comentarioBean = convertirComentario(comentario, dateFormat);
                mapaComentarios.put(comentarioBean.getId(), comentarioBean);
                comentariosPrincipales.add(comentarioBean);
            }
        }

        for (ComentarioDTO comentario : comentariosDTO) {
            if (comentario.getRespuestas() != null) {
                ComentarioBean comentarioPadre = mapaComentarios.get(comentario.getId());

                ComentarioBean comentarioHijo = convertirComentario(comentario, dateFormat);

                if (comentarioPadre != null) {
                    comentarioPadre.getRespuesta().add(comentarioHijo);
                }
            }
        }

        return comentariosPrincipales;
    }

    /**
     * Convierte un ComentarioDTO en un ComentarioBean.
     */
    private ComentarioBean convertirComentario(ComentarioDTO comentarioDTO, SimpleDateFormat dateFormat) {
        String fechaComentario = dateFormat.format(comentarioDTO.getFechaHora().getTime());
        return new ComentarioBean(
                comentarioDTO.getId(),
                comentarioDTO.getUsuario().getNombreUsuario(),
                fechaComentario,
                comentarioDTO.getContenido(),
                new LinkedList<>() // Inicializamos la lista de respuestas vacía
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
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
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
