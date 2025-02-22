/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.Exception.UserNotExistException;
import main.Exception.UserRegisterException;
import main.opiniones.ListaOpiniones;
import main.opiniones.Opinion;
import main.productos.ListaProductos;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Producto extends HttpServlet {

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
        ListaProductos productosRegistrados = (ListaProductos) application.getAttribute("productosRegistrados");
        ListaOpiniones opinionesRegistradas = (ListaOpiniones) application.getAttribute("opinionesRegistradas");
        HttpSession session = request.getSession();
        try {
            int codigo = Integer.parseInt(getParameter(request, "codProducto", "no user"));
            session.setAttribute("productoSeleccionado", productosRegistrados.getBusca(codigo));
            session.setAttribute("opinionesProducto", opinionesRegistradas.getOpionesProducto(codigo));
            response.sendRedirect("/InfinitySoft/html/producto.jsp");
        } catch (UserNotExistException | UserRegisterException ex) {
            request.setAttribute("messageError", ex.getMessage());
            application.getRequestDispatcher("/html/producto.jsp").forward(request, response);
        } catch(SQLException ex){
            request.setAttribute("messageError", ex.getMessage());
            application.getRequestDispatcher("/html/producto.jsp").forward(request, response);
        }
    }
    
    private String getParameter(HttpServletRequest request, String texto, String textoException) throws UserRegisterException, SQLException {
        String parameter = request.getParameter(texto);
        

        if (parameter.isEmpty()) {
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
