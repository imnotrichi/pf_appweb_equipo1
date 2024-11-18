<%-- 
    Document   : MiPerfil
    Created on : 17 nov 2024, 23:20:13
    Author     : ricar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloEstructura.css">
    <link rel="stylesheet" href="./estilos/estiloMiPerfil.css">
    <script src="https://kit.fontawesome.com/75561fa68d.js" crossorigin="anonymous"></script>
    <title>The Music Hub - Perfil</title>
</head>

<body>
    <%@include file="./fragmentos/Encabezado.xhtml"%>

    <div class="contenedor">
        <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

        <main>
            <div class="perfil">
                <div class="datos">
                    <img src="imagenes/avatar.jpeg" alt="">
                    <h2>@bort_crab</h2>
                    <p>Ciudad Obregón</p>
                </div>
                <p>Posts(2):</p>
            </div>
            <div>
                <button class="editar-button" onclick="location.href='Editar.html'">
                    <img src="imagenes/edit.png" alt="Ícono de editar.">
                </button>
                <a target="_blank" href="https://open.spotify.com/playlist/4PFubmBI9iohFQRCw206qs?si=282196fd25374d91">
                    <article>
                        <img src="" alt="">
                        <h2>@bort_crab</h2>
                        <h3>¡ACTUALICÉ MI PLAYLIST Y YA TIENE MÁS DE 9000 CANCIONES!</h3>
                        <p>Escuchen mi playlist, tiene un poco de mucho (no de todo) :D</p>
                    </article>
                </a>
            </div>

            <div>
                <button class="editar-button" onclick="location.href='Editar.html'">
                    <img src="imagenes/edit.png" alt="Ícono de editar.">
                </button>
                <a hef="">
                    <article>
                        <h2>@bort_crab</h2>
                        <h3>Ya escucharon el Otokotachi no Wakare de Fishmans?</h3>
                        <p>Directos de los GOD.</p>
                    </article>
                </a>
            </div>
            
            <%@include file="./fragmentos/BotonNuevoPost.xhtml"%>
        </main>
    </div>
</body>

</html>
