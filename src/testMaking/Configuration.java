/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
参考サイト
http://www.mlab.im.dendai.ac.jp/~yamada/java/properties/
*/

package testMaking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private Properties conf;

    public Configuration(String filename){
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

    public void store(String filename, String comments) {
        try {
            conf.store(new FileOutputStream(filename), comments);
        } catch (IOException e) {
            System.err.println("Cannot open " + filename + ".");
            e.printStackTrace();
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
        Configuration conf = new Configuration(args[0]);
        // 読み込み
        System.out.println(conf.getProperty("university"));
        System.out.println(conf.getProperty("faculty"));

        // 追加
        conf.addProperty("division", "Information Systems and Multimedia Design");

        // 説明を指定して保存
        conf.store(args[0], "configurations");
        //conf.storeToXML(args[0] + ".xml", "configurations");
    }
}


