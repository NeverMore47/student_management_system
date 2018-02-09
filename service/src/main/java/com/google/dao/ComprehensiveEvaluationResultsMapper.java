package com.google.dao;

import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.vo.ComprehensiveEvaluationResultVO;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface ComprehensiveEvaluationResultsMapper {

    int countComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO);

    ComprehensiveEvaluationResultVO findComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO);

    void saveResult(ComprehensiveEvaluationResultDTO resultDTO);

    int updateResult(ComprehensiveEvaluationResultDTO resultDTO);
}
