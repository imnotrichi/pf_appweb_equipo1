<%-- 
    Document   : Comentarios
    Created on : 20 nov 2024, 00:18:21
    Author     : Abe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="comentario" items="${comentarios}">
    <div class="comentario respuesta">
        <p>
            <span class="usuario">@${comentario.nombreUsuario} (${comentario.fechaComentario}): </span>
            ${comentario.contenido}
        </p>
        <button class="responder-btn">Responder</button>

        <c:if test="${not empty comentario.respuesta}">
            <div class="respuestas">
                <jsp:include page="./fragmentos/Comentarios.jsp">
                    <jsp:param name="comentarios" value="${comentario.respuesta}" />
                </jsp:include>
            </div>
        </c:if>

        
    </div>
</c:forEach>
