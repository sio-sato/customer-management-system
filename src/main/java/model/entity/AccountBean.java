package model.entity;

import java.io.Serializable;

public class AccountBean implements Serializable {
	private int roleId;
	private String roleName;

	public AccountBean() {

	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
