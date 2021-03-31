package org.example;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main ( String[] args ) throws SQLException {
        // 1.获得DataSource
        MysqlDataSource dataSource = new MysqlDataSource();
        // url,user,password
        dataSource.setURL("jdbc:mysql://127.0.0.1:3306/java18?charactionEncoding=utf8&useSSL=false");
        dataSource.setUser("root");
        // 【写自己mysql的密码】
        dataSource.setPassword("111111");
        // 2.获取connection
        Connection connection = dataSource.getConnection();
        // 3.拼接SQL
        String sql = "select * from student where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,2);
        // 4.得到 ResetSet
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println("查询的数据name:"+
                    resultSet.getString("name"));
        }
        // 5.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
