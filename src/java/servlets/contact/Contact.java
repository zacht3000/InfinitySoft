/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.contact;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.Exception.UserNotExistException;
import main.Exception.UserRegisterException;
import main.formularios.Formulario;
import main.formularios.ListaFormularios;
import main.usuarios.ListaUsuarios;
import main.usuarios.TipoUsuario;
import main.usuarios.Usuario;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Contact extends HttpServlet {

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
        HttpSession session = request.getSession();
        ServletContext application = getServletContext();
        ListaFormularios formulariosRegistrados = (ListaFormularios) application.getAttribute("formulariosRegistrados");

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String nombre, apellidos, nickname, correo, area, pregunta;
        try {
            if (session.getAttribute("usuario") == null) {
                ListaUsuarios listaUsuarios = (ListaUsuarios) application.getAttribute("usuariosRegistrados");
                Usuario usuariosAnonimo = listaUsuarios.getBuscaUsuarioNickNameOrCorreo("anonimo");
                nickname = usuariosAnonimo.getNickName();
                nombre = getParameter(request, "nombre");
                apellidos = getParameter(request, "apellidos");
                correo = usuariosAnonimo.getCorreo();
            } else {
                nickname = usuario.getNickName();
                nombre = usuario.getNombre();
                apellidos = usuario.getApellidos();
                correo = usuario.getCorreo();
            }
            area = getParameter(request, "area");
            pregunta = getParameter(request, "pregunta");
            
            formulariosRegistrados.mete(new Formulario(formulariosRegistrados.getIdSiguiente(), nickname, nombre, apellidos, correo, area, pregunta));
            
            request.setAttribute("messageCorrect", "Se ha enviado al equipo de soporte su duda, no tardaremos en respoder.");
            application.getRequestDispatcher("/html/contact.jsp").forward(request, response);
        } catch (UserRegisterException | UserNotExistException ex) {
            request.setAttribute("messageError", ex.getMessage());
            application.getRequestDispatcher("/html/contact.jsp").forward(request, response);
        }catch (SQLException ex) {
            // Mandamos el mensaje de error al jsp donde se mostrará
            request.setAttribute("messageError", "Error al consultar la base de datos. Inténtelo más tarde");
            application.getRequestDispatcher("/html/contact.jsp").forward(request, response);
        }
    }
    
    private String getParameter(HttpServletRequest request, String texto) throws UserRegisterException, SQLException {
        String parameter = request.getParameter(texto);
        

        if (parameter.isEmpty()) {
            throw new UserRegisterException(texto);
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
