<%-- 
    Document   : Popular
    Created on : 17 nov 2024, 22:29:17
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <link rel="stylesheet" href="./estilos/estiloPopular.css">
    <title>The Music Hub - Popular</title>
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
                    <h2>@ricardoalann</h2>
                    <h3>Jenni Rivera ha regreado???</h3>
                    <p>He visto unas fotos donde se observa a una persona muy similar a la silueta de Jenni...</p>
                </article>
            </a>

            <a href="">
                <article>
                    <img src="" alt="">
                    <h2>@licoreeee</h2>
                    <h3>¿Qué opinan de "XQ ERES ASÍ"?</h3>
                    <p>¿Qué opinan del nuevo sencillo de Álvaro Díaz "XQ ERES ASÍ"? A mi la verdad me gustó mucho...</p>
                </article>
            </a>

            <a href="PostNormalUsuarioNormal.html">
                <article>
                    <img src="" alt="">
                    <h2>@pipucate</h2>
                    <h3>Taylor Swift tu patrona</h3>
                    <p>Les estaré mostrando porque Taylor Swift es una de las mejores cantantes en la actualidad...</p>
                </article>
            </a>

            <button>
                <a href="NuevoPost.html">+</a>
            </button>
        </main>
    </div>
</body>
</html>
