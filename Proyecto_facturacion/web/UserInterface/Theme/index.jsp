<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String user = (String) session.getAttribute("id_user"); 
  ResultSet res = (ResultSet) session.getAttribute("facturas");%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Facturacion Electronica</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <script src="assets/js/chart-master/Chart.js"></script>
    
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
            <h3><i class="fa fa-angle-right"></i> Pedidos realizados</h3>
            <div class="showback">
                    <h4><i class="fa fa-angle-right"></i> Nueva factura</h4>
                    <form class="form-login" action="../../NFServlet">
                        <div id="info" style="display:none">
                            <input id="id_user" name="id_user" type="text" value="<%out.println(user);%>">
                        </div>
                        <button type="submit" class="btn btn-primary btn-lg btn-block">Generar nueva factura</button>
                    </form>
            </div>
                <div class="row mt">
                    <div class="col-lg-12">
                    <div class="content-panel">
                    <h4><i class="fa fa-angle-right"></i> Facturas</h4>
                        <section id="unseen">
                          <table class="table table-bordered table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Fecha factura</th>
                                <th class="numeric">Valor del iva</th>
                                <th class="numeric">Valor Sub Total</th>
                                <th class="numeric">Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%while(res.next()){%>
                                <tr>
                                    <td><%out.println(res.getString("id_factura"));%></td>
                                    <td><%out.println(res.getString("fecha_fac"));%></td>
                                    <td class="numeric"><%out.println(res.getString("val_iva"));%></td>
                                    <td class="numeric"><%out.println(res.getString("val_sub_total"));%></td>
                                    <td class="numeric"><%out.println(res.getString("total"));%></td>
                                    <td>
                                        <form action="../../ShowFactura">
                                            <div id="info" style="display:none">
                                                <input id="id_cliente" name="id_cliente" type="text" value="<%out.println(user);%>">
                                                <input id="id_factura" name="id_factura" type="text" value="<%out.println(res.getString("id_factura"));%>">
                                                <input id="btm_submit" type="submit">
                                            </div>
                                            <button type="submit" class="btn btn-round btn-primary">Ver factura</button>
                                        </form>
                                    </td>
                                </tr>
                            <%}%>
                            </tbody>
                        </table>
                        </section>
                </div><!-- /content-panel -->
             </div><!-- /col-lg-4 -->			
            </div><!-- /row -->
      </section><! --/wrapper -->
      </section>

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2014 - Alvarez.is Autor diseño
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>	
	
	<script type="text/javascript">
        $(document).ready(function () {
        var unique_id = $.gritter.add({
            // (string | mandatory) the heading of the notification
            title: 'Bienvenido a la tu pagina favorita de compras!',
            // (string | mandatory) the text inside the notification
            text: 'Bienvenido a nuestra pagina recuerda que puede hacer nuevos pedidos oprimiendo el boton Generar nueva factura :)',
            // (string | optional) the image to display on the left
            image: 'assets/img/ui-sam.jpg',
            // (bool | optional) if you want it to fade out on its own or just sit there
            sticky: true,
            // (int | optional) the time you want it to be alive for before fading out
            time: '',
            // (string | optional) the class name you want to apply to that specific message
            class_name: 'my-sticky-class'
        });

        return false;
        });
	</script>
	
	<script type="application/javascript">
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    </script>

  </body>
</html>
