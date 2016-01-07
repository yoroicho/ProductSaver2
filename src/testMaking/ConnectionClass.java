/*
http://dev.classmethod.jp/server-side/java/java_mysql_transaction/
 */
package testMaking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class ConnectionClass {
 
    static final String URL = "jdbc:mysql://localhost/cm";
    static final String USERNAME = "user";
    static final String PASSWORD = "pass";
     
    public static void main(String[] args) {
     
        String sql = "INSERT INTO music VALUES (?, ?, ?, ?);";
         
        String[][] list = {
            {"1", "Sam Smith",    "Stay With Me",          "2015"},
            {"2", "Kanye West",   "Gold Digger",           "2005"},
            {"3", "Roni Size",    "It's A Jazz Thing",     "1994"},
            {"4", "Prince",       "Kiss",                  "1988"},
            {"5", "Led Zeppelin", "When The Levee Breaks", "1971"}
        };
         
        try (  Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql); ) {
                 
            connection.setAutoCommit(false);
                 
            for (int i = 0; i < list.length; i++) {
                 
                statement.setInt   (1, Integer.valueOf(list[i][0]));
                statement.setString(2, list[i][1]);
                statement.setString(3, list[i][2]);
                statement.setInt   (4, Integer.valueOf(list[i][3]));
                 
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
                 
        } catch (SQLException e) {
                 
            e.printStackTrace();
 
        }
     
    }
 
}