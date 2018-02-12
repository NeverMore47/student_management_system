package com.google.service.impl;

import com.google.dao.StudentInfoMapper;
import com.google.dao.UserMapper;
import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.dto.UserDTO;
import com.google.entity.vo.CourseInfoVO;
import com.google.entity.vo.StudentInfoVO;
import com.google.service.ComprehensiveEvaluationResultsService;
import com.google.service.CourseInfoService;
import com.google.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoMapper infoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ComprehensiveEvaluationResultsService resultsService;

    @Autowired
    private CourseInfoService courseInfoService;

    @Override
    public int countStudentInfoListByDto(StudentInfoDTO infoDTO) {
        return infoMapper.countStudentInfoListByDto(infoDTO);
    }

    @Override
    public List<StudentInfoVO> findStudentInfoListByDto(StudentInfoDTO infoDTO) {
        return infoMapper.findStudentInfoListByDto(infoDTO);
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean saveStudentInfo(StudentInfoDTO infoDTO) {
        infoMapper.saveStudentInfo(infoDTO);
        if (infoDTO.getId() > 0) {
            // 新增学生信息后初始化登录用户，默认密码为学号
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(infoDTO.getStudentNo());
            userDTO.setUserPwd(infoDTO.getStudentNo());
            userDTO.setUserRoleId(3);

            userMapper.saveUser(userDTO);
            if (userDTO.getId() > 0) {
                infoDTO.setUserId(userDTO.getId());
                infoMapper.updateStudentInfo(infoDTO);
            }
            // 初始化成绩表 begin
            List<ComprehensiveEvaluationResultDTO> resultDTOList = new ArrayList<>();
            // 查询学生所在班级的课程列表
            List<CourseInfoVO> courseInfoVOList = courseInfoService.findCourseInfoByClassId(infoDTO.getStudentClassId());
            courseInfoVOList.forEach(courseInfoVO -> {
                ComprehensiveEvaluationResultDTO resultDTO = new ComprehensiveEvaluationResultDTO();

                resultDTO.setCourseId(courseInfoVO.getId());
                resultDTO.setStudentId(infoDTO.getId());

                resultDTOList.add(resultDTO);
            });
            // 批量插入初始成绩数据
            if (resultDTOList.size() > 0) {
                resultsService.saveResultBatch(resultDTOList);
                return true;
            }
        }
        return false;
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean updateStudentInfo(StudentInfoDTO infoDTO) {
        int flag = infoMapper.updateStudentInfo(infoDTO);
        if (flag == 1) {
            return true;
        }
        return false;
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean deleteStudentInfoById(long id) {
        int flag = infoMapper.deleteStudentInfoById(id);
        if (flag == 1) {
            return true;
        }
        return false;
    }
}
