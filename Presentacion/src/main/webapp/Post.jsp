<%-- 
    Document   : Post
    Created on : 13 nov 2024, 20:49:34
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./estilos/estiloEstructura.css">
        <link rel="stylesheet" href="./estilos/estiloPost.css">

        <title>The Music Hub - Post</title>
    </head>

    <body>
        <%@include file="./fragmentos/Encabezado.xhtml"%>

        <div class="contenedor">
            <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

            <main>
                <section>
                    <article class="post">
                        <img class="imagen-post" src="${requestScope.post.getImagen()}" alt="">
                        <div>
                            <h2 class="usuario-fecha">@${requestScope.post.getUsuario().getNombreUsuario()} - <span>${requestScope.post.getFechaHora()}</span></h2>
                            <c:choose>
                                <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"administrador\")}">
                                    <div class="funciones-admin">
                                        <button>
                                            Eliminar
                                            <img src="./imagenes/white-trash-icon.png" alt="white trash icon">
                                        </button>
                                        <button>
                                            Anclar
                                            <img src="./imagenes/white-pin-icon.png">
                                        </button>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                        <h1>${requestScope.post.getTitulo()}</h1> 
                        <h2>${requestScope.post.getSubtitulo()}</h2> 
                        <p>${requestScope.post.getContenido()}</p> 
                    </article>

                    <article class="contenedor-comentario">
                        <h3 class="subtitulo-comentario">Comentarios (${fn:length(requestScope.post.getComentarios())}):</h3>
                        <hr>
                        <c:forEach var="comentario" items="${requestScope.post.getComentarios()}">
                            <div class="comentario">
                                <p>
                                    <span class="usuario">@${comentario.getUsuario().getNombreUsuario()} (${comentario.getFechaHora()}): </span>
                                    ${comentario.getContenido()}
                                </p>
                                <c:choose>
                                    <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"normal\")}">
                                        <button class="responder-btn">Responder</button>
                                    </c:when>
                                    <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"administrador\")}">
                                        <button class="responder-btn">Eliminar</button>
                                    </c:when>
                                </c:choose>

                                <c:if test="${not empty comentario.respuesta}">
                                    <div class="respuestas">
                                        <jsp:include page="Comentarios.jsp">
                                            <jsp:param name="comentarios" value="${comentario.respuesta}" />
                                        </jsp:include>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>

                        <div class="comentario">
                            <p><span class="usuario">@abel_sanchez123 (14/10/2024): </span>yo la verdad estoy muy emocionado
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis assumenda fugit error
                                quasi accusantium dolores nobis aspernatur ullam, vitae earum! Fugiat iure rem doloremque ad
                                labore amet quas ut enim!
                            </p>
                            <c:choose>
                                <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"normal\")}">
                                    <button class="responder-btn">Responder</button>
                                </c:when>
                                <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"administrador\")}">
                                    <button class="responder-btn">Eliminar</button>
                                </c:when>
                            </c:choose>
                        </div>

                        <c:choose>
                            <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"normal\")}">
                                <div class="comentar">
                                    <textarea placeholder="Escribe un comentario..."></textarea>
                                    <button class="boton-comentar">
                                        Comentar
                                    </button>
                                </div>
                            </c:when>
                        </c:choose>
                    </article>
                </section>
            </main>
        </div>
    </body>
</html>
