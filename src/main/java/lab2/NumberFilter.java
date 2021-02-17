package lab2; 
  
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
  
public class NumberFilter implements Filter 
{ 
    private Double xMax = 5.; 
    private Double xMin = -3.; 
    private Double yMax = 3.; 
    private Double yMin = -3.; 
    private Double rMax = 4.; 
    private Double rMin = 1.; 
    
    public void init (FilterConfig config) throws ServletException 
    { 
       
    } 

    public void doFilter (ServletRequest request, ServletResponse response, 
                          FilterChain chain) throws IOException, ServletException 
    { 
        String x="";
        String y="";
        String r="";
        try {
            x=request.getParameter("coordX").replace(",",".");
            y=request.getParameter("coordY").replace(",",".");
            r=request.getParameter("paramR").replace(",",".");
            request.getServletContext().setAttribute("X", Double.parseDouble(x));
            request.getServletContext().setAttribute("Y", Double.parseDouble(y));
            request.getServletContext().setAttribute("R", Double.parseDouble(r));
            chain.doFilter(request, response);             
        } catch (Exception e) {
            String message="Some errors catched in filter\n" + e.getMessage() + "\n X Y R:" + x + "\n" + y + "\n" + r;
            for (StackTraceElement item:e.getStackTrace())
            message = message + "\n" + item.toString();
            request.getServletContext().setAttribute("LastError",message);
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/ErrorPage.jsp");
        }
        
    } 

    public void destroy() 
    { 
        
    } 
} 