package jp.co.useradmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
				}else{
					System.out.print("数字以外が入力されました。");
				}
			}else{
				System.out.print("何も入力されていません。");
			}
		}while(isInputId);
		return inputId;
	}
	
	
	//変更フラグ入力処理
	protected static String inputUpdateFlag(String tagName, String oldData){
		//初期化
		boolean isFlag = true;
		String type = null;
		do{
			System.out.println("");
			System.out.println(tagName + "変更しますか？");
			System.out.println("現在の" + tagName + "：" + oldData);
			System.out.println("[0]変更する　[1]変更しない");
			System.out.print("上記番号を入力して下さい ：");
			type = userInputProc();
			//未入力チェック
			if(ValuesCheck.isBlank(type)){
				
			}
		}while(isFlag);
		return null;
	}
}
