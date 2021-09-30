<%@page import="java.sql.ResultSet"%>
<%@page import="logica.Factura"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Factura fac = (Factura) session.getAttribute("factura");
    ResultSet res2 = (ResultSet) session.getAttribute("detalles");
    ResultSet res3 = (ResultSet) session.getAttribute("articulos");
    String user = (String) session.getAttribute("id_cliente");
    Double precio;
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Facturacion electronica</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <link href="assets/css/table-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a class="logo"><b>Facturacion electronica</b></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
               
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="../../index.html">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
     <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered">Facturacion electronica</h5>
              	  	
                  <li class="mt">
                      <a class="active" id="prueba">
                          <i class="fa fa-dashboard"></i>
                          <form action="../../RedirectUser">
                                <div id="info" style="display:none">
                                    <input id="id_user" name="id_cliente" type="text" value="<%out.println(user);%>">
                                </div>
                                    <button type="submit" class="btn btn-theme">Facturas realizadas</button>
                                </span>
                          </form>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-tasks"></i>
                          <span>Usuario</span>
                      </a>
                      <ul class="sub">
                          <li>
                              <form action="../../ShowUserBasicDates">
                                    <div id="info" style="display:none">
                                        <input id="id_user" name="id_cliente" type="text" value="<%out.println(user);%>">
                                    </div>
                                        <button type="submit" class="btn btn-theme">Editar informacion</button>
                                    </span>
                              </form>
                          </li>
                      </ul>
                  </li>
                  
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
          	<h4><i class="fa fa-angle-right"></i>
                    <label>Fecha factura: <b><%out.print(fac.getFecha_fac());%></b></label><br><br>
                    <label>Numero de factura: <b><%out.print(fac.getId_factura());%></b></label><br><br>
                    <label>Valor del iva: <b><%out.print(fac.getVal_iva());%></b></label><br><br>
                    <label>SubTotal: <b><%out.print(fac.getVal_sub_total());%></b></label><br><br>
                    <label>Fecha factura: <b><%out.print(fac.getTotal());%></b></label><br><br>
                </h4>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i> Articulos</h4>
                          <section id="unseen">
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>
                              <tr>
                                  <th>Nombre del articulo</th>
                                  <th class="numeric">Cantidad</th>
                                  <th class="numeric">Descuento</th>
                                  <th class="numeric">Valor del descuento</th>
                                  <th class="numeric">Valor unitario</th>
                                  <th class="numeric">Total</th>
                              </tr>
                              </thead>
                              <tbody>
                              <%while(res2.next()){%>
                                    <tr>
                                    <%while(res3.next()){
                                        if(res2.getString("id_articulo").equals(res3.getString("id_articulo"))){%>
                                            <td><%out.println(res3.getString("nombre"));%></td>
                                            <%break;
                                        }
                                    }
                                    precio = Double.parseDouble(res2.getString("total")) / Double.parseDouble(res2.getString("cantidad"));
                                    while(res3.previous()){}%>
                                        <td class="numeric"><%out.println(res2.getString("cantidad"));%></td>
                                        <td class="numeric"><%out.println(res2.getString("descuento"));%></td>
                                        <td class="numeric"><%out.println(res2.getString("val_descuento"));%></td>
                                        <td class="numeric"><%out.println(precio);%></td>
                                        <td class="numeric"><%out.println(res2.getString("total"));%></td>
                                    </tr>
                              <%}%>                              
                              </tbody>
                          </table>
                          </section>
                  </div><!-- /content-panel -->
               </div><!-- /col-lg-4 -->			
		  	</div><!-- /row -->
		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2014 - Alvarez.is
              <a href="FacturaIndivi.html" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>

    <!--script for this page-->
    

  </body>
</html>
