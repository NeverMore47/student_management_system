package com.google.dao;

import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.vo.ComprehensiveEvaluationResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public interface ComprehensiveEvaluationResultsMapper {

    int countComprehensiveEvaluationResultByDto(@Param("resultDTO") ComprehensiveEvaluationResultDTO resultDTO, @Param("classId") long classId);

    List<ComprehensiveEvaluationResultVO> findComprehensiveEvaluationResultByDto(@Param("resultDTO") ComprehensiveEvaluationResultDTO resultDTO, @Param("classId") long classId);

    void saveResultBatch(List<ComprehensiveEvaluationResultDTO> resultDTOList);

    int updateResult(ComprehensiveEvaluationResultDTO resultDTO);

    List<Map<String, Object>> avgResultGroupByCourseId(Long courseId);
}
