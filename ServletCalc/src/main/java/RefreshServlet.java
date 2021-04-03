import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -03
 * Time: 12:55
 */
public class RefreshServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码和数据类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        //每秒刷新一次
        response.setIntHeader("Refresh",1);
        PrintWriter writer = response.getWriter();
        writer.println(String.format("<h1>当前时间:%s</h1>",new Date()));
    }
}
