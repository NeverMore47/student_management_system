package com.google.entity;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public class CourseInfo {
    private long id;
    private String courseName;
    private String courseTeacherName;
    private Double courseCredit;
    private long courseProviderDepartment;

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

    public long getCourseProviderDepartment() {
        return courseProviderDepartment;
    }

    public void setCourseProviderDepartment(long courseProviderDepartment) {
        this.courseProviderDepartment = courseProviderDepartment;
    }
}
