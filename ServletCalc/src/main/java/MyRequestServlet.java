import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -03
 * Time: 13:02
 */
public class MyRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码和数据类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        //获取请求方法类型
        String method = request.getMethod();
        //获取相对地址信息
        String uri = request.getRequestURI();
        //
        String contentType = request.getContentType();

        PrintWriter writer = response.getWriter();
        writer.println(String.format("<h1>method:%s</h1><p></p>",method));
        writer.println(String.format("<h1>uri:%s</h1><p></p>",uri));
        writer.println(String.format("<h1>contentType:%s</h1><p></p>",contentType));
    }
}
