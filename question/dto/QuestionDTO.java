package com.xyz.testengine.question.dto;

public class QuestionDTO {
	@Override
	public String toString() {
		return "QuestionDTO [Id=" + Id + ", name=" + name + ", ans1=" + ans1 + ", ans2=" + ans2 + ", ans3=" + ans3
				+ ", ans4=" + ans4 + ", rans=" + rans + ", score=" + score + ", yourAns=" + yourAns + "]";
	}
	private int Id;
	private String name;
	private String ans1;
	private String ans2;
	private String ans3;
	private String ans4;
	private String rans;
	private int score;
	private String yourAns;
	public String getYourAns() {
		return yourAns;
	}
	public void setYourAns(String yourAns) {
		this.yourAns = yourAns;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getAns3() {
		return ans3;
	}
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	public String getAns4() {
		return ans4;
	}
	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}
	public String getRans() {
		return rans;
	}
	public void setRans(String rans) {
		this.rans = rans;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	

}
