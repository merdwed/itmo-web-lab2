package lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AreaCheckServlet extends HttpServlet {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

    @Override
    public void init() throws ServletException {

        super.init();
        ArrayList<String> tableHead = new ArrayList<String>(Arrays.asList("время Дата и время запроса", "Координата X", "Координата Y", "Параметр R", "Результат"));
        getServletContext().setAttribute("tableHead", tableHead);
        getServletContext().setAttribute("table", new ArrayList<ArrayList<String>>());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/MainPage.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Double x=(Double)getServletContext().getAttribute("X");
        Double y=(Double)getServletContext().getAttribute("Y");
        Double r=(Double)getServletContext().getAttribute("R");
       //проверяем попадание
        String message="Вы не попали";
        PrintWriter writer = response.getWriter();
        if(x <= 0 && y >= 0 && x*x + y*y <= r*r/4)message="Вы попали!";//вторая четверть
        if(x <= 0 && y <= 0 && x + y >= -r)message="Вы попали!";//третья четверть
        if(x >= 0 && y <= 0 && x <= r && y >= -r )message="Вы попали!";//четвертая четверть
        
        //добавляем в табличку новую строку
        ArrayList<ArrayList<String>> table=(ArrayList<ArrayList<String>>)getServletContext().getAttribute("table");
        Collections.reverse(table);
        ArrayList<String> newRow = new ArrayList<String>(Arrays.asList(dtf.format(LocalDateTime.now()),x.toString(),y.toString(),r.toString(), message));
        table.add(newRow);
        Collections.reverse(table);

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<style>");
        writer.println("    .error{");
        writer.println("        color:#f00;");
        writer.println("    }");
        writer.println("    .c {");
        writer.println("     border: 1px solid #333; /* Рамка */");
        writer.println("     display: inline-block;");
        writer.println("     padding: 5px 15px; /* Поля */");
        writer.println("     text-decoration: none; /* Убираем подчёркивание */");
        writer.println("     color: #000; /* Цвет текста */");
        writer.println("   }");
        writer.println("    .c:hover {");
        writer.println("     box-shadow: 0 0 5px rgba(0,0,0,0.3); /* Тень */");
        writer.println("     background: linear-gradient(to bottom, #fcfff4, #e9e9ce); /* Градиент */");
        writer.println("     color: #a00;");
        writer.println("    }");
        writer.println("table {");
        writer.println("    width: 700px; ");
        writer.println("    border: 2px solid #000; ");
        writer.println("    color: #08f;");
        writer.println("    margin: 10px 0px 10px 0px;");
        writer.println("}");
        writer.println("td {");
        writer.println("    padding: 3px; ");
        writer.println("    text-align: center; ");
        writer.println("    border-bottom: 1px solid #000; ");
        writer.println("}");
        writer.println("   </style>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h2>"+message+"</h2>");
        writer.println("<br>");
        writer.println("<a href=\"/lab2\" class=\"c\">Вернуться на главную</a>");
        writer.println("<br>");
        writer.println("<table>");
        writer.println("<tbody>");
        writer.println("<tr>");
        for(String item: (ArrayList<String>)getServletContext().getAttribute("tableHead"))
            writer.println("<td>"+item+"</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        for(String item: newRow)
            writer.println("<td>"+item+"</td>");
        writer.println("</tr>");
        writer.println("</tbody>");
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");
    }

}