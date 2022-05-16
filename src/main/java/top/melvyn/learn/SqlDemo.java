package top.melvyn.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDemo {
  public static void main(String[] args) throws SQLException {
    // JDBC连接的URL, 不同数据库有不同的格式:
    String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "root";

    // 获取连接:
    try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
      try (
          PreparedStatement ps = conn
              .prepareStatement("replace into students (id, score, grade, name, gender) values (?,?,?,?,?)",
                  Statement.RETURN_GENERATED_KEYS)) {
        ps.setObject(1, 1);
        ps.setObject(2, 99); // 注意：索引从1开始
        ps.setObject(3, 1); // grade
        ps.setObject(4, "Bob"); // name
        ps.setObject(5, 1); // gender
        int n = ps.executeUpdate();
        // try (ResultSet rs = ps.executeQuery()) {
        // while (rs.next()) {
        // long id = rs.getLong("id");
        // long grade = rs.getLong("grade");
        // String name = rs.getString("name");
        // String gender = rs.getString("gender");
        // System.out.print(id + " ");
        // System.out.print(grade + " ");
        // System.out.print(name + gender + "\n");
        // }
        // }
      }
    }
  }
}
