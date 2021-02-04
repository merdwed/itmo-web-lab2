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
         
        try {
            String x=request.getParameter("X").replace(",",".");
            String y=request.getParameter("Y").replace(",",".");
            String r=request.getParameter("R").replace(",",".");
            request.getServletContext().setAttribute("X", Double.parseDouble(x));
            request.getServletContext().setAttribute("Y", Double.parseDouble(y));
            request.getServletContext().setAttribute("Z", Double.parseDouble(r));
            chain.doFilter(request, response);             
        } catch (Exception e) {
            request.getServletContext().setAttribute("LastError","Wrong Number format for arguments X Y R");
            request.getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        }
        
    } 

    public void destroy() 
    { 
        
    } 
} 