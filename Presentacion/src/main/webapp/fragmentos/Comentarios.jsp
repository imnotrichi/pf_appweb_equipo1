<%-- 
    Document   : Comentarios
    Created on : 20 nov 2024, 00:18:21
    Author     : Abe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="comentarioRespuesta" items="${comentarios}">
    <div class="comentario respuesta">
        <p>
            <span class="usuario">@${comentarioRespuesta.nombreUsuario} (${comentarioRespuesta.fechaComentario}): </span>
            ${comentarioRespuesta.contenido}
        </p>
        <button class="responder-btn">Responder</button>

        <h1>AUXILIO, SALVENME, YA NO QUIERO NADOTA</h1>

        
    </div>
</c:forEach>
