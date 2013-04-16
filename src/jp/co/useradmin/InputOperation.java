package jp.co.useradmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class InputOperation {
	//標準入力
	private static BufferedReader br;
	
	
	//ユーザー入力処理
	protected static String userInputProc(){
		//初期化
		String inputStr = null;
		try{
			if(InputOperation.br == null){
				//BufferedReaderインスタンス生成
				br = new BufferedReader(new InputStreamReader(System.in));
			}
			inputStr = br.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return inputStr;
	}
	
	

	//ユーザーID入力処理
	protected static String inputGetUserId(String mode){
		//初期化
		boolean isInputId = true;
		String inputId = null;
		
		//ユーザーID取得処理
		do{
			System.out.print(mode +"したいユーザーIDを入力してください：");
			inputId = userInputProc();
			//未入力チェック
			if(ValuesCheck.isBlank(inputId)){
				//数値チェック
				if(ValuesCheck.isNumericValue(inputId)){
					isInputId = false;
				}else{System.out.println("数字以外が入力されました。");}
			}else{System.out.println("何も入力されていません。");}
		}while(isInputId);
		return inputId;
	}
	

	//ユーザー名入力処理(追加・変更用)
	protected static String inputGetUserName(String mode){
		//初期化
		boolean isGetName = true;
		String userName = null;
		System.out.print(mode + "するユーザーの名前を入力してください：");
		do{
			userName = userInputProc();
			//未入力チェック
			if(ValuesCheck.isBlank(userName)){
				isGetName = false;	//フラグ更新
			}else{System.out.print("何も入力されていません。ユーザー名を入力してください：");}
		}while(isGetName);
		return userName;
	}
	
	
	//年齢入力処理(追加・変更用)
	protected static String inputGetUserAge(String mode){
		//初期化
		boolean isGetAge = true;
		String userAge = null;
		System.out.print(mode + "するユーザーの年齢を入力してください：");
		do{
			//未入力チェック
			userAge = userInputProc();
			if(ValuesCheck.isBlank(userAge)){
				//数値チェック
				if(ValuesCheck.isNumericValue(userAge)){
					isGetAge = false;	//フラグ更新
				}else{System.out.print("数字以外が入力されました。数字で年齢を入力してください：");}
			}else{System.out.print("何も入力されていません。年齢を入力してください：");}			
		}while(isGetAge);
		return userAge;
	}
	
	
	//生年月日入力処理(追加・変更用)
	protected static Date inputGetUserBirthDay(String mode){
		//初期化
		boolean isGetBirthDay = true;
		Date userBirthDay = null;
		System.out.print(mode + "するユーザーの生年月日を例に従って入力してください。(例)1990-03-01：");
		do{
			//未入力チェック
			String userBirthDayStr = InputOperation.userInputProc();
			if(ValuesCheck.isBlank(userBirthDayStr)){
				//日付形式チェック
				userBirthDay = ValuesCheck.isDate(userBirthDayStr);
				if(userBirthDay != null){
					isGetBirthDay = false;		//フラグ更新
				}else{System.out.print("正しい日付形式ではありません。例に従って入力してください。(例)1990-03-01：");}
			}else{System.out.print("何も入力されていません。生年月日を入力してください：");}			
		}while(isGetBirthDay);
		return userBirthDay;
	}
	
	
	//変更モード入力処理
	protected static String inputUpdateMode(String tagName, String oldData){
		//初期化
		boolean isMode = true;
		String type = null;
		do{
			System.out.println("");
			System.out.println(tagName + "を変更しますか？");
			System.out.println("現在の" + tagName + "：" + oldData);
			System.out.println("[0]変更する　[1]変更しない");
			System.out.print("上記番号を入力して下さい ：");
			type = userInputProc();
			//未入力チェック
			if(ValuesCheck.isBlank(type)){
				//数値チェック
				if(ValuesCheck.isNumericValue(type)){
					//範囲内チェック
					if(ValuesCheck.isUpdateMode(type)){
						isMode = false;
					}else{System.out.println("無効な値が入力されました。");}
				}else{System.out.println("数字以外が入力されました。");}
			}else{System.out.println("何も入力されていません。");}
		}while(isMode);
		return type;
	}
}
