package org.korea.mvc.dto;

public class StudentDTO {
	private String sno;
	private String name;
	private int major;
	private int score;
	
	public StudentDTO(String sno, String name, int major, int score) {
		super();
		this.sno = sno;
		this.name = name;
		this.major = major;
		this.score = score;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "StudentDTO [sno=" + sno + ", name=" + name + ", major=" + major + ", score=" + score + "]";
	}
	
	
}
