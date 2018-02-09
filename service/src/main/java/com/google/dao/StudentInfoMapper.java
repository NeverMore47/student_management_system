package com.google.dao;

import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.vo.StudentInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface StudentInfoMapper {

    int countStudentInfoListByDto(@Param("infoDto")StudentInfoDTO infoDTO);

    List<StudentInfoVO> findStudentInfoListByDto(@Param("infoDto")StudentInfoDTO infoDTO);

    void saveStudentInfo(StudentInfoDTO infoDTO);

    int updateStudentInfo(StudentInfoDTO infoDTO);

    int deleteStudentInfoById(long id);
}
