package com.google.service;

import com.google.entity.vo.CourseInfoVO;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface CourseInfoService {

    List<CourseInfoVO> findCourseInfoByClassId(long classId);
}
