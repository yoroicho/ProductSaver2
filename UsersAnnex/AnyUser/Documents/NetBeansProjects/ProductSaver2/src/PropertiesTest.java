import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class PropertiesTest {
	public static void main(String args[]) {
		Properties property = new Properties();
		//Property property = new Property("test.properties");

		property.setProperty("key1",""+1234);
		property.setProperty("key2",""+1.2);
		property.setProperty("key3","abc");

		//値の取得
		int key1Data = Integer.parseInt(property.getProperty("key1"));
		double key2Data = Double.parseDouble(property.getProperty("key2"));
		String key3Data = property.getProperty("key3");
		System.out.println(key1Data);
		System.out.println(key2Data);
		System.out.println(key3Data);

		//存在しないキーで、データを取得しようとするとnullが返ってくる。
		System.out.println(property.getProperty("key4"));

		//list()メソッドで一覧表示することができる。
		property.list(System.out);

		//データの保存
		try {
			FileOutputStream out = new FileOutputStream("test.properties");
			property.store(out, "properties test data.");
		} catch (IOException e) {
			System.err.println("IO Error."+e);
		}

		//データの初期化
		property.setProperty("key1","");
		property.setProperty("key2","");
		property.setProperty("key3","");
		property.list(System.out);

		//データの読み込み
		try {
			FileInputStream in = new FileInputStream("test.ini");
			property.load(in);
		} catch (IOException e) {
			System.err.println("IO Error."+e);
		}
		property.list(System.out);

	}
}
