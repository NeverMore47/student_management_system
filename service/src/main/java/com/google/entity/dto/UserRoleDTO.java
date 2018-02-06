package com.google.entity.dto;

import com.google.base.BaseDto;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public class UserRoleDTO extends BaseDto {
    private Integer id;
    private String userRoleName;
    private String userRoleDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleDesc() {
        return userRoleDesc;
    }

    public void setUserRoleDesc(String userRoleDesc) {
        this.userRoleDesc = userRoleDesc;
    }
}
