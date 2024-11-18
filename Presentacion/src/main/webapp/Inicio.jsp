<%-- 
    Document   : Inicio
    Created on : 13 nov 2024, 20:27:30
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <link rel="stylesheet" href="./estilos/estiloInicio.css">
    <script src="https://kit.fontawesome.com/75561fa68d.js" crossorigin="anonymous"></script>
    <title>The Music Hub - Inicio</title>
</head>

<body>
    <header>
        <a href="Inicio.jsp" class="logo">
            <img src="./imagenes/blue-vinyl-icon.png" alt="Disco de Vinilo Azul">
            <span class="the">THE</span>
            <span class="music">MUSIC</span>
            <span class="hub">HUB</span>
        </a>
        <div class="header-derecha">
            <a href="Perfil.html" class="mi-perfil">
                <img src="./imagenes/avatar.jpeg" alt="Avatar del usuario actual.">
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
            <a href="Post.html">
                <article>
                    <img src="./imagenes/white-pin-icon.png" alt="">
                    <h3>Nuevo Lanzamiento!</h3>
                    <p>Charli XCX lanzará su nuevo álbum este 11/10/2024.</p>
                </article>
            </a>

            <a href="">
                <article>
                    <img src="./imagenes/white-pin-icon.png" alt="">
                    <h3>The Marias en Tiny Desk!</h3>
                    <p>Una gran presentación de parte de la banda.</p>
                </article>
            </a>

            <a href="PostNormal.html">
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

            <button>
                <a href="NuevoPost.html">+</a>
            </button>
        </main>
    </div>
</body>

</html>