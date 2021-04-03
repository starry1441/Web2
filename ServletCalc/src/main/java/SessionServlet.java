import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -01
 * Time: 20:10
 */
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码和数据类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        //验权（验证用户名和密码是否正确）
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        if(name != null && pwd != null && name.equals("root") && pwd.equals("111111")) {
            //用户效验成功，创建会话信息
            //尝试从客户端获取Session，如果获取不到则创建一个会话信息;false：不会新创建一个会话信息
            HttpSession session = request.getSession(true); //一定会有一个会话信息
            String sessionId = session.getId();
            writer.println("<h1>欢迎访问~</h1>");
            writer.println(String.format("<h3>SessionID:%s</h3>",sessionId));

            //session存储访问数量的key
            String countKey = "countkey";
            if(session.isNew() || session.getAttribute(countKey) == null) {
                session.setAttribute(countKey,1);
                writer.println("<h4>访问次数：1</h4>");
            } else {
                int count = (int)session.getAttribute(countKey);
                count++;
                //重新将访问数据变更到
                session.setAttribute(countKey,count);
                writer.println(String.format("<h4>访问次数：%d</h4>",count));
            }

        } else {
            writer.println("<h1>用户名或密码错误！</h1>");
        }
    }
}
