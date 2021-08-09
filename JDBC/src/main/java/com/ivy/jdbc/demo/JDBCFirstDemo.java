package com.ivy.jdbc.demo;

import java.sql.*;

public class JDBCFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1、加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2、用户信息和url
        String url = "jdbc:mysql://localhost:3306/jdbcstudy";
        String username = "root";
        String password = "root";

        //3、连接成功，获取数据库对象
        Connection connection = DriverManager.getConnection(url, username, password);

        //4、获取SQL的对象Statement
        Statement statement = connection.createStatement();

        //5、执行SQL，查看返回结果
        String sql = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println(resultSet.getObject("id"));
            System.out.println(resultSet.getObject("name"));
            System.out.println(resultSet.getObject("age"));
        }
        //6、释放连接
        statement.close();
        connection.close();
    }
}
