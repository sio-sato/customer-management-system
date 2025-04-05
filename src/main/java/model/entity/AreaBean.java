package model.entity;

import java.io.Serializable;

public class AreaBean implements Serializable {

	private String areaCode;
	private String areaName;

	public AreaBean() {

	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
