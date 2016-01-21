/*
 http://dev.classmethod.jp/server-side/java/java_mysql_transaction/
 */
package testMaking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionClass {

    static final String URL = "jdbc:mysql://localhost:3306/test_swing_jdbc?zeroDateTimeBehavior=convertToNull";
    static final String USERNAME = "root";
    static final String PASSWORD = "pass";

    public static void main(String[] args) {

        String sql = "INSERT INTO music VALUES (?, ?, ?, ?);";

        String[][] list = {
            {"16", "Sam Smith", "Stay With Me", "2015"},
            {"17", "Kanye West", "Gold Digger", "2005"},
            {"18", "Roni Size", "It's A Jazz Thing", "1994"},
            {"19", "Prince", "Kiss", "1988"},
            {"20", "Led Zeppelin", "When The Levee Breaks", "1971"}
        };

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql);) {

            connection.setAutoCommit(false);

            for (int i = 0; i < list.length; i++) {

                statement.setInt(1, Integer.valueOf(list[i][0]));
                statement.setString(2, list[i][1]);
                statement.setString(3, list[i][2]);
                statement.setInt(4, Integer.valueOf(list[i][3]));

                statement.addBatch();
                System.out.println(statement.toString());

            }

            int[] result = statement.executeBatch();
            System.out.println("登録：" + result.length + "件");

            try {

                connection.commit();
                System.out.println("登録成功");

            } catch (SQLException e) {

                connection.rollback();
                System.out.println("登録失敗：ロールバック実行");

                e.printStackTrace();

            }

            Statement stm = connection.createStatement();
            String sql_select = "select * from music";
            ResultSet rs = stm.executeQuery(sql_select);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("取得結果 -> " + id + ":" + name);
            }

        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
            e.printStackTrace();

        }

    }

}
