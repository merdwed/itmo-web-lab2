<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>errorPage</title>
        <style>
            .error{
                color:#f00;
            }
            .c {
             border: 1px solid #333; /* Рамка */
             display: inline-block;
             padding: 5px 15px; /* Поля */
             text-decoration: none; /* Убираем подчёркивание */
             color: #000; /* Цвет текста */
           }
            .c:hover {
             box-shadow: 0 0 5px rgba(0,0,0,0.3); /* Тень */
             background: linear-gradient(to bottom, #fcfff4, #e9e9ce); /* Градиент */
             color: #a00;
            }
           </style>
    </head>
    <body>
        <h1 class="error"><%= (String)getServletContext().getAttribute("LastError") %></h1>
        <a href="/lab2" class="c">Вернуться на главную</a>
    </body>
</html>