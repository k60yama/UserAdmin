package jp.co.useradmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class UserStoreMain implements UserStore {
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	//各フィールド
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊	
	//モード別フラグ管理
	private static final int ADD_MODE = 0;
	private static final int UPDATE_MODE = 1;
	private static final int DELETE_MODE = 2;
	
	//処理別の情報
	private String _modeName;	//処理モード名
	private int _mode;			//処理モード
	
	//ユーザーリスト
	private List<User> uList; 
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊	

	
	//コンストラクタ
	protected UserStoreMain(String modeName, int mode){
		this._modeName = modeName;
		this._mode = mode;
	}
	
	
	//モード別処理
	protected void modeMainProc() throws IOException{
		//処理開始メッセージ
		System.out.println();
		System.out.println("========== " + this._modeName + " ==========");
		
		//追加処理以外は一覧を表示させる
		if(this._mode != ADD_MODE){
			//XMLファイルチェック
			if(!ValuesCheck.isXMLFile()){
				System.out.println("XMLファイルが作成されていません。");
				return ;
			}else{
				//ユーザー一覧表示
				this.uList = this.getUserList();	
				if(this.uList == null){
					System.out.println("ユーザー一覧表示で異常が発生しました。");
					return ;
				}
			}
		}
		
		//各処理モードへ
		switch(this._mode){
		case ADD_MODE:
			//ユーザー追加メイン処理へ
			AddUser add = new AddUser(this);
			add.addUserMain();				
			break;
		case UPDATE_MODE:
			//ユーザー変更メイン処理へ
			UpdateUser update = new UpdateUser(this);
			update.updateUserMain();
			break;
		case DELETE_MODE:
			//ユーザー削除メイン処理へ
			DeleteUser delete = new DeleteUser(this);
			delete.deleteUserMain();			
			break;
		}
	}
	
	
	@Override
	//XMLファイルにユーザー情報を追加
	public void createUser(User user) throws IOException {
		XMLMainProc.addUserInfo((AddUser)user);
	}

	
	@Override
	public void updateUser(User user) throws IOException {
		//XMLファイルを更新する処理
	}

	
	//XMLファイルの内容を削除する処理
	@Override
	public void removeUser(String id) throws IOException {
		XMLMainProc.deleteUserInfo(id);
	}

	
	//指定されたユーザーIDの情報を取得
	@Override
	public User getUser(String id) throws IOException {
		return this.uList.get(Integer.parseInt(id));
	}

	
	//ユーザー情報の取得処理
	@Override
	public List<User> getUserList() throws IOException {
		//初期化
		List<User> usersArray = null;
		try{
			//ArrayListインスタンスを生成する
			usersArray = new ArrayList<User>();
			
			//<user>タグのノードリストを取得する
			Document doc = XMLMainProc.getDocumentDOMParse();
			NodeList nodes = XMLMainProc.getNodeDOMParse(doc, "user");
			for(int i=0; i<nodes.getLength(); i++){
				//ShowUserListインスタンス生成
				ShowUserList uList = new ShowUserList();
				uList.getUserMain(nodes, i);
				
				//ユーザー情報表示
				System.out.print("ユーザーID: " + uList.uId + "　");
				System.out.print("名前: " + uList.uName + "　");
				System.out.print("年齢: " + uList.uAge + "　");
				System.out.println("生年月日: " + uList.uBirthDay);
				usersArray.add(uList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return usersArray;
	}
}

