/*
 * CrearPost.java
 */
package servlets;

import beans.UsuarioBean;
import com.mycompany.dto.ComunDTO;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
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
        }

        String avatar = "";
        //PROCESAMIENTO DE LA IMAGEN
        if (request.getPart("avatar") != null) {
            // Se crea la ruta del directorio donde se almacenarán las imagenes
            String path = request.getServletContext().getRealPath("");
            String pathGuardar = path + "imagenesPosts";
            File dir = new File(pathGuardar);

            // Se crea el directorio si no existe
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Se obtiene el archivo
            Part filePart = request.getPart("avatar");

            // Se obtiene la referencia del archivo (nombre del archivo)
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            InputStream fileContent = filePart.getInputStream();
            File targetFile = new File(pathGuardar + File.separator + fileName);
            Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            IOUtils.closeQuietly(fileContent);
            avatar = targetFile.toString();
        }
        //FIN PROCESAMIENTO IMAGEN

        if (!titulo.isBlank() && !tipoPost.isBlank()) {
            String imagen = "";
            if (subtitulo.isBlank()) {
                subtitulo = "";
            }

            try {
                ComunDTO postNuevo = new ComunDTO(new GregorianCalendar(), titulo, subtitulo, cuerpo, tipoPost, usuario, avatar);
                accesoDatos.publicarPost(postNuevo);
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
    }

}
