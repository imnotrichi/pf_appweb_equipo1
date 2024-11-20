<%-- 
    Document   : Post
    Created on : 13 nov 2024, 20:49:34
    Author     : Equipo1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./estilos/estiloEstructura.css">
        <link rel="stylesheet" href="./estilos/estiloPost.css">

        <title>The Music Hub - Post</title>
    </head>

    <body>
        <%@include file="./fragmentos/Encabezado.xhtml"%>

        <div class="contenedor">
            <%@include file="./fragmentos/BarraNavegacion.xhtml"%>

            <main>
                <section>
                    <article class="post">
                        <img class="imagen-post" src="imagenes/taylor.png" alt="">
                        <div>
                            <h2 class="usuario-fecha">@${nombreUsuario} - <span>10/10/2024</span></h2>
                        </div>
                        <h1>${titulo}</h1> 
                        <h2>${subtitulo}</h2> 
                        <p>${contenido}</p> 
                    </article>


                    <article class="contenedor-comentario">
                        <h3 class="subtitulo-comentario">Comentarios (3):</h3>
                        <hr>
                        <div class="comentario">
                            <p><span class="usuario">@abel_sanchez123 (14/10/2024): </span>yo la verdad estoy muy emocionado
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis assumenda fugit error
                                quasi accusantium dolores nobis aspernatur ullam, vitae earum! Fugiat iure rem doloremque ad
                                labore amet quas ut enim!
                            </p>
                            <button class="responder-btn">Responder</button>

                        </div>

                        <div class="comentario">
                            <p><span class="usuario">@ricardoalan (14/10/2024): </span>yo la verdad estoy muy emocionado
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis assumenda fugit error
                                quasi accusantium dolores nobis aspernatur ullam, vitae earum! Fugiat iure rem doloremque ad
                                labore amet quas ut enim!
                            </p>
                            <button class="responder-btn">Responder</button>

                        </div>

                        <div class="comentario">
                            <p><span class="usuario">@pipucate (14/10/2024): </span>yo la verdad estoy muy emocionado
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis assumenda fugit error
                                quasi accusantium dolores nobis aspernatur ullam, vitae earum! Fugiat iure rem doloremque ad
                                labore amet quas ut enim!
                            </p>
                            <button class="responder-btn">Responder</button>
                            <div class="respuestas">
                                <div class="comentario respuesta">
                                    <p><span class="usuario">@ricardoalan (14/10/2024): </span>Estoy de acuerdo contigo, pero Lorem ipsum dolor sit amet consectetur adipisicing elit. Earum totam, assumenda molestiae dolore unde porro cum. Numquam vel officiis aperiam, ducimus eius ipsa voluptas autem necessitatibus nisi. Ut, rerum iste.</p>
                                    <button class="responder-btn">Responder</button>
                                </div>

                                <div class="comentario respuesta">
                                    <p><span class="usuario">@pipucate (14/10/2024): </span>Muy buen punto</p>
                                    <button class="responder-btn">Responder</button>
                                </div>
                            </div>

                        </div>

                        <div class="comentar">
                            <textarea placeholder="Escribe un comentario..."></textarea>
                            <button class="boton-comentar">
                                Comentar
                            </button>
                        </div>
                    </article>
                </section>

            </main>
        </div>
    </body>
</html>
