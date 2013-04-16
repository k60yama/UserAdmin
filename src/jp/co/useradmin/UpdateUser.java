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
	protected Date uBirthDay;
	
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
		this.uId = this.getId();				//ユーザーID
		this.uName = this.getName();			//ユーザー名
		this.uAge = this.getAge();				//ユーザー年齢
		this.uBirthDay = this.getBirthDate();	//ユーザー生年月日
		
		//確認
		System.out.println("ユーザーID =　" + this.uId);
		System.out.println("ユーザー名 =　" + this.uName);
		System.out.println("ユーザー年齢 =　" + this.uAge);
		System.out.println("ユーザー生年月日 =　" + this.uBirthDay);
		
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
	
	
	//ユーザーID取得処理
	@Override
	public String getId() {
		return this._su.uId;
	}

	
	//ユーザー名取得処理
	@Override
	public String getName() {
		//初期化
		String name = this._su.uName;
		
		//変更モード選択
		String mode = InputOperation.inputUpdateMode("名前", name);
		if("0".equals(mode)){
			//新ユーザー名入力処理
			name = InputOperation.inputGetUserName("変更");
		}
		return name;
	}

	
	//ユーザー年齢取得処理
	@Override
	public int getAge() {
		//初期化
		int age = this._su.uAge;
		
		//変更モード選択
		String mode = InputOperation.inputUpdateMode("年齢", String.valueOf(age));
		if("0".equals(mode)){
			//新ユーザー年齢入力処理
			age = Integer.valueOf(InputOperation.inputGetUserAge("変更"));
		}
		return age;
	}

	
	//ユーザー生年月日取得処理
	@Override
	public Date getBirthDate() {
		//初期化
		Date birthday = null;
		String birthdayStr = String.valueOf(this._su.uBirthDay);
		try{
			//SimpleDateFormatインスタンス生成
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			//変更モード選択
			String mode = InputOperation.inputUpdateMode("生年月日", birthdayStr);
			if("0".equals(mode)){
				//新ユーザー生年月日入力処理
				birthdayStr = format.format(InputOperation.inputGetUserBirthDay("変更"));
			}
			birthday = format.parse(birthdayStr);		//Date変換
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
