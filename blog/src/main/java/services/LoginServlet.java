package services;

import dao.UserinfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -04
 * Time: 15:08
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;  //1:登录成功
        String msg = ""; //错误原因
        //1.得到前端传递的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.去数据库验证用户名和密码[业务]
        if(username != null && !username.equals("") &&
        password != null && !password.equals("")) {
            //有参数，执行数据库查询
            UserinfoDao userinfoDao = new UserinfoDao();
            UserInfo userInfo = null;
            try {
                userInfo = userinfoDao.getUser(username,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(userInfo.getId() > 0) {
                //查到了该用户(用户名和密码正确)
                succ = 1;
                //将用户信息存放到session
                HttpSession session = request.getSession(); //用来创建会话
                //将用户信息存放在当前session
                session.setAttribute("userinfo",userInfo);
            }else {
                msg = "用户名或密码不正确";
            }

        }else {
            //参数不完整
            msg = "非法请求，参数不完整";
        }

        //3.返回结果
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        ResultJSONUtils.write(response,result);
    }
}
