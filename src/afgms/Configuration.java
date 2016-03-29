/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 参考サイト
 http://www.mlab.im.dendai.ac.jp/~yamada/java/properties/
 */
package afgms;

//import testMaking.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Configuration {

    private Properties conf;
    private String lineSeparator;
    private String userDir;
    private String fileSeparator;
    private String configurationPath;

    public Configuration(String filename) { // args[0]で起動時に設定ファイル名を指定することで、起動時に環境を選択しなくてもよい。
        /*
         設定ファイルを分離する事により登録データベースならびに保存基ディレクトリを変更する
         ことが可能だが、これを起動時に手動で行った場合錯誤が生じる可能性がある。
         しかし、用途によってこれら環境を今後分離する可能性は否定できないため、コマンドライン
         付きのショートカットのargs[0]にConfigファイル名を指定することで錯誤の防止と利便性の
         向上を確保するものとする。
        ＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊　重要　＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
         但し、args[0]を指定しない直接起動をした際にエラーもしくはデフォルトConfigファイルが
         設定となるとなるようする必要がある。（拡張子のみのファイルが出来てしまう可能性がある）
         */

        try { // OS依存情報取得
            this.lineSeparator = System.getProperty("line.separator"); // 改行コード
            this.userDir = System.getProperty("user.dir"); // 自分自身のパス
            this.fileSeparator = System.getProperty("file.separator"); // Windowsでは円マーク
            System.out.println(this.lineSeparator + " " + this.userDir + " " + this.fileSeparator + " です。");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "OS依存情報が取得できません、終了いたします。");
            System.exit(0);
        }

        configurationPath = userDir + fileSeparator + filename + ".xml";

        conf = new Properties();
        try {
            conf.loadFromXML(new FileInputStream(configurationPath)); // XML形式で無い場合はconf.load()
        } catch (IOException e) {
            System.err.println("Cannot open " + configurationPath + ".");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, userDir + fileSeparator + filename + ".xml" + "の指定環境で初回起動です。");
        }

    }

    public void removeProperty(String key) {
        if (conf.containsKey(key)) {
            conf.remove(key);
        } else {
            System.err.println("Key not found: " + key);
            JOptionPane.showMessageDialog(null, "Key not found: " + key);
        }
    }

    public String getProperty(String key) {
        if (conf.containsKey(key)) {
            return conf.getProperty(key);
        } else {
            System.err.println("Key not found: " + key);
            JOptionPane.showMessageDialog(null, "Key not found: " + key);
            return "";
        }
    }

    public void updateProperty(String key, String value) {
        if (!conf.containsKey(key)) {
            System.err.println("Key not exists: " + key);
            JOptionPane.showMessageDialog(null, "Key not exists: " + key);
        } else {
            conf.setProperty(key, value);
        }
    }
    
        public void setProperty(String key, String value) { // キーの有無に関わらずファイルへ
                    conf.setProperty(key, value);
            }

    public void addProperty(String key, String value) {
        if (conf.containsKey(key)) {
            System.err.println("Key already exists: " + key);
            JOptionPane.showMessageDialog(null, "Key already exists: " + key);
        } else {
            conf.setProperty(key, value);
        }
    }

    public void store(String filename, String comments) {
        try {
            conf.store(new FileOutputStream(configurationPath), comments);
        } catch (IOException e) {
            System.err.println("Cannot open " + configurationPath + ".");
            JOptionPane.showMessageDialog(null, "Cannot open " + configurationPath + ".");
            e.printStackTrace();
        }
    }

    public void storeToXML(String filename, String comments) {
        try {
            conf.storeToXML(new FileOutputStream(configurationPath), comments);
        } catch (IOException e) {
            System.err.println("Cannot open " + configurationPath + ".");
            JOptionPane.showMessageDialog(null, "Cannot open " + configurationPath + ".");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // mainのメソッドはすべてサンプル事例。
        Configuration conf = new Configuration(args[0]);
        // 読み込み
        System.out.println(conf.getProperty("university"));
        System.out.println(conf.getProperty("faculty"));

        // 追加
        conf.addProperty("division", "Information Systems and Multimedia Design");

        // 説明を指定して保存
        conf.store(args[0], "configurations");
        //conf.storeToXML(args[0] + ".xml", "configurations");
        // サンプル事例以上。
    }
}
