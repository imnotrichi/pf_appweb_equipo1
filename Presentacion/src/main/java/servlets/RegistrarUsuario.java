/*
 * RegistrarUsuario.java
 */
package servlets;

import beans.UsuarioBean;
import com.mycompany.dto.EstadoDTO;
import com.mycompany.dto.MunicipioDTO;
import com.mycompany.dto.NormalDTO;
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
import java.util.Calendar;
import org.apache.commons.io.IOUtils;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class RegistrarUsuario extends HttpServlet {

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

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String apellido1 = "", apellido2 = "";

        String[] partes = apellidos.split(" ");

        if (partes.length == 1) {
            apellido1 = partes[0];
            apellido2 = "";
        } else if (partes.length == 2) {
            apellido1 = partes[0];
            apellido2 = partes[1];
        }
        String nombreUsuario = request.getParameter("usuario");
        String correo = request.getParameter("email");
        String contrasenia = request.getParameter("contrasena");
        String telefono = request.getParameter("telefono");
        String ciudad = request.getParameter("ciudad");
        String genero = request.getParameter("genero");

        //PROCESAMIENTO DE LA IMAGEN
        // Se crea la ruta del directorio donde se almacenarán las imagenes
        String path = request.getServletContext().getRealPath("");
        String pathGuardar = path + "avatares";
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
        //FIN PROCESAMIENTO IMAGEN

        String fechaNacimientoStr = request.getParameter("fechaNacimiento");

        Calendar fechaNacimiento = Calendar.getInstance();
        String[] dateParts = fechaNacimientoStr.split("-");
        fechaNacimiento.set(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[2]));
        EstadoDTO estado = new EstadoDTO("Sonora");
        MunicipioDTO municipio = new MunicipioDTO("Cajeme", estado);

//        UsuarioDTO usuario = new NormalDTO(nombre, apellido1, apellido2, correo, contrasenia, telefono, nombreUsuario, new byte[1], ciudad, fechaNacimiento, genero, municipio);
//        System.out.println("HOLA DESDE EL SERVLET");
//        try {
//            String tipo = "normal";
//            System.out.println("REGISTRO DE USUARIO SERVLET");
//            accesoDatos.registrarUsuario(usuario);
//            UsuarioBean bean = new UsuarioBean(nombreUsuario, correo, ciudad, new byte[1], tipo);
//            HttpSession session = request.getSession();
//            session.setAttribute("usuario", bean);
//            response.sendRedirect(request.getContextPath() + "/Inicio.jsp");
//        } catch (FacadeException e) {
//            System.out.println("EXCEPCION");
//            this.getServletContext()
//                    .getRequestDispatcher("/IniciarRegistrar.jsp")
//                    .forward(request, response);
//        }
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
