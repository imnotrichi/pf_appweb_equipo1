<%-- 
    Document   : General
    Created on : 17 nov 2024, 20:57:17
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <header>
        <a href="Inicio2.html" class="logo">
            <img src="./imagenes/blue-vinyl-icon.png" alt="Disco de Vinilo Azul">
            <span class="the">THE</span>
            <span class="music">MUSIC</span>
            <span class="hub">HUB</span>
        </a>
        <div class="header-derecha">
            <a href="Perfil.html" class="mi-perfil">
                <img src="imagenes/avatar.jpeg" alt="Avatar del usuario actual.">
                <p>Mi perfil</p>
            </a>
            <p>I</p>
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="text" name="buscar" placeholder="Buscar">
        </div>
    </header>

    <div class="contenedor">
        <aside>
            <%@include file="./fragmentos/BarraNavegacion.xhtml"%>
        </aside>

        <main>
            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@licoreeee</h2>
                    <h3>Qué opinan de "XQ ERES ASI"?</h3>
                    <p>Diganme su opinión.</p>
                </article>
            </a>

            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@abel_san13</h2>
                    <h3>Habrá video musical de Apple de Charli XCX????</h3>
                </article>
            </a>

            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@Pipucate</h2>
                    <h3>Ustedes qué opinan de la controversia de Chappel Roan??</h3>
                    <p>A mí no me importa la verdad, yo la sigo amando.</p>
                </article>
            </a>

            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@salammimami</h2>
                    <h3>Me gustó mucho 143 de Katy Perry y soporten.</h3>
                </article>
            </a>

            <button>
                <a href="NuevoPost.html">+</a>
            </button>
        </main>
    </div>
</body>
</html>
