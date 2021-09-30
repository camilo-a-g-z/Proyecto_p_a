package servlets;

import datos.DBCliente;
import datos.DBFactura;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import logica.Cliente;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Camilo Garcia
 */
public class ClienteServlet extends HttpServlet {

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
        //se crea conexion a base de datos de empleados
        DBCliente empDB = new DBCliente();
        PrintWriter out = response.getWriter();
        ResultSet res = null;
        ResultSet res_F = null;
        Cliente cli = new Cliente();
        DBFactura facDB = new DBFactura();
        try {
            //se llama y guardan los datos recividos segun el parametro recivido
            res = empDB.getClienteByCorreo(request.getParameter("Nombre"));
            out.println("<html>");
            out.println("<body>");
            //se consulta si la respuesta esta vacia
            if (!res.next()) {
                out.println("<meta http-equiv='refresh' content='3;URL=Cliente.jsp'>");//redirects after 3 seconds
                out.println("<p style='color:red;'>Contraseña o usuario incorrecto</p>");
            } else {
                if (res.getString("password") == null ? request.getParameter("contrasena") == null : res.getString("password").equals(request.getParameter("contrasena"))) {
                    
                    res_F = facDB.getFacturaById_usuario(Integer.parseInt(res.getString("id_cliente")));                    
                    request.getSession().setAttribute("facturas", res_F);
                    request.getSession().setAttribute("id_user", res.getString("id_cliente"));
                    
                    
                    out.println("<meta http-equiv='refresh' content='3;URL=UserInterface/Theme/index.jsp'>");//redirects after 3 seconds
                    out.println("<p style='color:red;'>Bienvenido " + res.getString("nombre") + "</p>");
                } else {
                    out.println("<meta http-equiv='refresh' content='3;URL=Empleado.jsp'>");//redirects after 3 seconds
                    out.println("<p style='color:red;'>Contraseña o usuario incorrecto</p>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            //en caso de que no se pueda establecer coneccion se muestra el error
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Error en esta parte " + e.toString() + "</h1>");
            out.println("<h1>El error esta: " + empDB.getMensaje() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
