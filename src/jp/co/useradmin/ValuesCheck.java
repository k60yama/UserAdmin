package jp.co.useradmin;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ValuesCheck {
	//モード別フラグ管理
	private static final int ADD_MODE = 0;
	private static final int UPDATE_MODE = 1;
	private static final int DELETE_MODE = 2;
	private static final int SHOW_MODE = 3;
	
	/*
	 * 入力チェック
	 * 戻り値：true(入力有り)、false(入力無し)
	 */
	protected static boolean isBlank(String str){
		//初期化
		boolean isBlank = false;
		if(!("".equals(str.trim()))){
			isBlank = true;
		}
		return isBlank;
	}
	
	
	/*
	 * 数値チェックメソッド
	 * 戻り値：true(数値)、false(数値以外) 
	 */
	protected static boolean isNumericValue(String str){
		//初期化
		boolean isNumericValue = false;
		try{
			//Integerクラス変換
			Integer.parseInt(str);
			isNumericValue = true;
			return isNumericValue;
		}catch(NumberFormatException e){
			return isNumericValue;
		}
	}
	
	
	/*
	 * 日付形式チェックメソッド
	 * 戻り値：Date
	 * nullでない場合：日付形式
	 * nullの場合：日付形式ではない
	 */
	protected static Date isDate(String str){
		//初期化
		Date date = null;
		
		//日付形式指定(SimpleDateFormatインスタンス生成)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);		//日付解析を厳密に行う
		try{
			//日付形式に変換
			date = sdf.parse(str);
		}catch(ParseException e){}
		return date;
	}
	
	
	/*
	 * 処理モードチェック
	 * 戻り値： String
	 * nullでない場合：処理モード内
	 * nullの場合：処理モード外
	 */
	protected static String isModeType(int mode){
		//初期化
		String isModeType = null;
		
		//各処理モードへ
		switch(mode){
		case ADD_MODE:
			isModeType = UserAdminMain.ADD_MODE_NAME;
			break;
		case UPDATE_MODE:
			isModeType = UserAdminMain.UPDATE_MODE_NAME;
			break;
		case DELETE_MODE:
			isModeType = UserAdminMain.DELETE_MODE_NAME;
			break;
		case SHOW_MODE:
			isModeType = UserAdminMain.SHOW_MODE_NAME;
			break;
		}
		return isModeType;
	}
	
	
	/*
	 * XMLファイル存在チェック
	 * 戻り値:true(有)、false(無)
	 */
	protected static boolean isXMLFile(){
		//初期化
		boolean isXMLFile = false;
		
		//Fileインスタンス生成
		File file = new File(XMLMainProc.FILE_NAME);
		if(file.exists()){
			isXMLFile = true;
		}
		return isXMLFile;
	}
	
	
	/*
	 * ユーザーID存在チェック
	 * 戻り値: String型
	 * nullでない場合：ユーザーID有
	 * nullの場合：ユーザーID無
	 */
	protected static String isUserId(String chkId){
		//初期化
		String index = null;
		Element element = null;
		String id = null;
		
		try{
			Document doc = XMLMainProc.getDocumentDOMParse();
			NodeList nodes = XMLMainProc.getNodeDOMParse(doc, "user");
			for(int i=0; i<nodes.getLength(); i++){
				//付属情報を取得
				element = (Element)nodes.item(i);
				id = element.getAttribute("id");
				if(id.equals(chkId)){
					index = String.valueOf(i);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return index;
	}
	
	
	/*
	 * 変更モードチェック
	 * 戻り値：true(正常値)、false(異常値)
	 */
	protected static boolean isUpdateMode(String inputType){
		//初期化
		boolean isUpdateMode = false;
		if(("0".equals(inputType)) || ("1".equals(inputType))){
			isUpdateMode = true;
		}
		return isUpdateMode;
	}
}
