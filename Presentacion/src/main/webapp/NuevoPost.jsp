<%-- 
    Document   : NuevoPost
    Created on : 17 nov 2024, 23:13:32
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloNuevoPost.css">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <title>The Music Hub - Nuevo post</title>
</head>

<body>
    <%@include file="./fragmentos/Encabezado.xhtml"%>

    <div class="contenedor">
        <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

        <main>
            <form id="nuevoPost">
                <h2>Nuevo post</h2>
                <br>
                <div>
                    <label for="titulo">Título</label>
                    <input type="text" name="titulo" id="titulo"/>
                </div>

                <div>
                    <label for="subtitulo">Subtítulo</label>
                    <input type="text" name="subtitulo" id="subtitulo"/>
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
                            <option value="general">General</option>
                            <option value="review">Review</option>
                            <option value="noticia">Noticia</option>
                            <option value="playlist">Playlist</option>
                        </select>
                    </div>
                </div>

                <div>
                    <label for="cuerpo">Cuerpo</label>
                    <textarea id="cuerpo" name="cuerpo" rows="10" cols="50" maxlength="2000"></textarea>
                </div>

                <div class="botones">
                    <button class="boton-descartar">
                        <a href="Inicio.jsp">Descartar</a>
                    </button>
                    <button type="submit" class="publicar-button">Publicar</button>
                </div>
            </form>
        </main>
    </div>
</body>

</html>
