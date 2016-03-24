/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afgms;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

class SystemInvokeThread extends Thread {

    private MainJFrame mainJFrame;
    private String systemDir;
    private String sourceDir;

    @Override
    public void run() {
        try {
            //http://futurismo.biz/archives/2978
            // Process Builder 生成

            ProcessBuilder pb = new ProcessBuilder(systemDir, sourceDir);

            // 標準出力と標準エラーをマージする (Defualt false)
            pb.redirectErrorStream(true);

            // プロセス開始
            Process process = pb.start();



            // 結果の出力
            InputStream is = process.getInputStream();

            // 結果の引渡し（コールバック）
            mainJFrame.printInputStreamSystemInvoke(is);
            
                        // プロセス完了待ち合わせ
            process.waitFor();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SystemInvokeThread.class.getName()).log(Level.SEVERE, null, ex);
            this.getMainJFrame().setMessagejTextAreaRedirectErrorStream(ex.toString());
        }
    }

    /**
     * @return the systemDir
     */
    public String getSystemDir() {
        return systemDir;
    }

    /**
     * @param systemDir the systemDir to set
     */
    public void setSystemDir(String systemDir) {
        this.systemDir = systemDir;
    }

    /**
     * @return the sourceDir
     */
    public String getSourceDir() {
        return sourceDir;
    }

    /**
     * @param sourceDir the sourceDir to set
     */
    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    /**
     * @return the mainJFrame
     */
    public MainJFrame getMainJFrame() {
        return mainJFrame;
    }

    /**
     * @param mainJFrame the mainJFrame to set
     */
    public void setMainJFrame(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;
    }

}
