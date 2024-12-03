<%-- 
    Document   : MiPerfil
    Created on : 17 nov 2024, 23:20:13
    Author     : ricar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./estilos/estiloEstructura.css">
        <link rel="stylesheet" href="./estilos/estiloMiPerfil.css">
        <script src="https://kit.fontawesome.com/75561fa68d.js" crossorigin="anonymous"></script>
        <title>The Music Hub - Perfil</title>
    </head>

    <body>
        <%@include file="./fragmentos/Encabezado.xhtml"%>

        <div class="contenedor">
            <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

            <main>
                <div class="perfil">
                    <div class="datos">
                        <img src="${pageContext.request.contextPath}/${sessionScope.usuario.getAvatar()}" alt="Avatar">
                        <h2>@${sessionScope.usuario.getNombreUsuario()}</h2>
                        <p>${sessionScope.usuario.getCiudad()}</p>
                    </div>
                    <p>Posts(${fn:length(requestScope.listaPosts)}):</p>
                </div>
                <c:forEach var="post" items="${listaPosts}">
                    <div>
                        <a class="editar-button" href="EditarDatosPost?id=${post.getId()}">
                            <img src="imagenes/edit.png" alt="Ícono de editar.">
                        </a>
                        <a href="Post?id=${post.getId()}">
                            <c:choose>
                                <c:when test="${post.imagen != null and not empty post.imagen}">
                                    <article style="background-image: linear-gradient(rgba(0, 0, 0, 0.6), rgba(111, 192, 248, 1)), url('${post.imagen}'); background-size: cover; background-position: center;">
                                </c:when>    
                                <c:otherwise>
                                    <article>
                                </c:otherwise>
                            </c:choose>            
                                <h2>@${post.usuario.nombreUsuario}</h2> 
                                <h3>${post.titulo}</h3> 
                                <p>${post.subtitulo}</p> 
                            </article>
                        </a>
                    </div>
                </c:forEach>

                <%@include file="./fragmentos/BotonNuevoPost.xhtml"%>
            </main>
        </div>
    </body>

</html>
