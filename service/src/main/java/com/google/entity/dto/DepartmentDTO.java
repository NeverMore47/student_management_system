package com.google.entity.dto;

import com.google.base.entity.BaseDto;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class DepartmentDTO extends BaseDto {
    private long id;
    private String departmentName;
    private String departmentDesc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDesc() {
        return departmentDesc;
    }

    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
    }
}
