<%-- 
    Document   : closeSession
    Created on : 14-may-2020, 13:11:33
    Author     : Santiago Naranjo Marcillo
--%>

<% 
    request.getSession().invalidate();
    application.getRequestDispatcher("/index.jsp").forward(request, response); 
%>

