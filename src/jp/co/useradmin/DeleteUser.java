package jp.co.useradmin;

import java.io.IOException;


public class DeleteUser {
	//インスタンス格納
	private UserStore _us;
	
	
	//コンストラクタ
	protected DeleteUser(UserStoreMain us){
		this._us = us;
	}
	
	
	//削除メイン処理
	protected void deleteUserMain() throws IOException{
		//ユーザーID存在チェック処理
		String index = ValuesCheck.isUserId(InputOperation.inputGetUserId("削除"));
		if(index == null){
			System.out.println("XMLファイルに入力されたユーザーIDが見つかりませんでした。");
			return ;	//メソッド終了
		}
		
		//ShowUserListインスタンス取得
		ShowUserList uList = (ShowUserList)this._us.getUser(index);
		this._us.removeUser(uList.uId);
	}
}
