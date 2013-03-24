package jp.co.useradmin;

import java.io.IOException;


public class UserAdminMain {
	//モード別の処理名
	protected static final String ADD_MODE_NAME = "ユーザー追加処理"; 
	protected static final String UPDATE_MODE_NAME = "ユーザー変更処理"; 	
	protected static final String DELETE_MODE_NAME = "ユーザー削除処理"; 	
	protected static final String SHOW_MODE_NAME = "ユーザー一覧参照"; 	
	
	/*
	 * main メソッド
	 * 戻り値なし
	 */
	public static void main(String[] args){
		//ユーザー処理モードを入力させるメッセージ表示
		System.out.println("========== ユーザー処理モード ==========");
		System.out.println("[0] " + ADD_MODE_NAME + "モード");
		System.out.println("[1] " + UPDATE_MODE_NAME + "モード");
		System.out.println("[2] " + DELETE_MODE_NAME + "モード");
		System.out.println("[3] " + SHOW_MODE_NAME + "モード");
		System.out.print("上記処理モードの番号を入力してください ：");
		
		//空白チェック
		String modeTypeStr = InputOperation.userInputProc();	//入力された処理モード取得
		boolean strResult = ValuesCheck.isBlank(modeTypeStr);
		if(!strResult){
			System.out.println("何も入力されていません。処理を終了します。");
			return ; 	//メソッド強制終了			
		}
		
		//数値チェック
		boolean numResult = ValuesCheck.isNumericValue(modeTypeStr);
		if(!numResult){
			System.out.println("数値以外が入力されました。処理を終了します。");
			return ; 	//メソッド強制終了
		}
		
		//処理モードチェック
		int modeType = Integer.parseInt(modeTypeStr);
		String modeName = ValuesCheck.isModeType(modeType);  
		if(modeName == null){
			System.out.println("処理モード以外の番号が入力されたました。処理を終了します。");
			return ; 	//メソッド強制終了
		}
		
		//各処理モードへ
		try {
			modeProcMain(modeName, modeType);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(modeName + "処理で異常が検知されました。処理を終了します。");
			return ;	//メソッド強制終了
		}
		System.out.println(modeName + "が正常終了しました。");	//処理終了メッセージ表示
	}
	
	
	/*
	 * ユーザー操作メイン処理
	 */
	private static void modeProcMain(String modeName, int modeType) throws IOException{
		//UserStoreMainインスタンス生成
		UserStoreMain store = new UserStoreMain(modeName, modeType);
		
		//Userインスタンス取得
		User user = store.getUserInstance();
		if(user instanceof AddUser){
			System.out.println("AddUserです");
			//System.out.println("入力されたユーザー名は" + ((AddUser)user).uName + "です。");
			store.createUser(user);
		}else if(user instanceof UpdateUser){
			System.out.println("UpdateUserです");
		}else if(user instanceof DeleteUser){
			System.out.println("DeleteUserです");
		}else if(user instanceof ShowUserList){
			System.out.println("ShowUserListです");
		}
	}
}
