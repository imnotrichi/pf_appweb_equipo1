package servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Equipo1
 */
@MultipartConfig
public class GuardarImagenPost extends HttpServlet {

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
        response.setContentType("application/json"); // Cambiar a JSON para la respuesta
        response.setCharacterEncoding("UTF-8");

        Map<String, String> responseBody = new HashMap<>();
        Gson gson = new Gson();

        try {
            // Ruta del directorio donde se almacenarán las imágenes
            String rutaDirectorio = getServletContext().getRealPath("/imagenesPost");
            File directorioImagenesPost = new File(rutaDirectorio);

            // Crear el directorio si no existe
            if (!directorioImagenesPost.exists()) {
                directorioImagenesPost.mkdir();
            }

            // Obtener el archivo
            Part imagen = request.getPart("imagen"); // Asegúrate de que el nombre coincida con el del FormData en JS

            if (imagen == null || imagen.getSubmittedFileName().isBlank()) {
                responseBody.put("status", "error");
                responseBody.put("message", "No se envió ninguna imagen.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(gson.toJson(responseBody));
                return;
            }

            // Guardar el archivo
            String referencia = imagen.getSubmittedFileName();
            String rutaImagen = rutaDirectorio + File.separator + referencia;
            imagen.write(rutaImagen);

            // Responder con éxito
            responseBody.put("status", "success");
            responseBody.put("message", "Imagen subida exitosamente.");
            responseBody.put("filePath", "/imagenesPost/" + referencia); // Ruta relativa para usar en la app
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(gson.toJson(responseBody));
        } catch (Exception e) {
            // Manejo de errores
            System.out.println(e.getMessage());
            responseBody.put("status", "error");
            responseBody.put("message", "Hubo un error al guardar la imagen.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson(responseBody));
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
