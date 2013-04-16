package jp.co.useradmin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class ShowUserList implements User{
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	//各フィールド
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊	
	private Element element;
	
	//ユーザー情報保持
	protected String uId;
	protected String uName;
	protected int uAge;
	protected String uBirthDay;
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊

	
	//ユーザー情報の取得
	protected void getUserMain(NodeList nodes, int index){
		//<user>タグのエレメントを取得
		element = (Element)nodes.item(index);
		this.uId = this.getId();
		this.uName = this.getName();
		this.uAge = this.getAge();
		
		//SimpleDateFormatインスタンス取得
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.uBirthDay = format.format(getBirthDate());
	}

	
	//ユーザーID取得処理
	@Override
	public String getId() {
		return element.getAttribute("id");
	}

	
	//ユーザー名取得処理
	@Override
	public String getName() {
		return this.getUserInfoData("name");
	}

	
	//年齢取得処理
	@Override
	public int getAge() {
		String age = this.getUserInfoData("age");
		return Integer.parseInt(age);
	}

	
	//生年月日取得処理
	@Override
	public Date getBirthDate() {
		Date date = null;
		String dateStr = this.getUserInfoData("birthday");
		try{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.parse(dateStr);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	
	
	//<user>タグ内のユーザー情報取得
	private String getUserInfoData(String tagName){
		NodeList list = element.getElementsByTagName(tagName);
		Element element = (Element)list.item(0);
		return element.getFirstChild().getNodeValue();
	}
}
