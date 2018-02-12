package com.google.service.impl;

import com.google.base.util.ImportExcelUtil;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by zengxiangyuan on 2018/2/6.
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
        //todo
        if (flag == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int importStudentInfoByExcel(MultipartFile file) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            InputStream is = file.getInputStream();
            String fileName = file.getOriginalFilename();
            // 利用工具类读取excel中的数据
            List<List<Object>> list = ImportExcelUtil.getBankListByExcel(is, fileName);
            list.forEach(infoList -> {
                StudentInfoDTO infoDTO = null;
                for (int i = 0; i < infoList.size(); i++) {
                    Integer gender = 2;
                    if (Objects.equals(infoList.get(2).toString(), "男")) {
                        gender = 1;
                    }

                    Long classId = 0L;
                    String className = infoList.get(7).toString();
                    if (Objects.equals(className, "一班")) {
                        classId = 1L;
                    } else if (Objects.equals(className, "二班")) {
                        classId = 2L;
                    } else if (Objects.equals(className, "三班")) {
                        classId = 3L;
                    } else if (Objects.equals(className, "四班")) {
                        classId = 4L;
                    } else if (Objects.equals(className, "五班")) {
                        classId = 5L;
                    }

                    try {
                        infoDTO = new StudentInfoDTO(infoList.get(0).toString(),
                                infoList.get(1).toString(),
                                gender,
                                sdf.parse(infoList.get(3).toString()),
                                infoList.get(4).toString(),
                                infoList.get(5).toString(),
                                infoList.get(6).toString(),
                                classId);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                this.saveStudentInfo(infoDTO);
            });

            return list.size();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
