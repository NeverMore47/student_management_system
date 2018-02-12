package com.google.service.impl;

import com.google.dao.CourseInfoMapper;
import com.google.entity.vo.CourseInfoVO;
import com.google.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
@Service("courseInfoService")
public class CourseInfoServiceImpl implements CourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public List<CourseInfoVO> findCourseInfoByClassId(long classId) {
        return courseInfoMapper.findCourseInfoByClassId(classId);
    }
}
