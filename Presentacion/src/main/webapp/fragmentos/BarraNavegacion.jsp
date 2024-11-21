<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Barra de Navegación</title>
        <link rel="stylesheet" href="../estilos/estiloEstructura.css"/>
    </head>
    <body>
        <aside>
            <nav>
                <div class="secciones">
                    <ul>
                        <li><a href="Inicio.jsp"><img src="./imagenes/white-home-icon.png" alt=""/>Inicio</a></li>
                        <li><a href="General.jsp?tipo=Reviews"><img src="./imagenes/white-review-icon.png" alt="">Reviews</a></li>
                        <li><a href="General.jsp?tipo=Popular"><img src="./imagenes/white-popular-icon.png" alt="">Popular</a></li>
                        <li><a href="General.jsp?tipo=Noticias"><img src="./imagenes/white-news-icon.png" alt="">Noticias</a></li>
                        <li><a href="General.jsp?tipo=Playlists"><img src="./imagenes/white-playlist-icon.png" alt="">Playlists</a></li>
                        <li><a href="General.jsp?tipo=General"><img src="./imagenes/white-general-icon.png" alt="">General</a></li>
                    </ul>
                </div>
                <div class="cerrar-sesion">
                    <form action="CerrarSesion" method="POST">
                        <input type="hidden" name="atributo" value="valorDeseado"/>
                        <input type="submit" value="Cerrar Sesi&#243;n" class="cerrar-sesion-button"/>
                    </form>
                </div>
            </nav>
        </aside>
    </body>
</html>