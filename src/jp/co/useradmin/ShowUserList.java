package jp.co.useradmin;

import java.util.Date;


public class ShowUserList implements User{

	//ユーザー参照メイン処理
	protected void showUserMainProc(){
		System.out.println("ノード数：" + XMLMainProc.getNodeDOMParse("user").getLength());
	}
	
	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public int getAge() {
		return 0;
	}

	@Override
	public Date getBirthDate() {
		return null;
	}
}
