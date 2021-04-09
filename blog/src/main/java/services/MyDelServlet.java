package services;

import dao.ArticleInfoDao;
import dao.UserinfoDao;
import models.ArticleInfo;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -06
 * Time: 19:48
 */

@WebServlet("/mydel")
public class MyDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;  //1:登录成功
        String msg = "";    //错误信息
        //1. 获取参数
        int id = Integer.parseInt(request.getParameter("id"));
        // 2.调用数据库执行相应的业务逻辑
        ArticleInfoDao articleInfoDao = new ArticleInfoDao();
        try {
            succ = articleInfoDao.del(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(succ != 1) {
            msg = "非法操作";
        }

        //3. 构建和返回后端结果
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        ResultJSONUtils.write(response, result);
    }
}
