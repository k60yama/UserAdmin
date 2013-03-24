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
}
