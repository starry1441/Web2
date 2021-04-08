package services;

import dao.UserInfoDao;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:注册功能的业务层
 * User: starry
 * Date: 2021 -04 -05
 * Time: 23:22
 */
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回给前端数据
        int succ = 0;   //1表示成功
        String msg = "";    //错误信息提示

        //1. 获取到前端提供的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        //非空效验
        if(username != null && !username.equals("") &&
        password != null && !password.equals("") &&
        password2 != null && !password2.equals("")) {
            if(!password.equals(password2)) {
                msg = "两次密码输入不一致";
            }
        }else {
            msg = "非法输入";
        }

        //2. 进行业务处理
        UserInfoDao userInfoDao = new UserInfoDao();
        try {
            succ = userInfoDao.add(username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //3. 返回结果
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("succ",succ);
        hashMap.put("msg",msg);
        ResultJSONUtils.write(response,hashMap);
    }
}
