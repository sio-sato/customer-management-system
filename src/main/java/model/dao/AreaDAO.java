package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.AreaBean;

public class AreaDAO {

	//	地区名をプルダウンで表示させる
	public List<AreaBean> getAllArea() throws SQLException, ClassNotFoundException {

		List<AreaBean> Area = new ArrayList<AreaBean>();
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM m_area")) {

			while (rs.next()) {
				String code = rs.getString("area_code");
				String name = rs.getString("area_name");
				AreaBean area = new AreaBean();
				area.setAreaCode(code);
				area.setAreaName(name);
				Area.add(area);

			}
		}
		return Area;
	}

}
