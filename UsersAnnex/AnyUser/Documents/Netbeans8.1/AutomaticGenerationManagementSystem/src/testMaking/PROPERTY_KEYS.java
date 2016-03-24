/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.util.ResourceBundle;

/**
 *
 * @author 00499
 */
	/**
	 * <p>[概 要] プロパティファイルキーの列挙型クラス</p>
	 * <p>[詳 細] </p>
	 * <p>[備 考] </p>
	 */
    public enum PROPERTY_KEYS {
    	DEFAULT_ID("default.id"),
    	DEFAULT_NAME("default.name");

        private String propertiesKey;

        private PROPERTY_KEYS( String propertiesKey ) {
            this.propertiesKey = propertiesKey;
        }

        public String getString() {
            return this.propertiesKey;
        }
    

	/**
	 * <p>[概 要] プロパティファイルの値を取得</p>
	 * <p>[詳 細] </p>
	 * <p>[備 考] </p>
	 * @param  field プロパティファイルのキー名
	 * @return プロパティファイルの値（パラメータがnullの場合はnullを返します。）
	 */
	public static String getProperties(String field){
		if(field == null){
			// パラメータがnullの場合、nullを返します
			return null;
		}

		// 「sample.properties」ファイルの読み込み
		ResourceBundle rb = ResourceBundle.getBundle("sample");

		// プロパティファイルから引数に指定されたキー値を取得
		String value = rb.getString(PROPERTY_KEYS.valueOf(field).getString());

		return value;
	}
        
        public static String setProperties(String field , String value){
            ResourceBundle rb = ResourceBundle.getBundle("sample");
            return ""; // dumy
        }
    }


