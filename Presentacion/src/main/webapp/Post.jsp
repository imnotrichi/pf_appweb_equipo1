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
                        <img class="imagen-post" src="${pageContext.request.contextPath}/${imagenPost}" alt="">
                        <div>
                            <h2 class="usuario-fecha">@${nombreUsuario} - <span>${fechaPublicacion}</span></h2>
                            <c:choose>
                                <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"administrador\")}">
                                    <div class="funciones-admin">
                                        <a href="EliminarPost?idPost=${id}">
                                            <button>
                                                Eliminar
                                                <img src="./imagenes/white-trash-icon.png" alt="white trash icon">
                                            </button>
                                        </a>
                                        <a href="AnclarPost?idPost=${id}">
                                            <button>
                                                Anclar
                                                <img src="./imagenes/white-pin-icon.png">
                                            </button>
                                        </a>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                        <h1>${titulo}</h1> 
                        <h2>${subtitulo}</h2> 
                        <p>${contenido}</p> 
                    </article>

                    <article class="contenedor-comentario">
                        <h3 class="subtitulo-comentario">Comentarios (${cantidadComentarios}):</h3>
                        <hr>
                        <c:if test="${not empty comentarios}">
                            <c:forEach var="comentario" items="${comentarios}">
                                <div class="comentario">
                                    <p>
                                        <span class="usuario">@${comentario.nombreUsuario} (${comentario.fechaComentario}): </span>
                                        ${comentario.contenido}
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
                                            <c:forEach var="comentarioRespuesta" items="${comentario.respuesta}">
                                                <div class="comentario respuesta">
                                                    <p>
                                                        <span class="usuario">@${comentarioRespuesta.nombreUsuario} (${comentarioRespuesta.fechaComentario}): </span>
                                                        ${comentarioRespuesta.contenido}
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
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:if>

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