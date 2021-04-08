package dao;

import models.ArticleInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:用来操作articleinfo表
 * User: starry
 * Date: 2021 -04 -06
 * Time: 18:08
 */
public class ArticleInfoDao {

    /**
     * 从数据库中根据id查询所有文章
     * @param uid
     * @return
     * @throws SQLException
     */
    public List<ArticleInfo> getListByUID(int uid) throws SQLException {
        List<ArticleInfo> list = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        String sql = "select * from articleinfo where uid=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, uid);
        // 查询数据库并返回结果
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setId(resultSet.getInt("id"));
            articleInfo.setRcount(resultSet.getInt("rcount"));
            articleInfo.setTitle(resultSet.getString("title"));
            articleInfo.setContent(resultSet.getString("Content"));
            articleInfo.setCreatetime(resultSet.getDate("createtime"));
            list.add(articleInfo);
        }
        DBUtils.close(resultSet,statement,connection);
        return list;
    }

    /**
     * 删除文章
     * @param id
     * @return
     * @throws SQLException
     */
    public int del(int id) throws SQLException {
        int result = 0;
        if(id > 0) {
            Connection connection = DBUtils.getConnection();
            String sql = "delete from articleinfo where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            result = statement.executeUpdate();
            DBUtils.close(null,statement,connection);
        }
        return result;
    }
}