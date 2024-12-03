package servlets;

/*
 * IniciarSesion.java
 */
import beans.UsuarioBean;
import com.mycompany.dto.AdministradorDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
public class IniciarSesion extends HttpServlet {

    private IAccesoDatosFacade accesoDatos;

    @Override
    public void init() throws ServletException {
        accesoDatos = new AccesoDatosFacade();
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
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");

        try {
                UsuarioDTO usuario = accesoDatos.obtenerUsuario(correo, contrasenia);
            if (usuario != null) {
                String tipo = "";
                if (usuario instanceof NormalDTO) {
                    tipo = "normal";
                } else if (usuario instanceof AdministradorDTO) {
                    tipo = "administrador";
                }
                UsuarioBean bean = new UsuarioBean(usuario.getNombreUsuario(), usuario.getCorreo(), usuario.getCiudad(), usuario.getAvatar(), tipo);
                HttpSession session = request.getSession();
                session.setAttribute("usuario", bean);
                response.sendRedirect(request.getContextPath() + "/Inicio");
            } else {
                request.setAttribute("error", "Correo o contraseña incorrectos");
                this.getServletContext().getRequestDispatcher("/IniciarRegistrar.jsp").forward(request, response);
            }
        } catch (FacadeException e) {
            System.out.println("Error al iniciar sesión");
            request.setAttribute("error", "Ocurrió un error durante el inicio de sesión");
            this.getServletContext().getRequestDispatcher("/IniciarRegistrar.jsp").forward(request, response);
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
