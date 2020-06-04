<%-- 
    Document   : login
    Created on : 13-may-2020, 0:13:58
    Author     : Santiago Naranjo Marcillo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="main.usuarios.TipoUsuario" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="author" content="Santiago Naranjo Marcillo">
    <meta name="description" content="InfinitySoft">
    <meta name="keywords" content="InfinitySoft, work, trabajo, 
              Web design, diseño web, web hosting, alojamiento web, ecommerce, comercio electronico">
    <link rel="shortcut icon" type="img/ico" href="${pageContext.request.contextPath}/media/img/ico/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <title>INFINITYSOFT</title>
</head>

<body>
    <main class="main">
        <header>
            <nav>
                <ul class="menu">
                    <div class="logo" id="container_logo">
                        <a href="#">
                            <img id="logo_img" src="${pageContext.request.contextPath}/media/img/png/Logo.png" alt="Logo">
                        </a>
                        <div>
                            <h1>INFINITYSOFT</h1>
                            <div onmousedown="mDown(this)" onmouseup="mUp(this)">
                                <h2>Tú haces que sea infinito</h2>
                            </div>
                        </div>
                    </div>
                    <li class="item"><a href="${pageContext.request.contextPath}/index.jsp">INICIO</a></li>
                        <li class="item"><a href="#">SUSCRIPCIONES</a></li>
                        <li class="item"><a href="${pageContext.request.contextPath}/html/productos.jsp">PLANTILLA</a></li>
                        <li class="item"><a href="${pageContext.request.contextPath}/html/contact.jsp">CONTACTO</a></li>
                        <li class="item"><a href="#">SOBRE NOSOTROS</a></li>
                        <li class="item"><a href="${pageContext.request.contextPath}/html/blog.jsp">BLOG</a>
                        <c:if test="${sessionScope.usuario.getTipo() eq TipoUsuario.ADMINISTRADOR}">
                            <li class="item"><a href="${pageContext.request.contextPath}/html/manageUsers.jsp">ADMINISTRAR</a></li>
                        </c:if>
                        </li>
                        <c:if test="${sessionScope.usuario eq null}">
                            <li class="item button"><a href="${pageContext.request.contextPath}/html/login.jsp">ENTRAR</a></li>
                            <li class="item button secondary"><a href="${pageContext.request.contextPath}/html/register.jsp">REGISTRARSE</a></li>
                            </c:if>
                            <c:if test="${sessionScope.usuario ne null}">
                            <li class="item button"><a href="${pageContext.request.contextPath}/html/login.jsp">${sessionScope.usuario.getNickName()}</a></li>
                            <li class="item button secondary"><a href="${pageContext.request.contextPath}/html/closeSession.jsp">CERRAR SESIÓN</a></li>
                            </c:if>
                        <li class="toggle"><a href="#" onclick="myFunction()"><i id="icon" class="fas fa-bars"></i></a></li>
                    </ul>
                </nav>
        </header>
            <div class="login">
                <c:if test="${sessionScope.usuario ne null}">
                    <c:redirect url="/index.jsp" context="/InfinitySoft"></c:redirect>
                </c:if>
                <form id="container_login" action="login" method="POST">
                    <c:if test="${not empty messageError}">
                        <div class="Error">
                            <p id="error">${messageError}</p>
                        </div>
                    </c:if>
                    <c:if test="${not empty messageCorrect}">
                        <div class="Error">
                            <p id="correcto">${messageCorrect}</p>
                        </div>
                    </c:if>
                    <c:remove scope="session" var="messageError"/>
                    <c:remove scope="session" var="messageCorrect"/>
                    <p>Nombre de usuario o e-mail:</p>
                    <input type="text" id="nombre" name="nombreCorreo" required/>
                    <p>Contraseña:</p>
                    <input type="password" name="contrasenya" id="contrasenya" required/><br/>
                    <input type="submit" id="send" value="Entrar"/><br/>
                </form>
            </div>
            <div class="social_media">
                <article>
                    <h2>CONTACTO</h2>
                    <p>Contacto: 66666666 – 96000000<br>Correo: info@infinitysoft.com<br>Dirreción: Madrid, Av.
                        Puerta de Hierro</p>
                    <p>Ubicación:</p>
                    <div class="iframe-container">
                        <iframe
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3036.299174356977!2d-3.7363057841482465!3d40.446514879361565!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd42282ea6a1047d%3A0xed212bebe104472c!2sAv.%20Puerta%20de%20Hierro%2C%2028040%20Madrid!5e0!3m2!1ses!2ses!4v1579864583378!5m2!1ses!2ses"></iframe>
                    </div>
                </article>
                <article>
                    <h2>COMUNIDAD</h2>
                    <p>INFINITYSOFT Blog</p>
                    <p>INFINITYSOFT Arena</p>
                    <p>Facebook</p>
                    <p>Twitter</p>
                    <p>Pinterest</p>
                    <p>YouTube</p>
                    <p>LinkedIn</p>
                    <p>Instagram</p>
                </article>
                <article>
                    <h2>AVISOS Y CONDICIONES</h2>
                    <p>Acerca de INFINITYSOFT</p>
                    <p>Términos de Uso</p>
                    <p>Términos del App Market</p>
                    <p>Política de Privacidad</p>
                    <p>Abuso</p>
                    <p>Afiliados</p>
                    <p>Actualizaciones y lanzamientos</p>
                    <p>Contáctanos</p>
                    <p>Aviso de Patente</p>
                    <p>Mapa del Sitio </p>
                </article>
                <article class="social_media_description">
                    <div class="social_media_description_title">
                        <img id="social_media_description_title_logo" src="${pageContext.request.contextPath}/media/img/png/logo.png" alt="Logo">
                        <h3>INFINITYSOFT</h3>
                    </div>
                    <div id="social_media_description_text">
                        <p>INFINITYSOFT.com es una plataforma<br> líder en desarrollo web, basada en el<br>sistema
                            "en
                            la nube", que tiene millones<br>de usuarios alrededor del mundo.<br>Ahora es más fácil
                            tener
                            una presencia<br>online profesional.</p> ​
                        <p>Promociona tu negocio, exhibe tu arte,<br> configura una tienda online o solo<br>explora
                            nuevas ideas. El creador de<br> páginas web INFINITYSOFT tiene todo lo que<br>necesitas
                            para
                            crear un sitio web<br>totalmente personalizado, gratuito y de<br>alta calidad.</p>
                        <p>&copy; 2006-2020 INFINITYSOFT.com, Inc</p>
                </article>
            </div>
            <footer class="footer">
                <p><small>INFINITYSOFT&copy; 2006-2020</small></p>
                <p><small>TEMA CREADO POR INFINITYSOFT</small></p>
            </footer>
        </main>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/animation.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/change_text.js"></script>
    </body>

</html>
