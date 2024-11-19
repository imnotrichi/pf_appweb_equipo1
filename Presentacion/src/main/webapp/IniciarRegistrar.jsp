<%-- 
    Document   : IniciarRegistrar
    Created on : 13 nov 2024, 20:08:36
    Author     : ricar
--%>

<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./estilos/estiloIniciarRegistrar.css">
    <title>Iniciar Sesión / Registrarse</title>
</head>

<body>
    <main>
        <section>
            <input type="checkbox" id="chk" aria-hidden="true">
            <div class="form-contenedor registrar-contenedor">
                <form action="RegistrarUsuario" method="POST" id="registroForm" enctype="multipart/form-data">
                    <h1>Registrarse</h1>
                    <div class="nombres campos">
                        <label for="nombre">Nombre(s)</label>
                        <input type="text" name="nombre" required/>
                    </div>

                    <div class="nombres campos">
                        <label for="apellidos">Apellidos</label>
                        <input type="text" name="apellidos" required/>
                    </div>

                    <div class="columna1 campos">
                        <label for="usuario">Usuario</label>
                        <input type="text" name="usuario"required />

                    </div>

                    <div class="columna2 campos">
                        <label for="telefono">Teléfono</label>
                        <input type="number" name="telefono" required/>
                    </div>

                    <div class="columna1 campos">
                        <label for="email">Correo electrónico</label>
                        <input type="email" name="email" required/>
                    </div>


                    <div class="columna2 campos">
                        <label for="ciudad">Ciudad</label>
                        <input type="text" name="ciudad"required />
                    </div>

                    <div class="columna1 campos">
                        <label for="contrasena">Contraseña</label>
                        <input type="password" name="contrasena"required />
                    </div>

                    <div class="columna2 campos genero">
                        <label for="genero">Género</label>
                        <select name="genero" id="genero">
                            <option value="masculino">Masculino</option>
                            <option value="femenino">Femeninio</option>
                            <option value="no binario">No binario</option>
                            <option value="otro">Otro</option>
                        </select>
                    </div>

                    <div class="columna1 campos">
                        <label for="fechaNacimiento">Fecha nacimiento</label>
                        <input type="date" name="fechaNacimiento" required/>
                    </div>

                    <div class="columna2 campos">
                        <label for="avatar">Avatar</label>
                        <label for="archivo" class="custom-file-upload">
                            Seleccionar
                            <img src="./imagenes\black-folder-icon.png" alt="" class="icono" />
                        </label>
                        <input type="file" name="avatar" id="archivo" accept="image/*" required/>
                    </div>
                    
                    <input type="submit" value="Registrarse">
                </form>
            </div>

            <div class="form-contenedor iniciar-contenedor">
                <form action="IniciarSesion" method="POST">
                    <h1>Iniciar Sesión</h1>
                    <label for="correo">correo</label>
                    <input type="text" name="correo" required>

                    <label for="contrasenia">Contraseña</label>
                    <input type="password" name="contrasenia" required>
                    
                    <input type="submit" value="Iniciar Sesión">
                </form>
            </div>

            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <div class="logo">
                            <img src="imagenes/black-vinyl-icon.png" alt="">
                            <h2>THE<span>MUSIC</span>HUB</h2>
                        </div>
                        <h3>Si ya tienes una Cuenta:</h3>
                        <label for="chk" class="switch switch-iniciar">Iniciar sesión</label>
                    </div>

                    <div class="overlay-panel overlay-right">
                        <div class="logo">
                            <img src="imagenes/black-vinyl-icon.png" alt="">
                            <h2>THE<span>MUSIC</span>HUB</h2>
                        </div>
                        <h3>¿No tienes cuenta?</h3>
                        <label for="chk" class="switch switch-registrar">Registrarse</label>
                    </div>
                </div>
            </div>
        </section>
    </main>
</body>

</html>
