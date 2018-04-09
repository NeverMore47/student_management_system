package com.google.entity.dto;

import java.util.Date;

import com.google.base.entity.BaseDto;

public class StudentExtendInfoDTO extends BaseDto {
	private long id;
	private long studentId;
	private String studentPhoneNum;
	private String studentAdress;
	private String politicalStatus;
	private double averagePoint;
	private Date graduationDate;
	private String graduationNo;

	private String studentNo;
	private String studentRealName;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
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

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentRealName() {
		return studentRealName;
	}

	public void setStudentRealName(String studentRealName) {
		this.studentRealName = studentRealName;
	}
	
	
}