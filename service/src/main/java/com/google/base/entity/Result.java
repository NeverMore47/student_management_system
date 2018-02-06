package com.google.base.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Result {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    /** state 常量 **/
    /**
     * 登陆超时状态
     **/
    public static final Integer SESSION_TIMEOUT = -1;
    /**
     * 失败状态
     **/
    public static final Integer INPUT = 0;
    public static final Integer FAILURE = 0;
    /**
     * 成功状态
     **/
    public static final Integer SUCCESS = 1;
    /**
     * 失败状态使用全局提示
     **/
    public static final Integer INPUT_AUTO = 2;
    /**
     * 成功状态使用全局提示
     **/
    public static final Integer SUCCESS_AUTO = 3;
    /**
     * 系统错误
     */
    public static final Integer SYS_ERROR = 9999;

    /**
     * [异常状态码]失败状态使用全局提示 用于结算页面tradpage特殊处理,需要弹出提示的时候返回购物车
     **/
    public static final Integer TRAD_INPUT_AUTO = -999;
    /**
     * [异常状态码]异步请求对于登陆的拦截
     **/
    public static final Integer NOT_LOGIN = -998;
    /**
     * [异常状态码]main.dec中不弹出，给页面自己处理
     **/
    public static final Integer NOT_ALERT = -997;
    /**
     * [异常状态码]登陆已过期，需刷新页面
     */
    public static final Integer LOGIN_OUT_DATE = -996;

    private Integer state = Result.SUCCESS;
    private boolean success = true;
    private String message = "";

    private final Map<String, Object> data = new HashMap<String, Object>();

    public Result() {
    }

    /**
     * @param success 是否成功
     */
    public Result(boolean success) {
        this.success = success;
    }

    /**
     * @param success 是否成功
     * @param state   状态码
     */
    public Result(boolean success, Integer state) {
        this.success = success;
        this.state = state;
    }

    public Result(boolean success, String msg) {
        this.success = success;
        this.message = msg;
    }

    public Result(Integer state, String msg) {
        this.state = state;
        this.message = msg;
    }

    public Result(boolean success, Integer state, String msg) {
        this.success = success;
        this.state = state;
        this.message = msg;
    }

    /**
     * 将属性绑定至request中
     *
     * @param request
     */
    public void rendering(HttpServletRequest request) {
        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            request.setAttribute(key, data.get(key));
        }
    }

    /**
     * 将属性绑定至Model中
     *
     * @param model
     */
    public void rendering(Model model) {
        model.addAllAttributes(data);
    }

    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 增加属性
     *
     * @param key
     * @param obj
     */
    public void addAttribute(String key, Object obj) {
        data.put(key, obj);
    }

    /**
     * 追加所有属性
     *
     * @param result
     */
    public void addAttrbutes(Result result) {
        data.putAll(result.getData());
    }

    public void addAttributes(Map<String, Object> map) {
        data.putAll(map);
    }

    /**
     * 获得属性
     *
     * @param key
     */
    @JsonIgnore
    public Object getAttribute(String key) {
        return data.get(key);
    }

    /**
     * 获得属性
     *
     * @param key
     * @param type
     * @return
     */
    @JsonIgnore
    public <T> T getAttribute(String key, Class<T> type) {
        if (data.get(key) == null) {
            return null;
        }
        return (T) data.get(key);
    }

    /**
     * 检查key是否存在
     *
     * @param key
     * @return
     */
    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    /**
     * 检查value是否存在
     *
     * @param value
     * @return
     */
    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    /**
     * 判断result是否存在键值
     *
     * @return
     */
    @JsonIgnore
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStateAndSuccess(Integer state, boolean success, String message) {
        setState(state);
        setSuccess(success);
        setMessage(message);
    }

    public void setSuccessAndMessage(boolean success, String message) {
        setSuccess(success);
        setMessage(message);
    }
}
