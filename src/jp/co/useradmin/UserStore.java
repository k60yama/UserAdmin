package jp.co.useradmin;

import java.io.IOException;
import java.util.List;

public interface UserStore {
	public void createUser(User user) throws IOException;

	public void updateUser(User user) throws IOException;

	public void removeUser(String id) throws IOException;

	public User getUser(String id) throws IOException;

	public List<User> getUserList() throws IOException;
}
