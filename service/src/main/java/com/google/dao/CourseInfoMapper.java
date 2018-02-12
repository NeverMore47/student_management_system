package com.google.dao;

import com.google.entity.vo.CourseInfoVO;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface CourseInfoMapper {

    List<CourseInfoVO> findCourseInfoByClassId(long classId);
}
