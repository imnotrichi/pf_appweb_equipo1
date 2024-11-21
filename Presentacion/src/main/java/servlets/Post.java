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
        String id = request.getParameter("id");
        try {
            System.out.println("HOLA DESDE SERVLET POST");
            PostDTO post = accesoDatos.obtenerPostID(Long.parseLong(id));
            request.setAttribute("titulo", post.getTitulo());
            request.setAttribute("nombreUsuario", post.getUsuario().getNombreUsuario());
            request.setAttribute("subtitulo", post.getSubtitulo());
            request.setAttribute("contenido", post.getContenido());
            request.setAttribute("imagenPost", post.getImagen());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            String fechaFormateada = dateFormat.format(post.getFechaHoraCreacion().getTime());
            request.setAttribute("fechaPublicacion", fechaFormateada);

            if (post.getComentarios() != null && !post.getComentarios().isEmpty()) {
                request.setAttribute("cantidadComentarios", post.getComentarios().size());

                // Filtrar solo los comentarios principales (aquellos que no son respuestas)
                List<ComentarioBean> comentariosPrincipales = new LinkedList<>();
                for (ComentarioDTO comentario : post.getComentarios()) {
                    if (comentario.getRespuesta().getId() == null) {
                        // Es un comentario principal, agregarlo a la lista
                        comentariosPrincipales.add(convertirComentario(comentario, dateFormat));
                    } else {
                        // Es una respuesta, asignar al comentario padre
                        agregarRespuestaAComentario(comentariosPrincipales, comentario, dateFormat);
                    }
                }

                request.setAttribute("comentarios", comentariosPrincipales);
            }

            request.getRequestDispatcher("/Post.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el post.");
        }

    }

    /**
     * Convierte un comentario DTO en un objeto ComentarioBean
     */
    private ComentarioBean convertirComentario(ComentarioDTO comentarioDTO, SimpleDateFormat dateFormat) {
        String fechaComentario = dateFormat.format(comentarioDTO.getFechaHora().getTime());
        List<ComentarioBean> respuestas = null;
        if (comentarioDTO.getRespuestas() != null && !comentarioDTO.getRespuestas().isEmpty()) {
            respuestas = convertirComentarios(comentarioDTO.getRespuestas(), dateFormat);
        }
        return new ComentarioBean(
                comentarioDTO.getUsuario().getNombreUsuario(),
                fechaComentario,
                comentarioDTO.getContenido(),
                respuestas
        );
    }

    /**
     * Asocia una respuesta a su comentario principal.
     */
    private void agregarRespuestaAComentario(List<ComentarioBean> comentariosPrincipales, ComentarioDTO respuestaDTO, SimpleDateFormat dateFormat) {
        // Buscar el comentario principal al que pertenece la respuesta
        for (ComentarioBean comentarioBean : comentariosPrincipales) {
            if (comentarioBean.getContenido().equals(respuestaDTO.getRespuesta().getId())) {
                // Agregar la respuesta al comentario padre
                comentarioBean.getRespuesta().add(convertirComentario(respuestaDTO, dateFormat));
                break;
            }
        }
    }

    /**
     * Convierte una lista de comentarios DTO en una lista de ComentarioBean.
     */
    private List<ComentarioBean> convertirComentarios(List<ComentarioDTO> comentariosDTO, SimpleDateFormat dateFormat) {
        List<ComentarioBean> comentariosBean = new LinkedList<>();
        for (ComentarioDTO comentario : comentariosDTO) {
            // Solo agregar los comentarios que no son respuestas
            if (comentario.getRespuesta().getId()== null) {
                comentariosBean.add(convertirComentario(comentario, dateFormat));
            }
        }
        return comentariosBean;
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
