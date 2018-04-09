package com.google.entity.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public class StudentInfoVO {
    private long id;
    private long userId;
    private String studentNo;
    private String studentRealName;
    private Integer studentGender;
    private Date studentBirthDate;
    private String studentNativePlace;
    private String studentPlaceGoTo;
    private String studentHealthStatus;
    private long studentClassId;
    private long departmentId;

    private String internshipReportPath;

    private List<RewardsAndPunishmentsRecordVO> raprList;
	private List<ComprehensiveEvaluationResultVO> cerList;
    
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

    public long getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(long studentClassId) {
        this.studentClassId = studentClassId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getInternshipReportPath() {
        return internshipReportPath;
    }

    public void setInternshipReportPath(String internshipReportPath) {
        this.internshipReportPath = internshipReportPath;
    }

	public List<RewardsAndPunishmentsRecordVO> getRaprList() {
		return raprList;
	}

	public void setRaprList(List<RewardsAndPunishmentsRecordVO> raprList) {
		this.raprList = raprList;
	}

	public List<ComprehensiveEvaluationResultVO> getCerList() {
		return cerList;
	}

	public void setCerList(List<ComprehensiveEvaluationResultVO> cerList) {
		this.cerList = cerList;
	}
}
