<%-- 
    Document   : login
    Created on : 13-may-2020, 0:13:58
    Author     : Santiago Naranjo Marcillo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <body>
        <h1>Login</h1>
        <c:if test="${not empty messageCorrect}">
                    <div>${messageCorrect}</div>
        </c:if>
        <c:if test="${not empty messageError}">
                    <div>${messageError}</div>
        </c:if>                     
        <form action="login" method="POST">
            <label for="nombreCorreo">Nombre de usuario o correo: </label><input type="text" name="nombreCorreo"/><br/>
            <label for="contrasenya">Contraseña: </label><input type="password" name="contrasenya"/><br/>
            <input type="submit" value="Registrarse"/><br/>
        </form>
    </body>
</html>
