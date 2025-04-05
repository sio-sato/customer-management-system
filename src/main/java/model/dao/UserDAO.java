package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

public class UserDAO {

	//	ログインに使用。指定したユーザーIDから登録されているパスワード、ユーザー名を返す。
	public UserBean getUser(String userId) throws SQLException, ClassNotFoundException {
		UserBean user = null; //ユーザーが見つからなかった場合はnullを返す
		String sql = "SELECT * FROM m_user WHERE user_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				user = new UserBean(); //ユーザーが見つかったらオブジェクトを作成
				user.setUserId(res.getString("user_id"));
				user.setPassword(res.getString("password"));
				user.setUserName(res.getString("user_name"));
				user.setRoleId(res.getInt("role_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	//	ユーザー新規登録
	public int addUser(UserBean user) throws SQLException, ClassNotFoundException {
		int count = 0;
		String sql = """
				INSERT INTO
					m_user(user_id,password,user_name,role_id)
				VALUE
					(?,?,?,?)
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			String id = user.getUserId();
			String pass = user.getPassword();
			String name = user.getUserName();
			int role = user.getRoleId();

			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setInt(4, role);

			count = pstmt.executeUpdate();

		}
		return count;
	}

	//	ユーザー名をプルダウンで表示させる
	public List<UserBean> getAllUser() throws SQLException, ClassNotFoundException {

		List<UserBean> UserList = new ArrayList<UserBean>();
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM m_user")) {

			while (rs.next()) {
				String id = rs.getString("user_id");
				String name = rs.getString("user_name");
				UserBean user = new UserBean();
				user.setUserId(id);
				user.setUserName(name);
				UserList.add(user);

			}
		}
		return UserList;
	}

}
