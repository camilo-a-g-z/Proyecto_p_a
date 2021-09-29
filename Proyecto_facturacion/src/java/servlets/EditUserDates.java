package servlets;

import datos.DBCliente;
import datos.DBFactura;
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
public class EditUserDates extends HttpServlet {

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
        DBCliente cliDB = new DBCliente();
        Cliente cli = new Cliente();
        DBFactura facDB = new DBFactura();
        ResultSet res = null;
        try (PrintWriter out = response.getWriter()) {
            //se trae id ciudad
            res = cliDB.getClienteById(Integer.parseInt(request.getParameter("id_cliente")));
            //se carga
            res.next();
            //se cargan datos en objeto
            cli.setApellido(request.getParameter("apellido"));
            cli.setNombre(request.getParameter("nombre"));
            cli.setDireccion(request.getParameter("direccion"));
            cli.setCelular(Double.parseDouble(request.getParameter("celular")));
            cli.setCedula(request.getParameter("cedula"));
            cli.setPassword(request.getParameter("password"));
            cli.setCorreo(request.getParameter("correo"));
            cli.setId_cliente(Integer.parseInt(request.getParameter("id_cliente")));
            cli.setId_ciudad(Integer.parseInt(res.getString("id_ciudad")));
            //se modifican datos
            cliDB.modifyCliente(cli);
            //ahora se cargan los datos necesarios para redireccionar a las facturas del usuario
            res = facDB.getFacturaById_usuario(Integer.parseInt(request.getParameter("id_cliente")));
            res.next();
            request.getSession().setAttribute("facturas", res);
            request.getSession().setAttribute("id_user", res.getString("id_cliente"));
            response.sendRedirect("UserInterface/Theme/index.jsp");
            
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
