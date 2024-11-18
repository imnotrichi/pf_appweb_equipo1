<%-- 
    Document   : Reviews
    Created on : 17 nov 2024, 22:31:26
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <link rel="stylesheet" href="./estilos/estiloReviews.css">
    <title>The Music Hub - Reviews</title>
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
                    <img src="./imagenes/white-pin-icon.png" alt="">
                    <h3>Reseña álbum Eternal Sunshine</h3>
                    <p>¿Por qué Eternal Sunshine es un buen álbum?</p>
                </article>
            </a>

            <a href="">
                <article>
                    <img src="" alt="">
                    <img src="./imagenes/white-pin-icon.png" alt="">
                    <h3>¿Hit Me Hard and Soft podría ser el álbum del año?</h3>
                    <p>A continuación se estarán presentando algunos puntos importantes de porque se cree que hit...</p>
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

            <button>
                <a href="NuevoPost.html">+</a>
            </button>
        </main>
    </div>
</body>
</html>
