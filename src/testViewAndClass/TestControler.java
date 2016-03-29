/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testViewAndClass;

/**
 *
 * @author 00499
 */
public class TestControler { // 外部クラスで切り分けるのは見送り
 private  TestTabForm ttf = null;
    TestControler(TestTabForm ttf){
        this.ttf=ttf;
    }
    private class TabOneAndTwo{
        String moveText = null;
        public void copyTextOneToTwo(){
            // メンバにアクセスできない
        }
    }

    public class Tab1 {
        private void copyTextOneToTwo(){
            
        }
}


    
}