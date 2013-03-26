package jp.co.useradmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserStoreMain implements UserStore {
	//モード別フラグ管理
	private static final int ADD_MODE = 0;
	private static final int UPDATE_MODE = 1;
	private static final int DELTE_MODE = 2;
	private static final int SHOW_MODE = 3;
	
	//処理別の情報
	private String _modeName;	//処理モード名
	private int _mode;			//処理モード
	
	//コンストラクタ
	protected UserStoreMain(String modeName, int mode){
		this._modeName = modeName;
		this._mode = mode;
	}
	
	//各モード別のインスタンス取得処理
	protected User getUserInstance(){
		//初期化
		User user = null;
		System.out.println("========== " + this._modeName + " ==========");		//処理開始メッセージ
		switch(this._mode){
		case ADD_MODE:
			user = new AddUser();					//AddUserインスタンス生成(仮)
			//((AddUser)user).addUserMainProc();		//追加用のユーザー情報取得
			break;
		case UPDATE_MODE:
			//UpdateUserインスタンス生成(仮)
			user = new UpdateUser();
			break;
		case DELTE_MODE:
			//DeleteUserインスタンス生成(仮)
			user = new DeleteUser();
			break;
		case SHOW_MODE:
			//ShowUserListインスタンス生成(仮)
			user = new ShowUserList();
			break;
		}
		return user;
	}
	
	@Override
	public void createUser(User user) throws IOException {
		AddUser add = (AddUser)user;	//ダウンキャスト
		XMLMainProc.addToXML(add);		//XMLファイルに入力情報を追加
	}

	@Override
	public void updateUser(User user) throws IOException {
		//XMLファイルを更新する処理
	}

	@Override
	public void removeUser(String id) throws IOException {
		//XMLファイルの内容を削除する処理
	}

	@Override
	public User getUser(String id) throws IOException {
		//XMLファイルから仮引数のIDのユーザーを取得する処理
		//変更と削除に用いる
		return null;
	}

	@Override
	public List<User> getUserList() throws IOException {
		//XMLファイルの全ユーザーを取得し、ユーザーリストを作成する。
		//変更、削除と一覧参照に用いる
		List<User> array = new ArrayList<User>();
		
		//ShowUserListインスタンス生成
		ShowUserList uList = new ShowUserList();
		array.add(uList);
		
		return array;
	}
}
