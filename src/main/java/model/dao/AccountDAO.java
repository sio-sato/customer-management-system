package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.AccountBean;

public class AccountDAO {
	//	アカウントの権限を取得する
	public List<AccountBean> getAllAccount() throws SQLException, ClassNotFoundException {

		List<AccountBean> Account = new ArrayList<AccountBean>();
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM role")) {

			while (rs.next()) {
				int id = rs.getInt("role_id");
				String name = rs.getString("role_name");
				AccountBean account = new AccountBean();
				account.setRoleId(id);
				account.setRoleName(name);
				Account.add(account);

			}
		}
		return Account;
	}

}
