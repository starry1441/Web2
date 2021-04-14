package dao;

import models.ArticleInfo;
import models.vo.ArticleInfoVO;
import utils.DBUtils;

import java.sql.*;
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

    /**
     * 通过文章号查询文章标题和内容
     * @param id
     * @return
     * @throws SQLException
     */
    public ArticleInfoVO getArtById(int id) throws SQLException {
        ArticleInfoVO art = new ArticleInfoVO();
        Connection connection = DBUtils.getConnection();
        String sql = "select a.*,u.username from articleinfo a,userinfo u where a.uid=u.id and a.id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            art.setTitle(resultSet.getString("title"));
            art.setContent(resultSet.getString("content"));
            art.setUsername(resultSet.getString("username"));
            art.setCreatetime(resultSet.getDate("createtime"));
            art.setRcount(resultSet.getInt("rcount"));
        }
        DBUtils.close(resultSet,statement,connection);
        return art;
    }

    /**
     * 修改文章标题和内容
     * @param id
     * @param title
     * @param content
     * @return
     * @throws SQLException
     */
    public int updateArt(int id, String title, String content) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "update articleinfo set title=?,content=? where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,title);
        statement.setString(2,content);
        statement.setInt(3,id);
        result = statement.executeUpdate();
        DBUtils.close(null,statement,connection);
        return result;
    }

    /**
     * 添加文章标题，内容
     * @param title
     * @param content
     * @param uid
     * @return
     * @throws SQLException
     */
    public int addArticle(String title, String content,int uid) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "insert into articleinfo(title,content,uid) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,title);
        statement.setString(2,content);
        statement.setInt(3,uid);
        result = statement.executeUpdate();
        DBUtils.close(null,statement,connection);
        return result;
    }

    /**
     * 操作数据库查询文章及作者
     * @return
     * @throws SQLException
     */
    public List<ArticleInfoVO> getAllArticle() throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "select a.*,u.username from articleinfo a,userinfo u where a.uid = u.id";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<ArticleInfoVO> list = new ArrayList<>();
        while(resultSet.next()) {
            ArticleInfoVO art = new ArticleInfoVO();
            art.setId(resultSet.getInt("id"));
            art.setTitle(resultSet.getString("title"));
            art.setUsername(resultSet.getString("username"));
            art.setCreatetime(resultSet.getDate("createtime"));
            art.setRcount(resultSet.getInt("rcount"));
            list.add(art);
        }
        DBUtils.close(resultSet,statement,connection);
        return list;
    }

    /**
     * 分页：
     * 操作数据库分页查询文章及作者
     * @param page
     * @param psize
     * @return
     * @throws SQLException
     */
    public List<ArticleInfoVO> getAllArticleByPage(int page, int psize) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "select a.*,u.username from articleinfo a,userinfo u where a.uid = u.id limit ?,?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,(page-1)*psize);
        statement.setInt(2,psize);
        ResultSet resultSet = statement.executeQuery();
        List<ArticleInfoVO> list = new ArrayList<>();
        while(resultSet.next()) {
            ArticleInfoVO art = new ArticleInfoVO();
            art.setId(resultSet.getInt("id"));
            art.setTitle(resultSet.getString("title"));
            art.setUsername(resultSet.getString("username"));
            art.setCreatetime(resultSet.getDate("createtime"));
            art.setRcount(resultSet.getInt("rcount"));
            list.add(art);
        }
        DBUtils.close(resultSet,statement,connection);
        return list;
    }

    /**
     * 访问量+1
     * @param id
     * @return
     * @throws SQLException
     */
    public int addCount(int id) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "update articleinfo set rcount=rcount+1 where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        result = statement.executeUpdate();
        DBUtils.close(null,statement,connection);
        return result;
    }
}