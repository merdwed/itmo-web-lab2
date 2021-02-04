<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding='UTF-8'%>
<jsp:useBean id="huy" class="lab2.HelloService" scope="page" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>helloworld</title>
    </head>
    <body>
        <h1>Hello World!! ${huy.message} И сдесь ХУЙ</h1>
        <h1><%= (String)getServletContext().getAttribute("atr")%></h1>
    </body>
</html>