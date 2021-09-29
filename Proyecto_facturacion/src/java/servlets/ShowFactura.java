/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import datos.DBArticulo;
import datos.DBCliente;
import datos.DBDetalle_fac;
import datos.DBFactura;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Factura;

/**
 *
 * @author User
 */
public class ShowFactura extends HttpServlet {

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
        DBFactura facDB = new DBFactura();
        DBArticulo artDB = new DBArticulo();
        DBDetalle_fac detDB = new DBDetalle_fac();
        ResultSet res1 =  null;
        ResultSet res2 =  null;
        Factura fac= new Factura();
        ResultSet res3 =  null;
        try (PrintWriter out = response.getWriter()) {
            res1 = facDB.getFacturaById_usuario(Integer.parseInt(request.getParameter("id_cliente")));
            res2 = detDB.getDetalle_facById_factura(Integer.parseInt(request.getParameter("id_factura")));
            res3 = artDB.getArticulos();
            while(res1.next()){
                if(res1.getString("id_factura").equals(request.getParameter("id_factura"))){
                    fac.setFecha_fac(res1.getString("fecha_fac"));
                    fac.setId_cliente(Integer.parseInt(res1.getString("id_cliente")));
                    fac.setId_factura(Integer.parseInt(res1.getString("id_factura")));
                    fac.setId_metodo_pago(Integer.parseInt(res1.getString("id_metodo_pago")));
                    fac.setTotal(Double.parseDouble(res1.getString("total")));
                    fac.setVal_iva(Double.parseDouble(res1.getString("val_iva")));
                    fac.setVal_sub_total(Double.parseDouble(res1.getString("val_sub_total")));
                    break;
                }
            }
            request.getSession().setAttribute("factura", fac);
            request.getSession().setAttribute("detalles", res2);
            request.getSession().setAttribute("articulos", res3);
            request.getSession().setAttribute("id_cliente", request.getParameter("id_cliente"));
            response.sendRedirect("UserInterface/Theme/FacturaIndi.jsp");
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
