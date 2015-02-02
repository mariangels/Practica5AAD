<%-- 
    Document   : insert
    Created on : 01-feb-2015, 19:43:33
    Author     : Mariangeles
--%>

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
        <h2>Insert</h2>
        <form action="Control" method="POST" enctype="multipart/form-data">
            Usuario: <input type="text" name="usuario" value="" />
            <br><br>
            Localidad: <input type="text" name="localidad" value="" />
            <br><br>
            Calle: <input type="text" name="calle" value="" />
            <br><br>
            Numero: <input type="text" name="numero" value="" />
            <br><br>
            Tipo: <input type="text" name="tipo" value="" />
            <br><br>
            Precio: <input type="text" name="precio" value="" />
            <br><br>
            Imagen:  <input type="file" name="archivo" />
            <br><br>
            
            
            
            <input type="hidden" name="target" value="prueba" />
            <input type="hidden" name="op" value="insert" />
            <input type="hidden" name="action" value="op" />
            <input type="submit" value="Insertar" />
        </form>
        <h2><a href="Control?target=prueba&op=select&action=view">Volver</a></h2>
    </body>
</html>
