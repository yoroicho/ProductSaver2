/*
http://www.mlab.im.dendai.ac.jp/~yamada/java/properties/
 */
package testMaking;

import java.io.*;
import java.util.Properties;

public class ConfigurationXML {
    private Properties conf;

    public ConfigurationXML(String filename){
        conf = new Properties();
        try {
            conf.loadFromXML(new FileInputStream(filename));
        } catch (IOException e) {
            System.err.println("Cannot open " + filename + ".");
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        if(conf.containsKey(key))
            return conf.getProperty(key);
        else {
            System.err.println("Key not found: " + key);
            return "";
        }
    }

    public void addProperty(String key, String value) {
        if(conf.containsKey(key))
            System.err.println("Key already exists: " + key);
        else {
            conf.setProperty(key, value);
        }
    }

    public void storeToXML(String filename, String comments) {
        try {
            conf.storeToXML(new FileOutputStream(filename), comments);
        } catch (IOException e) {
            System.err.println("Cannot open " + filename + ".");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //args[0] = "test.xml";
        //ConfigurationXML conf = new ConfigurationXML(args[0]);
        
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
