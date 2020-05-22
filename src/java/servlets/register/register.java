/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.register;

import main.Exception.UserAlreadyExistsException;
import main.Exception.AttributesRequirementsException;
import main.Exception.UserRegisterException;
import main.usuarios.ListaUsuarios;
import main.usuarios.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.usuarios.TipoUsuario;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class register extends HttpServlet {

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
        ListaUsuarios usuariosRegistrados =  (ListaUsuarios) application.getAttribute("usuariosRegistrados");
        try {
            String nickname = getParameter(request, "nickname", usuariosRegistrados);
            String nombre = getParameter(request, "nombre", usuariosRegistrados);
            String apellidos = getParameter(request, "apellidos", usuariosRegistrados);
            String correo = getParameter(request, "correo", usuariosRegistrados);
            String contrasenya = getParameter(request, "contrasenya", usuariosRegistrados);
            
            usuariosRegistrados.mete(new Usuario(nickname, correo, nombre, apellidos, contrasenya, TipoUsuario.NORMAL));
            request.setAttribute("messageCorrect", "Registrado correctamente.");
            application.getRequestDispatcher("/html/login.jsp").forward(request, response);
        } catch (UserRegisterException | AttributesRequirementsException | UserAlreadyExistsException ex) {
            request.setAttribute("messageError", ex.getMessage());
            application.getRequestDispatcher("/html/register.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Mandamos el mensaje de error al jsp donde se mostrará
            request.setAttribute("messageError", "Error al consultar la base de datos. Inténtelo más tarde");
            application.getRequestDispatcher("/html/register.jsp").forward(request, response);
        }
        
    }
    
    private String getParameter(HttpServletRequest request, String texto, ListaUsuarios usuariosRegistrados) 
            throws UserRegisterException, AttributesRequirementsException, UserAlreadyExistsException, SQLException{
        String parameter = request.getParameter(texto);
        
        if(parameter.isEmpty()) {
            throw new UserRegisterException("nombre de usuario");
        }
        
        if(texto.equals("correo")){
            parameter = getCorreo("El e-mail", parameter);
            if(usuariosRegistrados.yaExisteNickNameOrCorreo(parameter)){
                throw new UserAlreadyExistsException(texto);
            }
        } else if(texto.equals("nickname")){
            parameter = getNickName("El nombre de usuario", parameter);
            if(usuariosRegistrados.yaExisteNickNameOrCorreo(parameter)){
                throw new UserAlreadyExistsException(texto);
            }
        } else if(texto.equals("contrasenya")){
            parameter = getContrasenya("La contraseña", parameter);
        }
        
        return parameter;
    }
    
    private String getCorreo(String texto, String parameter) throws AttributesRequirementsException{
        
        if (!Pattern.matches(Usuario.PATRON_CORREO, parameter)) {
            throw new AttributesRequirementsException(texto);
        }
        
        return parameter;   
    }
    
    private String getNickName(String texto, String parameter) throws AttributesRequirementsException{
        
        if (!Pattern.matches(Usuario.PATRON_NICKNAME, parameter)) {
            throw new AttributesRequirementsException(texto);
        }
        
        return parameter;   
    }
    
    private String getContrasenya(String texto, String parameter) throws AttributesRequirementsException{
        
        if (!Pattern.matches(Usuario.PATRON_CONTRASENYA, parameter)) {
            throw new AttributesRequirementsException(texto);
        }
        
        return parameter;   
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
