package com.google.entity.dto;

import com.google.base.entity.BaseDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public class RewardsAndPunishmentsRecordDTO extends BaseDto {
    private long id;
    private Long studentId;
    private String rePuName;
    private String rePuDesc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rePuDate;
    private Integer isDelete;
    private String updateBy;
    private Timestamp updateAt;

    private String studentRealName;
    private String studentNo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
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

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
