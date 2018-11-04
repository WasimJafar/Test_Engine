package com.xyz.testengine.user.dto;

import java.util.ArrayList;

public class UserDTO {
	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", password=" + password + ", gender=" + gender + ", dateOfbirth="
				+ dateOfbirth + ", address=" + address + ", city=" + city + ", roleName=" + roleName + ", rights="
				+ rights + "]";
	}
	private String name;
	private String password;
	private String gender;
	private String dateOfbirth;
	private String address;
	private String city;
	private String roleName;
	private ArrayList<RightDTO> rights;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public ArrayList<RightDTO> getRights() {
		return rights;
	}
	public void setRights(ArrayList<RightDTO> rights) {
		this.rights = rights;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfbirth() {
		return dateOfbirth;
	}
	public void setDateOfbirth(String dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
