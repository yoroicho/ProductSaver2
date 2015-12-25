/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author 00499
 */
public class PropertyKeys {
	private Properties divConf; 

    public  void main(String[] args) {
        					FileOutputStream fos;
						try {
							fos = new FileOutputStream(new File("MakeConf.properties"));
							divConf.store(fos, "DivisionConfig");
							fos.close();
						} catch (IOException e2) {
							// TODO 自動生成された catch ブロック
							e2.printStackTrace();
						}
						//dispose();
						
        
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("MakeConf");
        String message = resourceBundle.getString("KEY");

        if ("".equals(message)) {
            System.out.println("Message Not Found.");
        } else {
            System.out.println(message);
        }
    }
}
