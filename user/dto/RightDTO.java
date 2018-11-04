package com.xyz.testengine.user.dto;

public class RightDTO {
	private String right;
	private String screenName;
	
	public RightDTO(String right, String screenName) {
		this.right=right;
		this.screenName=screenName;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	
}
