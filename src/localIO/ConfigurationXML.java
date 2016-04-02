/*
 http://www.mlab.im.dendai.ac.jp/~yamada/java/properties/
 */
package localIO;

//import testMaking.*;
import java.io.*;
import java.util.Properties;

public class ConfigurationXML {

    private Properties conf;

    public ConfigurationXML(String filename) {
        conf = new Properties();
        try {
            conf.loadFromXML(new FileInputStream(filename));
        } catch (IOException e) {
            System.err.println("Cannot open " + filename + ".");
            e.printStackTrace();
            AlertDialog alertDialog = new AlertDialog(new javax.swing.JFrame(), true);
            alertDialog.setJTextAreaMessage("Cannot open " + filename + ".");
            alertDialog.setVisible(true);
            System.out.println(alertDialog.getReturnStatus());
        }
    }

    public String getProperty(String key) {
        if (conf.containsKey(key)) {
            return conf.getProperty(key);
        } else {
            System.err.println("Key not found: " + key);
            AlertDialog alertDialog = new AlertDialog(new javax.swing.JFrame(), true);
            alertDialog.setJTextAreaMessage("Key not found: " + key);
            alertDialog.setVisible(true);
            System.out.println(alertDialog.getReturnStatus());
            return "";
        }
    }

    public void setProperty(String key, String value) {
        if (conf.containsKey(key)) {
            conf.setProperty(key, value);
        } else {
            System.err.println("Key not found: " + key);
            AlertDialog alertDialog = new AlertDialog(new javax.swing.JFrame(), true);
            alertDialog.setJTextAreaMessage("Key not found: " + key);
            alertDialog.setVisible(true);
            System.out.println(alertDialog.getReturnStatus());
        }
    }

        public void upDateProperty(String key, String value) {
        // 単純アップデート
            conf.setProperty(key, value);        
    }
    
    
    public void addProperty(String key, String value) {
        if (conf.containsKey(key)) {
            System.err.println("Key already exists: " + key);
            AlertDialog alertDialog = new AlertDialog(new javax.swing.JFrame(), true);
            alertDialog.setJTextAreaMessage("Key already exists: " + key);
            alertDialog.setVisible(true);
            System.out.println(alertDialog.getReturnStatus());
        } else {
            conf.setProperty(key, value);
        }
    }

    public void remove(String key) {
        if (conf.containsKey(key)) {
            conf.remove(key);
        } else {
            System.err.println("Key not exists: " + key);
            AlertDialog alertDialog = new AlertDialog(new javax.swing.JFrame(), true);
            alertDialog.setJTextAreaMessage("Key not exists: " + key);
            alertDialog.setVisible(true);
            System.out.println(alertDialog.getReturnStatus());
        }
    }

    public void storeToXML(String filename, String comments) {
        try {
            conf.storeToXML(new FileOutputStream(filename), comments);
        } catch (IOException e) {
            System.err.println("Cannot open " + filename + ".");
            e.printStackTrace();
            AlertDialog alertDialog = new AlertDialog(new javax.swing.JFrame(), true);
            alertDialog.setJTextAreaMessage("Cannot open " + filename + ".");
            alertDialog.setVisible(true);
            System.out.println(alertDialog.getReturnStatus());
        }
    }

    public static void main(String[] args) {
        ConfigurationXML conf = new ConfigurationXML(args[0]);

        // 読み込み
        //System.out.println("sitename1: " + conf.getProperty("sitename1"));
        //System.out.println("url1: " + conf.getProperty("url1"));
        // 追加
        //conf.addProperty("sitename3", "delicious");
        //conf.addProperty("url3", "http://www.delicious.com/");
        // 説明を指定して保存
        //conf.storeToXML(args[0], "ソーシャルブックマーク");
    }
}
