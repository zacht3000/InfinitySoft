<%-- 
    Document   : register
    Created on : 12-may-2020, 15:10:35
    Author     : Santiago Naranjo Marcillo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <c:if test="${not empty messageCorrect}">
                    <div>${messageCorrect}</div>
        </c:if>
        <c:if test="${not empty messageError}">
                    <div>${messageError}</div>
        </c:if>
        <c:if test="${sessionScope.usuario ne null}">
            <c:redirect url="/index.jsp" context="/InfinitySoft"></c:redirect>
        </c:if>
        <form action="register" method="POST">
            <label for="nickname">NickName: </label><input type="text" name="nickname"/><br/>
            <label for="nombre">Nombre: </label><input type="text" name="nombre"/><br/>
            <label for="apellidos">Apellidos </label><input type="text" name="apellidos"/><br/>
            <label for="correo">Correo: </label><input type="text" name="correo"/><br/>
            <label for="contrasenya">Contraseña: </label><input type="password" name="contrasenya"/><br/>
            <input type="submit" value="Registrarse"/><br/>
        </form>
    </body>
</html>
