import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:后端处理
 * User: starry
 * Date: 2021 -04 -01
 * Time: 11:44
 */
public class CalcServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 设置编码和返回的类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        //2. 获取前端传递的参数
        String num1 = request.getParameter("number1");  //id是给js用的
        String num2 = request.getParameter("number2");  //name才是给form表单用的
        //3. 业务逻辑处理
        int total = Integer.parseInt(num1) + Integer.parseInt(num2);
        //4. 返回结果给前端
        PrintWriter writer = response.getWriter();
        writer.println(String.format("<h1>计算结果为：%d</h1>",total));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这样请求get方法也可以相应
        this.doPost(request, response);
    }
}
