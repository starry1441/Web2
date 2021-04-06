package utils;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;


/**
 * Created with IntelliJ IDEA.
 * Description:数据库通用工具类
 * User: starry
 * Date: 2021 -04 -06
 * Time: 8:45
 */
public class DBUtils {

    public static MysqlDataSource dataSource = null;

    /**
     * 连接数据库
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //第一次链接数据库
        if (dataSource == null){
            dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://127.0.0.1:3306/blogtest?charactionEncoding=utf-8&useSSL=true");
            dataSource.setUser("root");
            dataSource.setPassword("111111");
        }
        return dataSource.getConnection();
    }

    /**
     * 释放资源
     * @param resultSet
     * @param statement
     * @param connection
     * @throws SQLException
     */
    public static void close(ResultSet resultSet,
                             PreparedStatement statement,
                             Connection connection) throws SQLException {
        if(resultSet != null) resultSet.close();
        if(statement != null) statement.close();
        if(connection != null) connection.close();
    }

}
