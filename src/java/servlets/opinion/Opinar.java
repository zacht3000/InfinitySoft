/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.opinion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.Exception.UserRegisterException;
import main.opiniones.ListaOpiniones;
import main.opiniones.Opinion;
import main.productos.ListaProductos;
import main.usuarios.ListaUsuarios;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Opinar extends HttpServlet {

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
        ListaOpiniones opinionesRegistradas = (ListaOpiniones) application.getAttribute("opinionesRegistradas");
        HttpSession session = request.getSession();
        try {
            int codProducto = Integer.parseInt(getParameter(request, "idProducto", "nombre de usuario"));
            String description = getParameter(request, "description", "comentario");
            int puntuacion = Integer.parseInt(getParameter(request, "puntuacion", "puntuacion"));
            String nickname = getParameter(request, "nickname", "nickname");
            opinionesRegistradas.mete(new Opinion(opinionesRegistradas.getCodigoSiguiente(), description, puntuacion, codProducto, nickname));
            application.getRequestDispatcher("/html/producto.jsp").forward(request, response);
        } catch (UserRegisterException ex) {
            request.setAttribute("messageError", ex.getMessage());
            application.getRequestDispatcher("/html/producto.jsp").forward(request, response);
        } catch (SQLException ex){
            request.setAttribute("messageError", ex.getMessage());
            application.getRequestDispatcher("/html/producto.jsp").forward(request, response);
        }
    }
    
     private String getParameter(HttpServletRequest request, String texto, String textoException) throws UserRegisterException, SQLException {
        String parameter = request.getParameter(texto);
        

        if (parameter == null || parameter.isEmpty()) {
            throw new UserRegisterException(textoException);
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
