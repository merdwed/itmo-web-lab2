<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<jsp:useBean id="testbean" class="lab2.HelloService" scope="page" />
<!DOCTYPE html>
<html>
	<head>               
		<title>Лабораторная работа №1 /Речкалов Михаил</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
        <link rel="stylesheet" type="text/css" href="style.css">
        
	</head>
	<body>
		<%@ include  file="header.jsp" %>
		<form action="ControllerServlet" method="post" onsubmit="return formcheck();">
    		<div class="form-input">
    		    <div>
                    <p>
    		            Координата X:
                        <input type="checkbox" name="coordX" value="-3">-3
                        <input type="checkbox" name="coordX" value="-2">-2
                        <input type="checkbox" name="coordX" value="-1">-1
                        <input type="checkbox" name="coordX" value="0" checked>0
                        <input type="checkbox" name="coordX" value="1" >1
                        <input type="checkbox" name="coordX" value="2" >2
                        <input type="checkbox" name="coordX" value="3" >3
                        <input type="checkbox" name="coordX" value="4" >4
                        <input type="checkbox" name="coordX" value="5" >5
    		        </p>
                </div>
    		    <div>
    		        Координата Y:
                    <input type="text" class="number" name="coordY" id="coordY" placeholder=" от -3 до 3"> 
    		    </div>
                <div >
                    <span>Параметр R:</span>
                    <input type="text" class="number" name="paramR" id="paramR" placeholder=" от 1 до 4">               
    		    </div>
    		    <div>
                    <p color=#f00 id="wrong-input-message" hidden="true">Вы ввели данные некорректно</p>
                    <p><input type="submit" id="submit-button"></p>
                </div> 
    		</div>
		</form>
		
		<div>
            <center>
		        <canvas id="canvas" width="400" height="400"></canvas>
            </center>
		</div>
		<table>
			<tbody>
				<tr>
				<% for(String item: (ArrayList<String>)getServletConfig().getServletContext().getAttribute("tableHead")){ %>
					<td><%= item %></td>
				<%}%>
				</tr>
				<%for(ArrayList<String> row: (ArrayList<ArrayList<String>>)getServletConfig().getServletContext().getAttribute("table")) { %>   
				<tr>
				<%	for(String item : row ){%>
					<td><%= item %></td>
				<%	}%>
				</tr>
				<%}%>
			</tbody>
		</table>
        <script src="inputCheck.js"></script>
		<script src="canvas.js"></script>
	</body>
</html>