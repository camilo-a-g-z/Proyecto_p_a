package servlets;

import datos.DBEmpleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Camilo Garcia
 */
public class Empleado extends HttpServlet {

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
        DBEmpleado empDB = new DBEmpleado();
        PrintWriter out = response.getWriter();
        ResultSet res = null;
        try {
            res = empDB.geEmpleadoByNombre(request.getParameter("Nombre"));
            out.println("<html>");
            out.println("<body>");
            if(!res.next()){
                out.println("<h1>Empleado no existe</h1>");
            }else{
                while(res.next()){
                    out.println("<h1>Bienvenido"+request.getParameter("Nombre")+"</h1>");
                }
            }
            
            out.println("</body>");
            out.println("</html>");
            //request.getSession().setAttribute("empleados", res);
            //se envia al usuario al index
            //response.sendRedirect("Empleado.jsp");
        }catch(Exception e){
            //en caso de que no se pueda establecer coneccion se muestra el error
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Error en esta parte " + e.toString() + "</h1>");
            out.println("<h1>El error esta: "+empDB.getMensaje()+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        /*try{
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Facturacion web</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Seleccione empleado: </h1>");
            while (res.next()){
                out.println(res.getString("nombre"));
                out.println("<br>");
            }
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            //en caso de que no se pueda establecer coneccion se muestra el error
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Inicio</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error en esta parte " + e.toString() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
        }finally {            
            out.close();
        }*/
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
