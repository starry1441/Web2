import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description: hello servlet
 * User: starry
 * Date: 2021 -03 -31
 * Time: 20:52
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 获得一个写入流
        PrintWriter Writer = resp.getWriter();
        Writer.println("<h1 style='color:red'>Hello.Servlet~<h1>");

    }

}
