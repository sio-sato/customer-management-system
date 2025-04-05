package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.CustomerBean;

public class CustomerDAO {

	//指定したIDの顧客を返す
	public CustomerBean getCustomer(int customerId) throws SQLException, ClassNotFoundException {

		CustomerBean customer = new CustomerBean();
		String sql = """
				SELECT
				  C.customer_name,
				  C.customer_name_kana,
				  C.postal_code,
				  C.address,
				  A.area_code,
				  C.contact_person_name,
				  C.contact_person_name_kana,
				  C.contact_person_tel,
				  U.user_name
				FROM
				  m_customer C
				JOIN
				  m_area A ON C.area_code = A.area_code
				JOIN
				  m_user U ON C.user_id = U.user_id
				WHERE
				  C.customer_id = ?
				""";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				customer.setCustomerName(res.getString("customer_name"));
				customer.setCustomerNameKana(res.getString("customer_name_kana"));
				customer.setPostalCode(res.getString("postal_code"));
				customer.setAddress(res.getString("address"));
				customer.setAreaCode(res.getString("area_code"));
				customer.setContactPersonName(res.getString("contact_person_name"));
				customer.setContactPersonNameKana(res.getString("contact_person_name_kana"));
				customer.setContactPersonTel(res.getString("contact_person_tel"));
				customer.setUserName(res.getString("user_name"));

			} else { //デバック
				System.out.println("指定された customerId は見つかりませんでした: " + customerId);
			}

		}
		return customer;

	}

	//	全ての顧客リストを返す
	public List<CustomerBean> getAllCustomer() throws SQLException, ClassNotFoundException {
		List<CustomerBean> customerList = new ArrayList<CustomerBean>();
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("""
						SELECT
						  C.customer_id,
						  C.customer_name,
						  C.customer_name_kana,
						  C.postal_code,
						  C.address,
						  A.area_name,
						  C.contact_person_name,
						  C.contact_person_name_kana,
						  C.contact_person_tel,
						  U.user_name
						FROM
						  m_customer C
						JOIN
						  m_area A ON C.area_code = A.area_code
						JOIN
						  m_user U ON C.user_id = U.user_id
						ORDER BY
						  C.customer_id
						""")) {
			while (res.next()) {
				int id = res.getInt("customer_id");
				String name = res.getString("customer_name");
				String nameKana = res.getString("customer_name_kana");
				String post = res.getString("postal_code");
				String address = res.getString("address");
				String area = res.getString("area_name");
				String Pname = res.getString("contact_person_name");
				String PnameKana = res.getString("contact_person_name_kana");
				String tel = res.getString("contact_person_tel");
				String user = res.getString("user_name");

				CustomerBean customer = new CustomerBean();
				customer.setCustomerId(id);
				customer.setCustomerName(name);
				customer.setCustomerNameKana(nameKana);
				customer.setPostalCode(post);
				customer.setAddress(address);
				customer.setAreaName(area);
				customer.setContactPersonName(Pname);
				customer.setContactPersonNameKana(PnameKana);
				customer.setContactPersonTel(tel);
				customer.setUserName(user);
				customerList.add(customer);

			}

		}
		return customerList;
	}

	//	顧客情報を登録する
	public int addCustomer(CustomerBean customer) throws SQLException, ClassNotFoundException {
		int count = 0;
		String sql = """
				INSERT INTO
					m_customer(customer_name,customer_name_kana,postal_code,address,area_code,contact_person_name,contact_person_name_kana,contact_person_tel,user_id)
				VALUE
					(?,?,?,?,?,?,?,?,?)
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			String name = customer.getCustomerName();
			String nameKana = customer.getCustomerNameKana();
			String post = customer.getPostalCode();
			String address = customer.getAddress();
			String areaCode = customer.getAreaCode();
			String Pname = customer.getContactPersonName();
			String PnameKana = customer.getContactPersonNameKana();
			String tel = customer.getContactPersonTel();
			String userId = customer.getUserId();

			pstmt.setString(1, name);
			pstmt.setString(2, nameKana);
			pstmt.setString(3, post);
			pstmt.setString(4, address);
			pstmt.setString(5, areaCode);
			pstmt.setString(6, Pname);
			pstmt.setString(7, PnameKana);
			pstmt.setString(8, tel);
			pstmt.setString(9, userId);

			count = pstmt.executeUpdate();

		}
		return count;

	}

	//	指定された内容の顧客情報を更新する
	public int updateCustomer(CustomerBean customer) throws SQLException, ClassNotFoundException {
		int count = 0; //処理件数を表す
		String sql = """
				UPDATE
					m_customer
				SET
					customer_name = ?,
					customer_name_kana = ?,
					postal_code = ?,
					address = ?,
					area_code = ?,
					contact_person_name = ?,
					contact_person_name_kana = ?,
					contact_person_tel = ?,
					user_id = ?
				WHERE
					customer_id = ?
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			int id = customer.getCustomerId();
			String name = customer.getCustomerName();
			String nameKana = customer.getCustomerNameKana();
			String post = customer.getPostalCode();
			String address = customer.getAddress();
			String areaCode = customer.getAreaCode();
			String Pname = customer.getContactPersonName();
			String PnameKana = customer.getContactPersonNameKana();
			String tel = customer.getContactPersonTel();
			String userId = customer.getUserId();

			pstmt.setString(1, name);
			pstmt.setString(2, nameKana);
			pstmt.setString(3, post);
			pstmt.setString(4, address);
			pstmt.setString(5, areaCode);
			pstmt.setString(6, Pname);
			pstmt.setString(7, PnameKana);
			pstmt.setString(8, tel);
			pstmt.setString(9, userId);
			pstmt.setInt(10, id);

			count = pstmt.executeUpdate();

		}
		return count;
	}

	//	指定されたidの顧客情報を削除する
	public int deleteCustomer(int customerId) throws SQLException, ClassNotFoundException {
		int count = 0;
		String sql = """
				DELETE FROM
					m_customer
				WHERE
					customer_id = ?
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return count;
		}

	}

	//	検索されたキーワードと一致する顧客情報を表示する
	public List<CustomerBean> searchCustomer(CustomerBean customer) throws SQLException, ClassNotFoundException {
		List<CustomerBean> customerList = new ArrayList<CustomerBean>();
		String sql = """
				SELECT
					C.customer_id,
					C.customer_name,
				  	C.customer_name_kana,
				  	C.postal_code,
				  	C.address,
				  	A.area_name,
				  	C.contact_person_name,
					C.contact_person_name_kana,
				  	C.contact_person_tel,
					U.user_name
				FROM
				  	m_customer C
				JOIN
				  	m_area A ON C.area_code = A.area_code
				JOIN
					m_user U ON C.user_id = U.user_id
				WHERE
					C.customer_id LIKE ?
				OR
					C.customer_name LIKE ?
				OR
				 	C.customer_name_kana LIKE ?
				OR
					C.postal_code LIKE ?
				OR
					C.address LIKE ?
				OR
				  	A.area_name LIKE ?
				OR
				  	C.contact_person_name LIKE ?
				OR
				  	C.contact_person_name_kana LIKE ?
				OR
				  	C.contact_person_tel LIKE ?
				OR
					U.user_name LIKE ?
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			int id = customer.getCustomerId();
			String name = customer.getCustomerName();
			String nameKana = customer.getCustomerNameKana();
			String post = customer.getPostalCode();
			String address = customer.getAddress();
			String areaName = customer.getAreaName();
			String Pname = customer.getContactPersonName();
			String PnameKana = customer.getContactPersonNameKana();
			String tel = customer.getContactPersonTel();
			String userName = customer.getUserName();
			System.out.println("dao" + name);
			System.out.println("dao" + post);
			System.out.println("dao" + Pname);
			System.out.println("dao" + tel);
			System.out.println("dao" + userName);

			pstmt.setString(1, "%" + id + "%");
			pstmt.setString(2, "%" + name + "%");
			pstmt.setString(3, "%" + nameKana + "%");
			pstmt.setString(4, "%" + post + "%");
			pstmt.setString(5, "%" + address + "%");
			pstmt.setString(6, "%" + areaName + "%");
			pstmt.setString(7, "%" + Pname + "%");
			pstmt.setString(8, "%" + PnameKana + "%");
			pstmt.setString(9, "%" + tel + "%");
			pstmt.setString(10, "%" + userName + "%");
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				CustomerBean Customer = new CustomerBean();
				Customer.setCustomerId(res.getInt("customer_id"));
				Customer.setCustomerName(res.getString("customer_name"));
				Customer.setCustomerNameKana(res.getString("customer_name_kana"));
				Customer.setPostalCode(res.getString("postal_code"));
				Customer.setAddress(res.getString("address"));
				Customer.setAreaName(res.getString("area_name"));
				Customer.setContactPersonName(res.getString("contact_person_name"));
				Customer.setContactPersonNameKana(res.getString("contact_person_name_kana"));
				Customer.setContactPersonTel(res.getString("contact_person_tel"));
				Customer.setUserName(res.getString("user_name"));
				customerList.add(Customer);
			}
		}
		return customerList;

	}

}
