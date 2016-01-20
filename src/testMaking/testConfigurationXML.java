/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

/**
 *
 * @author 00499
 */
import java.io.File;
import localIO.*;

public class testConfigurationXML {
    
    public static void main(String[] args) {
        String jarPath = System.getProperty("java.class.path");
//String dirPath = jarPath.substring(0, jarPath.lastIndexOf(File.separator)+1);
String dirPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String filePath= dirPath +"test20160120test.XML";

        //filePath = System.getProperty("java.class.path")+"test20160120test.XML"; 
        System.out.println(filePath);
        localIO.ConfigurationXML conf = new localIO.ConfigurationXML( filePath);
        conf.addProperty("testKeyA", "A");
        conf.storeToXML( filePath, "普通のセーブ");
        conf.setProperty("testKeyAm", "");
        System.out.println("読み出しました" + conf.getProperty("testKeyA"));
        conf.remove("testKeyA");
        conf.storeToXML( filePath, "変更後のセーブ");
    }
    
    private static String get_currentpath(){ //自分自身が居るディレクトの絶対パスを得る
        // http://www.mltlab.com/wp/archives/293

	String cp=System.getProperty("java.class.path");
System.out.println(cp);
	String fs=System.getProperty("file.separator");
System.out.println(fs);
	String acp=(new File(cp)).getAbsolutePath();
System.out.println(acp);
//

	int p,q;

	for(p=0;(q=acp.indexOf(fs,p))>=0;p=q+1);

	return acp.substring(0,p);

}
}
