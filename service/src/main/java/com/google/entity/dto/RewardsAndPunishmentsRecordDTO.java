package com.google.entity.dto;

import com.google.base.entity.BaseDto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class RewardsAndPunishmentsRecordDTO extends BaseDto {
    private long id;
    private long studentId;
    private String RE_PU_NAME;
    private String RE_PU_DESC;
    private Date RE_PU_DATE;
    private Integer isDelete;
    private String updateBy;
    private Timestamp updateAt;

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

    public String getRE_PU_NAME() {
        return RE_PU_NAME;
    }

    public void setRE_PU_NAME(String RE_PU_NAME) {
        this.RE_PU_NAME = RE_PU_NAME;
    }

    public String getRE_PU_DESC() {
        return RE_PU_DESC;
    }

    public void setRE_PU_DESC(String RE_PU_DESC) {
        this.RE_PU_DESC = RE_PU_DESC;
    }

    public Date getRE_PU_DATE() {
        return RE_PU_DATE;
    }

    public void setRE_PU_DATE(Date RE_PU_DATE) {
        this.RE_PU_DATE = RE_PU_DATE;
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
}
