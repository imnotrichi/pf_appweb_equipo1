<%-- 
    Document   : MiPerfil
    Created on : 17 nov 2024, 23:20:13
    Author     : ricar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String referer = request.getHeader("referer");
    if (referer != null && !referer.isEmpty()) {
        session.setAttribute("returnTo", referer);
    }
%>
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
                        <img src="imagenes/avatar.jpeg" alt="">
                        <h2>@${sessionScope.usuario.getNombreUsuario()}</h2>
                        <p>${sessionScope.usuario.getCiudad()}</p>
                    </div>
                    <p>Posts(${fn:length(sessionScope.usuario.getPosts())}):</p>
                </div>
                <c:forEach items="${requestScope.listaPosts}" var="post">
                    <div>
                        <button class="editar-button" onclick="location.href = 'EditarPost.jsp'">
                            <img src="imagenes/edit.png" alt="Ícono de editar.">
                        </button>
                        <a target="_blank" href="https://open.spotify.com/playlist/4PFubmBI9iohFQRCw206qs?si=282196fd25374d91">
                            <article>
                                <h2>${post.getUsuario().getNombreUsuario()}</h2>
                                <h3>${post.getTitulo()}</h3>
                                <p>${post.getSubtitulo()}</p>
                            </article>
                        </a>
                    </div>
                    <tr>
                        <td>${pokemon.getNumero()}</td>
                        <td>${pokemon.getNombre()}</td>
                        <td>${pokemon.getTipo()}</td>
                        <td>${pokemon.getEvolucion()}</td>
                        <td>${pokemon.getNivelPoder()}</td>
                        <td>${pokemon.getDescripcion()}</td>
                    </tr>
                </c:forEach>

                <%@include file="./fragmentos/BotonNuevoPost.xhtml"%>
            </main>
        </div>
    </body>

</html>
