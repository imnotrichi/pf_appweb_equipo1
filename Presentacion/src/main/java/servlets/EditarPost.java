/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.UsuarioBean;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 *
 * @author Diego Valenzuela Parra
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class EditarPost extends HttpServlet {

    private IAccesoDatosFacade accesoDatos;

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
        response.setContentType("text/html;charset=UTF-8");

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
        String idPost = request.getParameter("idPost");
        String titulo = request.getParameter("titulo");
        String subtitulo = request.getParameter("subtitulo");
        String categoria = request.getParameter("tipo-post");
        String cuerpo = request.getParameter("cuerpo");
        UsuarioDTO usuario = null;

        IAccesoDatosFacade accesoDatos = new AccesoDatosFacade();
        try {
            usuario = accesoDatos.obtenerUsuario(((UsuarioBean) request.getSession().getAttribute("usuario")).getCorreo());
        } catch (FacadeException ex) {
            Logger.getLogger(CrearPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        String rutaRelativa = "";
        //PROCESAMIENTO DE LA IMAGEN
        if (!request.getPart("imagen").getSubmittedFileName().isBlank()) {
            // Se crea la ruta del directorio donde se almacenarán las imagenes
            String rutaDirectorio = getServletContext().getRealPath("/imagenesPost");
            File directorioImagenesPost = new File(rutaDirectorio);

            // Se crea el directorio si no existe
            if (!directorioImagenesPost.exists()) {
                directorioImagenesPost.mkdir();
            }

            // Se obtiene el archivo
            Part imagen = request.getPart("imagen");

            // Se obtiene la referencia del archivo (nombre del archivo)
            String referencia = imagen.getSubmittedFileName();

            // Ruta completa donde se almacenará el archivo en el servidor
            String rutaImagen = rutaDirectorio + File.separator + referencia;

            // Se almacena el archivo en el directorio
            imagen.write(rutaImagen);

            // Guardar la ruta relativa que será accesible por la aplicación web
            rutaRelativa = "imagenesPost/" + referencia;
            request.getSession().setAttribute("imagen", rutaRelativa);
        }
        //FIN PROCESAMIENTO IMAGEN

        if (!titulo.isBlank() && !categoria.isBlank()) {
            if (subtitulo.isBlank()) {
                subtitulo = "";
            }

            try {
                PostDTO post = accesoDatos.obtenerPostID(Long.valueOf(idPost));
                post.setTitulo(titulo);
                post.setSubtitulo(subtitulo);
                post.setImagen(rutaRelativa);
                post.setCategoria(categoria);
                post.setContenido(cuerpo);

                accesoDatos.editarPost(post, usuario);
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
                System.out.println("Error al crear la publicacion");
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
