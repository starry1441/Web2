package services;

import dao.ArticleInfoDao;
import models.vo.ArticleInfoVO;
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
 * Description:根据id查询文章标题和内容
 * User: starry
 * Date: 2021 -04 -11
 * Time: 21:00
 */

@WebServlet("/init")
public class InitServlet extends HttpServlet {

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
        ArticleInfoVO art = null;
        if(id > 0) {
            ArticleInfoDao dao = new ArticleInfoDao();
            try {
                 art = dao.getArtById(id);
                 succ = 1;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "非法登录，请先登录";
        }

        // 3.将上一步操作的结果返回给前端
        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        result.put("art",art);
        ResultJSONUtils.write(response, result);
    }
}