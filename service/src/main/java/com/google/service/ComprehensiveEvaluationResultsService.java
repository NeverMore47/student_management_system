package com.google.service;

import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.vo.ComprehensiveEvaluationResultVO;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface ComprehensiveEvaluationResultsService {

    int countComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO, long classId);

    List<ComprehensiveEvaluationResultVO> findComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO, long classId);

    boolean saveResultBatch(List<ComprehensiveEvaluationResultDTO> resultDTOList);

    boolean updateResult(ComprehensiveEvaluationResultDTO resultDTO);
}
