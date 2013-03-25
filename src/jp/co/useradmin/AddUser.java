package jp.co.useradmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class AddUser implements User{
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	//各フィールド
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	//ユーザーID管理ファイル
	private static final String FILE_NAME = "UID/currentUserID.txt";
	
	//ユーザー情報保持
	protected String uId;
	protected String uName;
	protected int uAge;
	protected Date uBirthDay;
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	
	
	//ユーザー情報取得メイン処理(追加)
	protected void addUserMainProc(){
		this.uName = this.getName();			//名前取得
		this.uAge = this.getAge();				//年齢取得
		this.uBirthDay = this.getBirthDate();	//生年月日
		this.uId = this.getId();				//ユーザーID
	}
	
	//ユーザーID取得処理
	@Override
	public String getId() {
		//初期化
		String lastUID = "0";
		File file = new File(FILE_NAME);			//ファイルチェック
		if(file.exists()){
			String getId = this.getLastUserID();	//最後に採番したユーザーID取得
			if(getId != null){
				//ユーザーIDのインクリメント
				int increment = Integer.parseInt(getId) + 1;
				lastUID = String.valueOf(increment);
			}
		}
		//ファイル書き込み
		this.writeUserID(lastUID);
		return lastUID;
	}
	
	
	//最後に採番したユーザーID取得処理
	private String getLastUserID(){
		//初期化
		InputStreamReader in = null;
		BufferedReader br = null;
		String lastUID = null;
		try{
			in = new InputStreamReader(new FileInputStream(FILE_NAME));	//InputStreamReaderインスタンス生成
			br = new BufferedReader(in);	//BufferedReaderインスタンス生成
			lastUID = br.readLine();		//ユーザーIDを取得
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			//後処理
			try{
				if(br != null){	br.close();}
				if(in != null){ in.close();}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return lastUID;
	}
	
	
	//採番したユーザーIDをファイルに書き込む
	private void writeUserID(String uid){
		//初期化
		OutputStreamWriter out = null;
		PrintWriter pw = null;
		try{
			out = new OutputStreamWriter(new FileOutputStream(FILE_NAME));
			pw = new PrintWriter(out);
			pw.println(uid);	//IDファイル書き込み
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			//後処理
			try {
				if(pw != null){ pw.close();}
				if(out != null){ out.close();}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//ユーザー名取得処理
	@Override
	public String getName() {
		//初期化
		boolean isGetName = true;
		String userName;
		System.out.print("追加するユーザーの名前を入力してください：");
		do{	
			//未入力チェック
			userName = InputOperation.userInputProc();
			if(ValuesCheck.isBlank(userName)){
				isGetName = false;	//フラグ更新
			}else{
				System.out.print("何も入力されていません。ユーザー名を入力してください：");
			}
		}while(isGetName);
		return userName;
	}

	
	//年齢取得処理
	@Override
	public int getAge() {
		//初期化
		boolean isGetAge = true;
		String userAge;
		System.out.print("追加するユーザーの年齢を入力してください：");
		do{
			//未入力チェック
			userAge = InputOperation.userInputProc();
			if(ValuesCheck.isBlank(userAge)){
				//数値チェック
				if(ValuesCheck.isNumericValue(userAge)){
					isGetAge = false;	//フラグ更新
				}else{
					System.out.print("数字以外が入力されました。数字で年齢を入力してください：");
				}
			}else{
				System.out.print("何も入力されていません。年齢を入力してください：");
			}
		}while(isGetAge);
		return Integer.parseInt(userAge);
	}

	
	//生年月日取得処理
	@Override
	public Date getBirthDate() {
		//初期化
		boolean isGetBirthDate = true;
		String userBirthDayStr;
		Date userBirthDay = null;
		System.out.print("追加するユーザーの生年月日を例に従って入力してください。(例)1990-03-01：");
		do{
			//未入力チェック
			userBirthDayStr = InputOperation.userInputProc();
			if(ValuesCheck.isBlank(userBirthDayStr)){
				//日付形式チェック
				userBirthDay = ValuesCheck.isDate(userBirthDayStr);
				if(userBirthDay != null){
					isGetBirthDate = false;		//フラグ更新
				}else{
					System.out.print("正しい日付形式ではありません。例に従って入力してください。(例)1990-03-01：");
				}
			}else{
				System.out.print("何も入力されていません。生年月日を入力してください：");
			}
		}while(isGetBirthDate);
		return userBirthDay;
	}
}
