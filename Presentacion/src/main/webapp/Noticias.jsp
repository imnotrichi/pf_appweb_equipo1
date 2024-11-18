<%-- 
    Document   : Noticias
    Created on : 17 nov 2024, 22:38:54
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <link rel="stylesheet" href="./estilos/estiloNoticias.css">
    <title>The Music Hub - Noticias</title>
</head>

<body>
    <%@include file="./fragmentos/Encabezado.xhtml"%>

    <div class="contenedor">
        <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

        <main>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@pipucate</h2>
                    <h3>Black Midi se desintegran</h3>
                    <p>El pasado 10 de agosto, en un Live de Instagram, Geordie Greep anunció que la banda se
                        disolvería.</p>
                </article>
            </a>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@pipucate</h2>
                    <h3>Nuevo álbum de King Gizzard</h3>
                    <p>El sexteto australiano de rock psicodélico lanzó su vigésimo quinto album. Ésta nueva entrega nos
                        presenta un sonido similar al de Fishing For Fishies.</p>
                </article>
            </a>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@ricardoalann</h2>
                    <h3>Hola, quería anunciarles que voy a sacar un nuevo album</h3>
                    <p>El próximo mes sacaré mi nuevo album titulado "Queremos que el Richi nos baile la pelusa".</p>
                </article>
            </a>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@mintfield_official</h2>
                    <h3>Nuevo álbum!!</h3>
                    <p>Holiii, queremos anunciarles que nuestro nuevo album "Aprende a Ser: Extended" ya está en todas las plataformas de streaming.</p>
                </article>
            </a>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@pipucate</h2>
                    <h3>Aphex Twins vuelve</h3>
                    <p>El mítico compositor y DJ vuelve con su remasterización del "Selected Ambient Works Volume II" de 1994.</p>
                </article>
            </a>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@licoree</h2>
                    <h3>Björk anuncia nueva gira</h3>
                    <p>El fenómeno mundial del artpop anuncia una nueva gira mundial. Dentro de la lista de ciudades elegidas están Bacadéhuachi y Bratislava.</p>
                </article>
            </a>
            
            <%@include file="./fragmentos/BotonNuevoPost.xhtml"%>
        </main>
    </div>
</body>
</html>
