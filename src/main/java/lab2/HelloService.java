package lab2;

import java.io.UnsupportedEncodingException;

public class HelloService {

    private String message;

    public HelloService() throws UnsupportedEncodingException {
        message = new String("ХУЙ HUILA!".getBytes("windows-1251"),"UTF-8");
    }
	
    public String getMessage()
    {
        return message;
    }
    public void setMessage(final String message)
    {
        this.message = message;
    }

}
