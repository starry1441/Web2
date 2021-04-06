import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:ajax版本计算器
 * User: starry
 * Date: 2021 -04 -04
 * Time: 9:39
 */
public class MyCalcServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码和数据类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        String msg = "";    //错误描述信息
        int sum = 0;
        int state = -1;     //200:正常返回

        //1. 先从前端获取参数
        String num1 = request.getParameter("number1");
        String num2 = request.getParameter("number2");
        //todo:非空效验[后端永远不要相信前端]
        if(num1 != null && num2 != null && !num1.equals("") && !num2.equals("")) {
            //2. 进行相应的业务处理（相加）
            sum = Integer.parseInt(num1) + Integer.parseInt(num2);
            state = 200;
        }else {
            //非法请求，参数传递不全
            msg = "非法请求，参数传递不全";
        }

        //3. 返回结果给前端
        //{"state":%d,"msg":"%s","sum":%d}
        PrintWriter writer = response.getWriter();
        writer.println(String.format("{\"state\":%d,\"msg\":\"%s\",\"sum\":%d}",state,msg,sum));

    }
}
