package com.google.entity;

import java.util.Date;

public class StudentExtendInfo {
	private long id;
	private String studentPhoneNum;
	private String studentAdress;
	private String politicalStatus;
	private double averagePoint;
	private Date graduationDate;
	private String graduationNo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudentPhoneNum() {
		return studentPhoneNum;
	}
	public void setStudentPhoneNum(String studentPhoneNum) {
		this.studentPhoneNum = studentPhoneNum;
	}
	public String getStudentAdress() {
		return studentAdress;
	}
	public void setStudentAdress(String studentAdress) {
		this.studentAdress = studentAdress;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public double getAveragePoint() {
		return averagePoint;
	}
	public void setAveragePoint(double averagePoint) {
		this.averagePoint = averagePoint;
	}
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getGraduationNo() {
		return graduationNo;
	}
	public void setGraduationNo(String graduationNo) {
		this.graduationNo = graduationNo;
	}
	
	
}
