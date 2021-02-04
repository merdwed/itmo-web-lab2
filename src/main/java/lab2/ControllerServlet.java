package lab2;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ControllerServlet extends HttpServlet {


    

    @Inject
    HelloService helloService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/MainPage.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        Double x=(Double)getServletContext().getAttribute("X");
        Double y=(Double)getServletContext().getAttribute("Y");
        Double r=(Double)getServletContext().getAttribute("R");
        
    }

}
