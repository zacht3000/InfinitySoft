/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.login;

import Exception.UserNotExistException;
import Exception.ContrasenyaIncorrectaException;
import Exception.UserRegisterException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import usuarios.ListaUsuarios;
import usuarios.TipoUsuario;
import usuarios.Usuario;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class login extends HttpServlet {

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

        ServletContext application = getServletContext();
        HttpSession session = request.getSession();
        
        Usuario usuario;
        ListaUsuarios usuariosRegistrados = (ListaUsuarios) application.getAttribute("usuariosRegistrados");
        
        try {
            String nombreCorreo = getParameter(request, "nombreCorreo", usuariosRegistrados);
            String contrasenya = getParameter(request, "contrasenya", usuariosRegistrados);
            if (esCorreo(nombreCorreo)) {
                usuario = usuariosRegistrados.getBuscaUsuarioNickNameOrCorreo(nombreCorreo);
            } else{
                usuario = usuariosRegistrados.getBuscaUsuarioNickNameOrCorreo(nombreCorreo);
            }
            
            if(usuario == null){
                throw new UserNotExistException(); 
            }
            
            if(!usuariosRegistrados.esContrasenyaCorecta(usuario, contrasenya))
                throw new ContrasenyaIncorrectaException(); 
            
            
            session.setAttribute("usuario", usuario);
            response.sendRedirect("/InfinitySoft/index.jsp");
            //application.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (UserRegisterException | ContrasenyaIncorrectaException | UserNotExistException ex) {
            request.setAttribute("messageError", ex.getMessage());
            application.getRequestDispatcher("/html/login.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Mandamos el mensaje de error al jsp donde se mostrará
            request.setAttribute("messageError", "Error al consultar la base de datos. Inténtelo más tarde");
            application.getRequestDispatcher("/html/register.jsp").forward(request, response);
        }
    }

    private String getParameter(HttpServletRequest request, String texto, ListaUsuarios usuariosRegistrados) throws UserRegisterException, SQLException {
        String parameter = request.getParameter(texto);

        if (parameter.isEmpty()) {
            throw new UserRegisterException("nombre o correo");
        }

        return parameter;
    }

    private boolean esCorreo(String parameter) {
        if (Pattern.matches(Usuario.PATRON_CORREO, parameter)) {
            return true;
        }

        return false;

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
        processRequest(request, response);
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
        processRequest(request, response);
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
