/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class MiPerfil extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HOLA, LLEGASTE AL SERVLET MI PERFIL - GET");
        try {
            List<PostDTO> listaPosts = accesoDatos.obtenerPostsPorCategoria(null);
            request.setAttribute("listaPosts", listaPosts);

            RequestDispatcher dispatcher = request.getRequestDispatcher("MiPerfil.jsp");
            dispatcher.forward(request, response);
        } catch (FacadeException e) {
            System.out.println("Error al iniciar sesión");
            request.setAttribute("error", "Ocurrió un error durante el inicio de sesión");
            this.getServletContext().getRequestDispatcher("/IniciarRegistrar.jsp").forward(request, response);
        }
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
        System.out.println("HOLA, LLEGASTE AL SERVLET MI PERFIL - POST");
        try {
            UsuarioDTO usuario = (NormalDTO) request.getSession().getAttribute("usuario");
            List<PostDTO> listaPosts = accesoDatos.obtenerPostsPorCategoria(null);
            request.setAttribute("listaPosts", listaPosts);
            if (usuario == null) {
                request.setAttribute("error", "Correo o contraseña incorrectos");
                this.getServletContext().getRequestDispatcher("/IniciarRegistrar.jsp").forward(request, response);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("MiPerfil.jsp");
            dispatcher.forward(request, response);
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
