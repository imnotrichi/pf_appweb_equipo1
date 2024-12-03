<%-- 
    Document   : EditarPost
    Created on : 18 nov 2024, 00:31:49
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./estilos/estiloEditarPost.css">
        <link rel="stylesheet" href="./estilos/estiloEstructura.css">
        <title>The Music Hub - Editar</title>
    </head>

    <body>
        <%@include file="./fragmentos/Encabezado.xhtml"%>

        <div class="contenedor">
            <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

            <main>
                <form action="EditarPost" method="POST" enctype="multipart/form-data">
                    <h2>Editar post</h2>
                    <br>

                    <!-- Campo oculto para enviar el ID del post -->
                    <input type="hidden" name="idPost" value="${requestScope.idPost}">

                    <div>
                        <label for="titulo">Título</label>
                        <input type="text" name="titulo" value="${requestScope.titulo}">
                    </div>

                    <div>
                        <label for="subtitulo">Subtítulo</label>
                        <input type="text" name="subtitulo"
                               value="${requestScope.subtitulo}">
                    </div>

                    <div class="imagen-tipo">
                        <div class="campo">
                            <label for="archivo">Añadir imagen</label>
                            <label for="archivo" class="custom-file-upload">
                                Seleccionar
                                <img src="imagenes/black-folder-icon.png" alt="" class="icono" />
                            </label>
                            <input type="file" name="imagen" id="archivo" accept="image/*" />
                        </div>

                        <div class="campo">
                            <p><label for="tipo-post">Tipo de post</label></p>
                            <select class="custom-select" name="tipo-post" id="tipo-post">
                                <c:choose>
                                    <c:when test="${requestScope.categoria eq 'GENERAL'}">
                                        <option value="general" selected>General</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="general">General</option>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${requestScope.categoria eq 'REVIEWS'}">
                                        <option value="review" selected>Review</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="review">Review</option>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${requestScope.categoria eq 'NOTICIAS'}">
                                        <option value="noticia" selected>Noticia</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="noticia">Noticia</option>
                                    </c:otherwise>
                                </c:choose>


                                <c:choose>
                                    <c:when test="${requestScope.categoria eq 'PLAYLIST'}">
                                        <option value="playlist" selected>Playlist</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="playlist">Playlist</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>

                    <div>
                        <label for="cuerpo">Cuerpo</label>
                        <textarea id="cuerpo" name="cuerpo" rows="10" cols="50" maxlength="2000">${requestScope.cuerpo}</textarea>
                    </div>

                    <div class="botones">
                        <button class="" >
                            <a href="MiPerfil.jsp">Descartar</a>
                        </button>
                        <input type="submit" value="Editar">
                    </div>
                </form>
            </main>
        </div>
    </body>

</html>