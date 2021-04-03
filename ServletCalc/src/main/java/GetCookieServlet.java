import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -01
 * Time: 19:32
 */
public class GetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 设置编码和返回的类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        //读取所有cookie
        Cookie[] cookies = request.getCookies();

        PrintWriter writer = response.getWriter();

        for (Cookie cookie : cookies) {
            writer.println(String.format("<h1>Cookie Name:%s,Cookie Valeue:%s</h1>",cookie.getName(),cookie.getValue()));
        }

    }
}
