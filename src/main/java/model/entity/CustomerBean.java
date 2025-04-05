package model.entity;

import java.io.Serializable;

public class CustomerBean implements Serializable {
	private int customerId;
	private String customerName;
	private String customerNameKana;
	private String postalCode;
	private String address;
	private String areaCode;
	private String contactPersonName;
	private String contactPersonNameKana;
	private String contactPersonTel;
	private String userId;
	private String areaName;
	private String userName;

	public CustomerBean() {

	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNameKana() {
		return customerNameKana;
	}

	public void setCustomerNameKana(String customerNameKana) {
		this.customerNameKana = customerNameKana;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonNameKana() {
		return contactPersonNameKana;
	}

	public void setContactPersonNameKana(String contactPersonNameKana) {
		this.contactPersonNameKana = contactPersonNameKana;
	}

	public String getContactPersonTel() {
		return contactPersonTel;
	}

	public void setContactPersonTel(String contactPersonTel) {
		this.contactPersonTel = contactPersonTel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
