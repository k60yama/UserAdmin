package jp.co.useradmin;


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

public class XMLMainProc {
	//XMLファイル名
	protected static final String FILE_NAME = "userInfo.xml";
	
	
	/*
	 * XML作成処理
	 */
	private static Document createXMLInit() throws ParserConfigurationException{
		//DocumentBuilderFactoryインスタンス取得
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		//DocumentBuilderインスタンス取得
		DocumentBuilder db = dbFactory.newDocumentBuilder();
		return db.newDocument();	//Document生成
	}
	
	
	/*
	 * XML取得処理(DOMパース)
	 */
	protected static Document getDocumentDOMParse() throws Exception{
		//DOMParserのインスタンス生成
		DOMParser parser = new DOMParser();
		parser.parse(FILE_NAME);		//XMLファイルのパース
		return parser.getDocument();	//Documentノード取得
	}
	
	
	/*
	 * ユーザー追加処理用
	 */
	protected static void addUserInfo(AddUser add){
		try{
			//初期化
			Document doc = null;
			Element users = null;
			boolean check = ValuesCheck.isXMLFile();	//XMLファイル存在チェック
			
			//root要素<users>タグを追加
			if(check){
				doc = getDocumentDOMParse();			//XMLファイル取得
				users = doc.getDocumentElement();
 			}else{
				doc = createXMLInit();					//XML作成処理
				users = doc.createElement("users");
				doc.appendChild(users);
			}
			
			//<users>タグに<user>タグと属性の追加
			Element user = doc.createElement("user");
			user.setAttribute("id", add.uId);
			users.insertBefore(user, users.getLastChild());
			
			//<name>タグ
			Element name = doc.createElement("name");							//name要素
			Text nameTxt = doc.createTextNode(add.uName);						//name要素内のテキスト
			name.appendChild(nameTxt);
			user.appendChild(name);
			
			//<age>タグ
			Element age = doc.createElement("age");								//age要素
			Text ageTxt = doc.createTextNode(String.valueOf(add.uAge));			//age要素内のテキスト
			age.appendChild(ageTxt);
			user.appendChild(age);
			
			//生年月日形式変更
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//<birthday>タグ
			Element birthday = doc.createElement("birthday");					//birthday要素
			Text birthdayTxt = doc.createTextNode(sdf.format(add.uBirthDay));	//birthday要素内のテキスト
			birthday.appendChild(birthdayTxt);
			user.appendChild(birthday);
			
			//XMLファイル出力
			exportXMLFile(doc, check);
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/*
	 * ユーザー削除処理用
	 */
	protected static void deleteUserInfo(String id){
		//初期化
		Document doc = null;
		Element user = null;
		String uId = null;
		boolean check = true;
		
		//userノード取得
		try{
			doc = getDocumentDOMParse();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ユーザー削除処理でエラーが発生しました。");
			return ;
		}
		
		//指定ノード削除処理
		NodeList nodes = getNodeDOMParse(doc, "user");
		for(int i=0; i<nodes.getLength(); i++){
			user = (Element)nodes.item(i);
			uId = user.getAttribute("id");
			if(uId.equals(id)){
				Node parent = user.getParentNode();
				parent.removeChild(user);
				exportXMLFile(doc, check);		//XMLファイル出力
			}
		}
	}
	
	
	/*
	 * XMLファイル形式でエクスポート
	 */
	private static void exportXMLFile(Document doc, boolean check){
		try{	
			//TransformerFactoryインスタンス取得
			TransformerFactory tFactory = TransformerFactory.newInstance();
			
			//Transformerインスタンス取得
			Transformer tFormer = tFactory.newTransformer();
			
			//プロパティの設定
			tFormer.setOutputProperty(OutputKeys.METHOD, "xml");							//出力形式
			tFormer.setOutputProperty(OutputKeys.INDENT, "yes");							//改行の有効化
			tFormer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "2");	//インデントの文字数
			
			//XMLファイルチェック
			if(check){
				tFormer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");			//XML宣言
			}
			
			//FileOutputStreamインスタンス生成
			FileOutputStream out = new FileOutputStream(FILE_NAME, false);
			tFormer.transform(new DOMSource(doc), new StreamResult(out));
		}catch(TransformerException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 指定ノードの取得
	 */
	protected static NodeList getNodeDOMParse(Document doc, String nodeName){
		return doc.getElementsByTagName(nodeName);
	}
}
