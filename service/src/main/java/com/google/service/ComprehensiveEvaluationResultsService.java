package com.google.service;

import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.vo.ComprehensiveEvaluationResultVO;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface ComprehensiveEvaluationResultsService {

    int countComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO);

    ComprehensiveEvaluationResultVO findComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO);
}
