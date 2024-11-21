<%-- 
    Document   : General
    Created on : 17 nov 2024, 20:57:17
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <link rel="stylesheet" href="./estilos/estiloGeneral.css">
    <title>The Music Hub - General</title>
</head>

<body>
    <%@include file="./fragmentos/Encabezado.xhtml" %>

    <div class="contenedor">
        <%@include file="./fragmentos/BarraNavegacion.xhtml" %>

        <main>
            <!-- Mostrar posts anclados -->
            <h2>Posts Anclados</h2>
            <c:forEach var="post" items="${posts}">
                <a href="Post?id=${post.getId()}">
                    <article>
                        <h2>@${post.usuario.nombreUsuario}</h2> 
                        <h3>${post.titulo}</h3> 
                        <p>${post.subtitulo}</p> 
                        <p>${post.contenido}</p> 
                    </article>
                </a>
            </c:forEach>

            <%@include file="./fragmentos/BotonNuevoPost.xhtml"%>
        </main>
    </div>
</body>
</html>
