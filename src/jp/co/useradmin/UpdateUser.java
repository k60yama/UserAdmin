package jp.co.useradmin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateUser implements User{
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	//各フィールド
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	//ユーザー情報保持
	//private final String[] infoTag = new String[]{"名前", "年齢", "生年月日"};
	//private HashMap<String, String> beforeDataMap = new HashMap<String, String>(); 
	protected String uId;
	protected String uName;
	protected int uAge;
	protected String uBirthDay;
	
	//各インスタンス保持
	private UserStoreMain _us;
	private ShowUserList _su;
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	
	
	//コンストラクタ
	protected UpdateUser(UserStoreMain us){
		this._us = us;
	}
	
	
	//変更メイン処理
	protected void updateUserMain() throws IOException{
		//ユーザーID存在チェック
		String index = ValuesCheck.isUserId(InputOperation.inputGetUserId("変更"));
		if(index == null){
			System.out.println("XMLファイルに入力されたユーザーIDが見つかりませんでした。");
			return ;	//メソッド終了
		}		
		
		//ShowUserListインスタンス取得
		this._su = (ShowUserList)this._us.getUser(index);
		
		//ユーザー情報取得
		this.uId = this.getId();			//ユーザーID
		this.uName = this.getName();		//ユーザー名
		
		
		//XML変更処理へ
		this._us.updateUser(this);
	}
	
	/*
	//ユーザー変更処理
	private void updateUserInfo(){
		//旧データ設定
		String[] oldData = new String[]{
			this._su.uName, String.valueOf(this._su.uAge), this._su.uBirthDay	
		};
		for(int i=0; i<oldData.length; i++){
			System.out.println();
			System.out.println(this.infoTag[i] + "を変更しますか？");
			System.out.println("現在の" + this.infoTag[i] + "：" + oldData[i]);
			System.out.println("[0]変更する　[1]変更しない");
			System.out.print("上記番号を入力して下さい ：");
		}
	}
	*/
	
	@Override
	public String getId() {
		return this._su.uId;
	}

	
	@Override
	public String getName() {
		
		
		
		
		return this._su.uName;
	}

	
	@Override
	public int getAge() {
		return this._su.uAge;
	}

	
	@Override
	public Date getBirthDate() {
		//初期化
		Date birthday = null;
		try{
			//SimpleDateFormatインスタンス生成
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			birthday = format.parse(this._su.uBirthDay);
		}catch(Exception e){
			e.printStackTrace();
		}
		return birthday;
	}
	
	/*
	private String getUpdateFlag(String tagName, String oldData){
		System.out.println();
		System.out.println(tagName + "を変更しますか？");
		System.out.println("現在の" + tagName + "：" + oldData);
		System.out.println("[0]変更する　[1]変更しない");
		System.out.print("上記番号を入力して下さい ：");
	}
	*/
}
