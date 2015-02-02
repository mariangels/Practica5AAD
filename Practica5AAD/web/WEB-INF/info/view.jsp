
<%@page import="java.util.List"%>
<%@page import="hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/estilos.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inmobiliaria</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>usuario</th>
                    <th>localidad</th>
                    <th>calle</th>
                    <th>numero</th>
                    <th>tipo</th>
                    <th>precio</th>
                    <th>imagenes</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Inmueble> lista = (List<Inmueble>)request.getAttribute("datos");
                    for(Inmueble p : lista){
                %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getUsuario()%></td>
                    <td><%= p.getLocalidad() %></td>
                    <td><%= p.getCalle() %></td>
                    <td><%= p.getNumero()%></td>
                    <td><%= p.getTipo()%></td>
                    <td><%= p.getPrecio()%></td>
                    <td><%= p.getImagenes()%></td>
                    
                    <td><a href="Control?target=prueba&op=delete&action=op&id=<%= p.getId() %>">Borrar</a></td>
                    <td><a href="Control?target=prueba&op=update&action=view&id=<%= p.getId() %>">Editar</a></td>
                    <td><a href="Control?target=prueba&op=ver&action=view&id=<%= p.getId() %>">Detalle</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        
        <h2><a href="Control?target=prueba&op=target&action=view">Insertar registro</a></h2>
        

    </body>
</html>
