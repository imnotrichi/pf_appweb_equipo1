<%-- 
    Document   : EditarPost
    Created on : 18 nov 2024, 00:31:49
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form action="#">
                <h2>Editar post</h2>
                <br>
                <div>
                    <label for="titulo">Título</label>
                    <input type="text" name="titulo" value="¡ACTUALICÉ MI PLAYLIST Y YA TIENE MÁS DE 9000 CANCIONES!">
                </div>

                <div>
                    <label for="subtitulo">Subtítulo</label>
                    <input type="text" name="subtitulo"
                        value="Escuchen mi playlist, tiene un poco de mucho (no de todo) :D">
                </div>

                <div class="imagen-tipo">
                    <div class="campo">
                        <label for="archivo">Añadir imagen</label>
                        <label for="archivo" class="custom-file-upload">
                            Seleccionar
                            <img src="imagenes/black-folder-icon.png" alt="" class="icono" />
                        </label>
                        <input type="file" id="archivo" accept="image/*" />
                    </div>

                    <div class="campo">
                        <p><label for="tipo-post">Tipo de post</label></p>
                        <select class="custom-select" name="tipo-post" id="tipo-post">
                            <option value="general">General</option>
                            <option value="review">Review</option>
                            <option value="noticia">Noticia</option>
                            <option value="playlist" selected>Playlist</option>
                        </select>
                    </div>
                </div>

                <div>
                    <label for="cuerpo">Cuerpo</label>
                    <textarea id="cuerpo" name="cuerpo" rows="10" cols="50" maxlength="2000">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sit delectus iure voluptatibus molestiae qui nemo aliquam velit dolorum labore maiores quam quisquam mollitia non dolor iusto, consectetur eveniet omnis voluptate.
                    </textarea>
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