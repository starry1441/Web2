package services;

import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:登录功能
 * User: starry
 * Date: 2021 -04 -06
 * Time: 16:32
 */

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回前端参数
        int succ = -1;
        String msg = "";

        //1. 获得前端请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2. 处理业务(验证用户名和密码)
        //非空效验
        if(username != null && !username.equals("") &&
        password != null && !password.equals("")) {
            //参数格式正确，执行查询数据库
            UserInfoDao userInfoDao = new UserInfoDao();
            try {
                UserInfo userInfo = userInfoDao.getUser(username,password);
                if (userInfo.getId() > 0) {
                    //用户账号密码正确
                    succ = 1;
                    //创建会话
                    HttpSession session = request.getSession();
                    // 将用户信息存放到当前session
                    session.setAttribute("userInfo",userInfo);
                } else {
                    //用户账号密码不正确，未从数据库中查得
                    msg = "账号密码输入错误";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            // 参数不完整，非法请求
            msg = "参数不完整，非法请求";
        }

        //3. 返回响应给前端
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("succ",succ);
        hashMap.put("msg",msg);
        ResultJSONUtils.write(response,hashMap);
    }
}
