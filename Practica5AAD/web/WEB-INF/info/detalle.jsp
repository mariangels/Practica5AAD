
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
    
    <h1>Inmobiliaria</h1>
    <h2>Datos</h2>
        <table>
        <%
            Inmueble p = (Inmueble)request.getAttribute("inmueble");
            if(p.getImagenes()!=null){
        %>

            <tr>
                <img src="img/<%= p.getImagenes() %>" width="100px">
            </tr>
        <%
        }
        %>
            <tr>
                <td>ID</td>
                <td><%= p.getId()%></td>
            </tr>
            <tr>
                <td>Usuario</td>
                <td><%= p.getUsuario()%></td>
            </tr>
            <tr>
                <td>Tipo</td>
                <td><%= p.getTipo()%></td>
            </tr>
            <tr>
                <td>Direccion</td>
                <td><%= " C/ " + p.getCalle() + ", nº: " + p.getNumero() %></td>
            </tr>
            <tr>
                <td>Localidad</td>
                <td><%= p.getLocalidad()%></td>
            </tr>
            <tr>
                <td>Precio</td>
                <td><%= p.getPrecio()%></td>
            </tr>
            
        </table>
        <h2><a href="Control?target=prueba&op=select&action=view">Volver</a></h2>
    </body>
</html>
