<%-- 
    Document   : Playlists
    Created on : 17 nov 2024, 21:27:58
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <link rel="stylesheet" href="./estilos/estiloPlaylists.css">
    <title>The Music Hub - Playlists</title>
</head>

<body>
    <%@include file="./fragmentos/Encabezado.xhtml"%>

    <div class="contenedor">
        <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

        <main>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@licoreeee</h2>
                    <h3>Ya tienen disponible mi playlist para..</h3>
                    <p>Sé que fue una larga espera pero ya tienen la playlist de octubre muejeje vayan a escucharla </p>
                </article>
            </a>

            <a href="">
                <article>
                    <h2>@sunflower4</h2>
                    <h3>He hecho esta nueva playlist :)</h3>
                    <p>Aquí abajo les dejaré el link a mi playlist nueva “Sunflower” la hice con lo que escucho.</p>
                </article>
            </a>

            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@abel_sanchez123</h2>
                    <h3>No quiero que se acabe el verano brat así que les dejo la playlist que hice wachen:</h3>
                    <p></p>
                </article>
            </a>

            <%@include file="./fragmentos/BotonNuevoPost.xhtml"%>
        </main>
    </div>
</body>
</html>
