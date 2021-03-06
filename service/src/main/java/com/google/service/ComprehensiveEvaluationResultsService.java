package com.google.service;

import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.vo.ComprehensiveEvaluationResultVO;

import java.util.List;
import java.util.Map;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public interface ComprehensiveEvaluationResultsService {

    int countComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO, long classId);

    List<ComprehensiveEvaluationResultVO> findComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO, long classId);

    List<ComprehensiveEvaluationResultVO> findComprehensiveEvaluationResultByStudentId(Long studentId);

    boolean saveResultBatch(List<ComprehensiveEvaluationResultDTO> resultDTOList);

    boolean updateResult(ComprehensiveEvaluationResultDTO resultDTO);

    List<Map<String, Object>> avgResultGroupByCourseId(Long courseId);
}
