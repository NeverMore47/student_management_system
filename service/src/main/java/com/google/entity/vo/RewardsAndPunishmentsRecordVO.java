package com.google.entity.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public class RewardsAndPunishmentsRecordVO {
    private long id;
    private long studentId;
    private String rePuName;
    private String rePuDesc;
    private Date rePuDate;
    private Integer isDelete;
    private String updateBy;
    private Timestamp updateAt;

    private String studentRealName;
    private Long classId;
    private Long departmentId;
    private String studentNo;

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

    public String getRePuName() {
        return rePuName;
    }

    public void setRePuName(String rePuName) {
        this.rePuName = rePuName;
    }

    public String getRePuDesc() {
        return rePuDesc;
    }

    public void setRePuDesc(String rePuDesc) {
        this.rePuDesc = rePuDesc;
    }

    public Date getRePuDate() {
        return rePuDate;
    }

    public void setRePuDate(Date rePuDate) {
        this.rePuDate = rePuDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public String getStudentRealName() {
        return studentRealName;
    }

    public void setStudentRealName(String studentRealName) {
        this.studentRealName = studentRealName;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
