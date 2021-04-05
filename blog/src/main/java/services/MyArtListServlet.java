package services;

import dao.ArticleInfoDao;
import dao.UserinfoDao;
import models.ArticleInfo;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -04
 * Time: 17:05
 */
public class MyArtListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;  //1:登录成功
        String msg = "";    //错误信息
        List<ArticleInfo> list = null;
        //1. 获取参数
        HttpSession session = request.getSession(false);
        if(session == null) {
            //用户未登录
            msg = "非法请求，请先登录";
        }else {
            //用户已经登录
            UserInfo userInfo = (UserInfo)session.getAttribute("userinfo");
            //2. 查询数据库
            int uid = userInfo.getId();
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();
            try {
                list = articleInfoDao.getListByUID(uid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            succ = 1;   //成功登录
        }

        //3. 构建和返回后端结果
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        result.put("list",list);
        ResultJSONUtils.write(response,result);
    }
}
