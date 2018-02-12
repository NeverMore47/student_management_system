package com.google.service;

import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.vo.StudentInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public interface StudentInfoService {

    int countStudentInfoListByDto(StudentInfoDTO infoDTO);

    List<StudentInfoVO> findStudentInfoListByDto(StudentInfoDTO infoDTO);

    StudentInfoVO findStudentInfoDetailByUserId(Long userId);

    boolean saveStudentInfo(StudentInfoDTO infoDTO);

    boolean updateStudentInfo(StudentInfoDTO infoDTO);

    boolean deleteStudentInfoById(long id);

    int importStudentInfoByExcel(MultipartFile file);

}
