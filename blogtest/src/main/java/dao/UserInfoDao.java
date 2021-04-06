package dao;

import models.UserInfo;
import utils.DBUtils;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:用来操作userinfo表
 * User: starry
 * Date: 2021 -04 -06
 * Time: 8:42
 */
public class UserInfoDao {

    /**
     * 注册用户(往数据库里插入数据)
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public int add(String username,String password) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "insert into userinfo(username,password) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,password);
        result = statement.executeUpdate();
        DBUtils.close(null,statement,connection);
        return result;
    }

    /**
     * 通过账号密码查询用户
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public UserInfo getUser(String username,String password) throws SQLException {
        UserInfo userInfo = new UserInfo();
        Connection connection = DBUtils.getConnection();
        String sql = "select * from userinfo where username=? and password=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,password);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setUsername(resultSet.getString("username"));
            userInfo.setPassword(resultSet.getString("password"));
        }
        DBUtils.close(resultSet,statement,connection);
        return userInfo;
    }

}
