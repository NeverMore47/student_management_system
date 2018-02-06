package com.google.entity.dto;

import com.google.base.BaseDto;

import java.sql.Timestamp;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class ComprehensiveEvaluationResultDTO extends BaseDto {
    private long id;
    private long courseId;
    private long studentId;
    private Double resultScore;
    private String updateBy;
    private Timestamp updateAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Double getResultScore() {
        return resultScore;
    }

    public void setResultScore(Double resultScore) {
        this.resultScore = resultScore;
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
