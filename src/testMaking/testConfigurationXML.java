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
import localIO.*;

public class testConfigurationXML {
    
    public static void main(String[] args) {
        localIO.ConfigurationXML conf = new localIO.ConfigurationXML("D:\\My Documents\\myCocConfTest3.XML");
        conf.addProperty("testKeyA", "A");
        conf.storeToXML("D:\\My Documents\\myCocConfTest3.XML", "普通のセーブ");
        conf.setProperty("testKeyA", "");
        System.out.println("読み出しました" + conf.getProperty("testKeyA"));
        conf.remove("testKeyA");
        conf.storeToXML("D:\\My Documents\\myCocConfTest3.XML", "変更後のセーブ");
    }
    
}
