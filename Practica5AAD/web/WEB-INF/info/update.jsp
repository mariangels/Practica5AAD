
<%@page import="hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/estilos.css">
        <title>JSP Page</title>
    </head>
<body>
        <%
            Inmueble p = (Inmueble)request.getAttribute("inmueble");
        %>
        <h1>Inmobiliaria</h1>
        <h2>Update</h2>
        <form action="Control" method="POST" enctype="multipart/form-data">

            Usuario: <input type="text" name="usuario" value="<%= p.getUsuario() %>" />
            <br><br>
            Localidad: <input type="text" name="localidad" value="<%= p.getLocalidad() %>" />
            <br><br>
            Calle: <input type="text" name="calle" value="<%= p.getCalle() %>" />
            <br><br>
            Numero: <input type="text" name="numero" value="<%= p.getNumero() %>" />
            <br><br>
            Tipo: <input type="text" name="tipo" value="<%= p.getTipo() %>" />
            <br><br>
            Precio: <input type="text" name="precio" value="<%= p.getPrecio() %>" />
            <br><br>
            Imagen:  <input type="file" name="archivo" />
            <br><br>

            <tr>
                <img src="img/<%= p.getImagenes() %>" width="100px">
            </tr>
             
            <input type="hidden" name="target" value="prueba" />
            <input type="hidden" name="op" value="update" />
            <input type="hidden" name="action" value="op" />
            <input type="hidden" name="id" value="<%= p.getId() %>" />
            <input type="submit" value="Modificar" />
        </form>
        <h2><a href="Control?target=prueba&op=select&action=view">Volver</a></h2>
    </body>
</html>
