package utils;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -04
 * Time: 11:02
 */
public class DBUtils {

    private static MysqlDataSource dataSource = null;

    /**
     * 得到 Connection 的通用方法
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            //第一次调用
            dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://127.0.0.1:3306/java18blog?characterEncoding=utf-8");
            dataSource.setUser("root");
            dataSource.setPassword("11111111");
        }
        return dataSource.getConnection();
    }

    /**
     * 关闭的通用方法
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
