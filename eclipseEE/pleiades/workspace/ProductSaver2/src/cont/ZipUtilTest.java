package cont;

import java.io.IOException;

import model.ZipUtil;
 
public class ZipUtilTest {
     
    private static final String BASE_DIR = "src/test/resources/ziputil/deflateTest/";
     

    public void testDeflate() throws IOException {
        ZipUtil.deflate("/Users/atsushi/Desktop/test.zip",
                BASE_DIR + "ファイル1.txt",
                BASE_DIR + "ファイル2.txt",
                BASE_DIR + "ディレクトリA",
                BASE_DIR + "ディレクトリB"
                );
         
        ZipUtil.inflate("/Users/atsushi/Desktop/test.zip", "/Users/atsushi/Desktop/");
    }
}