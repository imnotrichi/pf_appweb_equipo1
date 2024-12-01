package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author Equipo1
 */
public class GuardarImagenPost extends HttpServlet {

    

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
