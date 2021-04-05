package services;

import dao.UserinfoDao;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -04
 * Time: 10:57
 */
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = 0;   //状态，成功为 1
        String msg = "";    //显示错误信息

        //1. 获取请求的参数+非空效验
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        //非空效验
        if(username != null && password != null && password2 != null &&
                !username.equals("") && !password.equals("") && !password2.equals("")) {
            msg = "输入不全";
        }else if(password == password2) {
            msg = "恭喜你，注册成功";
        }else {
            msg = "输入两次密码不一致";
        }

        //2. [业务逻辑处理]操作数据库用户
        UserinfoDao userinfoDao = new UserinfoDao();
        try {
            succ = userinfoDao.add(username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //3. 返回结果
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        ResultJSONUtils.write(response,result);
    }
}
