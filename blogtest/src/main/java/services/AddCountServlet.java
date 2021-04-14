package services;

import dao.ArticleInfoDao;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:访问量+1
 * User: starry
 * Date: 2021 -04 -14
 * Time: 14:08
 */

@WebServlet("/addcount")
public class AddCountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1; // succ=1 表示操作成功
        String msg = ""; // 错误说明信息
        // 1.从前端获取参数
        int id = Integer.parseInt(request.getParameter("id"));

        // 2.调用数据库执行相应的业务逻辑
        if(id > 0) {
            ArticleInfoDao dao = new ArticleInfoDao();
            try {
                succ = dao.addCount(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "无效参数";
        }

        // 3.将上一步操作的结果返回给前端
        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        ResultJSONUtils.write(response, result);
    }
}