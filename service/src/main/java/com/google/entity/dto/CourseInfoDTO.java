package com.google.entity.dto;

import com.google.base.entity.BaseDto;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class CourseInfoDTO extends BaseDto {
    private long id;
    private String courseName;
    private String courseTeacherName;
    private Double courseCredit;
    private long courseProviderClassId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacherName() {
        return courseTeacherName;
    }

    public void setCourseTeacherName(String courseTeacherName) {
        this.courseTeacherName = courseTeacherName;
    }

    public Double getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Double courseCredit) {
        this.courseCredit = courseCredit;
    }

    public long getCourseProviderClassId() {
        return courseProviderClassId;
    }

    public void setCourseProviderClassId(long courseProviderClassId) {
        this.courseProviderClassId = courseProviderClassId;
    }
}
