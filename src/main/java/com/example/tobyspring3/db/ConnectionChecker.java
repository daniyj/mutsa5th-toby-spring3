package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectionChecker {

    public void select() throws ClassNotFoundException, SQLException{
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");

        while (rs.next()) {
            String col1 = rs.getString(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.printf("%s %s %s\n", col1, col2, col3);
        }
    }

    //db연결, db의 스키마 목록 출력
    public void check() throws SQLException,ClassNotFoundException{
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,
                dbUser,
                dbPassword
         );

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW DATABASES");
        rs = stmt.getResultSet();

        while (rs.next()) {
            String line = rs.getString(1);
            System.out.println(line);
        }
    }
    //workbench에서 만든 users테이블에 값 넣기
    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        PreparedStatement pstmt = conn.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)"
        );
        pstmt.setString(1, "1");
        pstmt.setString(2, "YJ");
        pstmt.setString(3, "12345678");
        pstmt.executeUpdate();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionChecker cc = new ConnectionChecker();
//        cc.check();
//        cc.add();
        cc.select();

    }//main

}
