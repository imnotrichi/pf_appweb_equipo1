<%-- 
    Document   : Comentarios
    Created on : 20 nov 2024, 00:18:21
    Author     : Abe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="respuesta" items="${respuestas}">
    <div class="comentario respuesta">
        <p>
            <span class="usuario">@${respuesta.getNombreUsuario()} (${respuesta.getFechaHora()}): </span>
            ${respuesta.getContenido()}
        </p>
        <button class="responder-btn">Responder</button>

        <c:if test="${not empty respuesta.getRespuestas()}">
            <div class="respuestas">
                <jsp:include page="./fragmentos/Comentarios.jsp">
                    <jsp:param name="respuesta" value="${respuesta.getRespuestas()}" />
                </jsp:include>
            </div>
        </c:if>

        
    </div>
</c:forEach>
