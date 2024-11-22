<%-- 
    Document   : Comentarios
    Created on : 20 nov 2024, 00:18:21
    Author     : Abe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty comentarios}">
    <c:forEach var="comentarioRespuesta" items="${comentarios}">
        <div class="comentario respuesta">
            <p>
                <span class="usuario">@${comentarioRespuesta.nombreUsuario} (${comentarioRespuesta.fechaComentario}): </span>
                ${comentarioRespuesta.contenido}
            </p>
            <c:choose>
                <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"normal\")}">
                    <button class="responder-btn">Responder</button>
                </c:when>
                <c:when test="${sessionScope.usuario.getTipo().equalsIgnoreCase(\"administrador\")}">
                    <button class="responder-btn">Eliminar</button>
                </c:when>
            </c:choose>
        </div>
    </c:forEach>
</c:if>
