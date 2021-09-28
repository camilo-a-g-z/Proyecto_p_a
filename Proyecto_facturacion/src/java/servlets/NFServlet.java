/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import datos.DBCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Cliente;

/**
 *
 * @author User
 */
public class NFServlet extends HttpServlet {

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
        DBCliente empDB = new DBCliente();
        Cliente cli = new Cliente();
        ResultSet res = null;
        try (PrintWriter out = response.getWriter()) {
            res = empDB.getClienteById(Integer.parseInt(request.getParameter("id_user")));
            res.next();
            cli.setApellido(res.getString("apellido"));
            cli.setCedula(res.getString("cedula"));
            cli.setCelular(Double.parseDouble(res.getString("celular")));
            cli.setCorreo(res.getString("correo"));
            cli.setDireccion(res.getString("direccion"));
            cli.setId_ciudad(Integer.parseInt(res.getString("id_ciudad")));
            cli.setId_cliente(Integer.parseInt(res.getString("id_cliente")));
            cli.setNombre(res.getString("nombre"));
            cli.setPassword(res.getString("password"));
            
            request.getSession().setAttribute("id_user", res.getString("id_cliente"));
            request.getSession().setAttribute("user", cli);
            response.sendRedirect("ClientOrder.jsp");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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
