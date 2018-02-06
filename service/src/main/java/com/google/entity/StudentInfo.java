package com.google.entity;

import java.util.Date;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class StudentInfo {
    private long id;
    private long userId;
    private String studentRealName;
    private Integer studentGender;
    private Date studentBirthDate;
    private String studentNativePlace;
    private String studentPlaceGoTo;
    private String studentHealthStatus;
    private long studentClassId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStudentRealName() {
        return studentRealName;
    }

    public void setStudentRealName(String studentRealName) {
        this.studentRealName = studentRealName;
    }

    public Integer getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(Integer studentGender) {
        this.studentGender = studentGender;
    }

    public Date getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(Date studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public String getStudentNativePlace() {
        return studentNativePlace;
    }

    public void setStudentNativePlace(String studentNativePlace) {
        this.studentNativePlace = studentNativePlace;
    }

    public String getStudentPlaceGoTo() {
        return studentPlaceGoTo;
    }

    public void setStudentPlaceGoTo(String studentPlaceGoTo) {
        this.studentPlaceGoTo = studentPlaceGoTo;
    }

    public String getStudentHealthStatus() {
        return studentHealthStatus;
    }

    public void setStudentHealthStatus(String studentHealthStatus) {
        this.studentHealthStatus = studentHealthStatus;
    }

    public long getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(long studentClassId) {
        this.studentClassId = studentClassId;
    }
}
