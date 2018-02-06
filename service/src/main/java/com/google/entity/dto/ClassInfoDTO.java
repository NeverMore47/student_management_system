package com.google.entity.dto;

import com.google.base.BaseDto;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class ClassInfoDTO extends BaseDto {
    private long id;
    private String className;
    private long classDepartmentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getClassDepartmentId() {
        return classDepartmentId;
    }

    public void setClassDepartmentId(long classDepartmentId) {
        this.classDepartmentId = classDepartmentId;
    }
}
