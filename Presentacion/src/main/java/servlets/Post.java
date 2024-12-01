/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.ComentarioBean;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.PostDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;

/**
 *
 * @author Abe
 */
public class Post extends HttpServlet {

    public static int contador = 0;

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
            contador++;
            System.out.println(contador);
        
            PostDTO post = accesoDatos.obtenerPostID(Long.valueOf(id));
            System.out.println(post.getComentarios().size() + "en la vuelta" + contador);
            request.setAttribute("id", id);
            request.setAttribute("titulo", post.getTitulo());
            request.setAttribute("nombreUsuario", post.getUsuario().getNombreUsuario());
            request.setAttribute("subtitulo", post.getSubtitulo());
            request.setAttribute("contenido", post.getContenido());
            request.setAttribute("imagenPost", post.getImagen());
            request.setAttribute("tipoPost", post.getTipoPost());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = dateFormat.format(post.getFechaHoraCreacion().getTime());
            request.setAttribute("fechaPublicacion", fechaFormateada);

            if (post.getComentarios() != null && !post.getComentarios().isEmpty()) {
                request.setAttribute("cantidadComentarios", post.getComentarios().size());
                System.out.println("SE OBTUVO COMENTARIOS");

                // Procesar comentarios
                List<ComentarioBean> comentariosPrincipales = procesarComentarios(post.getComentarios(), dateFormat);

                request.setAttribute("comentarios", comentariosPrincipales);
            }

            request.getRequestDispatcher("/Post.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el post.");
        }
    }

    /**
     * Procesa la lista de comentarios, separando comentarios principales y
     * respuestas.
     */
    private List<ComentarioBean> procesarComentarios(List<ComentarioDTO> comentariosDTO, SimpleDateFormat dateFormat) {
        List<ComentarioBean> comentariosPrincipales = new LinkedList<>();
        Map<Long, ComentarioBean> mapaComentarios = new HashMap<>();

        // Convertir todos los ComentarioDTO a ComentarioBean y organizarlos por ID
        for (ComentarioDTO comentario : comentariosDTO) {
            ComentarioBean comentarioBean = convertirComentario(comentario, dateFormat);
            mapaComentarios.put(comentarioBean.getId(), comentarioBean);

            // Si el comentario no es una respuesta, es principal
            if (comentario.getRespuesta() == null) {
                comentariosPrincipales.add(comentarioBean);
            }
        }

        // Asignar respuestas a los comentarios principales
        for (ComentarioDTO comentario : comentariosDTO) {
            if (comentario.getRespuesta() != null) {
                Long idPadre = comentario.getRespuesta().getId();
                ComentarioBean comentarioPadre = mapaComentarios.get(idPadre);
                if (comentarioPadre != null) {
                    comentarioPadre.getRespuesta().add(mapaComentarios.get(comentario.getId()));
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
