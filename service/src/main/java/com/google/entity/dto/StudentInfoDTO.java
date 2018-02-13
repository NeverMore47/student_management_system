package com.google.entity.dto;

import com.google.base.entity.BaseDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public class StudentInfoDTO extends BaseDto {
    private long id;
    private long userId;
    private String studentNo;
    private String studentRealName;
    private Integer studentGender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date studentBirthDate;
    private String studentNativePlace;
    private String studentPlaceGoTo;
    private String studentHealthStatus;
    private Long studentClassId;

    private String internshipReportPath;

    public StudentInfoDTO() {
    }

    public StudentInfoDTO(String studentNo, String studentRealName, Integer studentGender, Date studentBirthDate, String studentNativePlace, String studentPlaceGoTo, String studentHealthStatus, Long studentClassId) {
        this.studentNo = studentNo;
        this.studentRealName = studentRealName;
        this.studentGender = studentGender;
        this.studentBirthDate = studentBirthDate;
        this.studentNativePlace = studentNativePlace;
        this.studentPlaceGoTo = studentPlaceGoTo;
        this.studentHealthStatus = studentHealthStatus;
        this.studentClassId = studentClassId;
    }

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

    public Long getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Long studentClassId) {
        this.studentClassId = studentClassId;
    }

    public String getInternshipReportPath() {
        return internshipReportPath;
    }

    public void setInternshipReportPath(String internshipReportPath) {
        this.internshipReportPath = internshipReportPath;
    }
}
