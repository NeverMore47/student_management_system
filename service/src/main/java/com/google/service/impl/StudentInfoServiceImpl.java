package com.google.service.impl;

import com.google.dao.StudentInfoMapper;
import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.vo.StudentInfoVO;
import com.google.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoMapper infoMapper;

    @Override
    public int countStudentInfoListByDto(StudentInfoDTO infoDTO) {
        return infoMapper.countStudentInfoListByDto(infoDTO);
    }

    @Override
    public List<StudentInfoVO> findStudentInfoListByDto(StudentInfoDTO infoDTO) {
        return infoMapper.findStudentInfoListByDto(infoDTO);
    }
}
