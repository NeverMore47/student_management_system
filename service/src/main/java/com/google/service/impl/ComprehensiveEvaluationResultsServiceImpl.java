package com.google.service.impl;

import com.google.dao.ComprehensiveEvaluationResultsMapper;
import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.vo.ComprehensiveEvaluationResultVO;
import com.google.service.ComprehensiveEvaluationResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@Service("comprehensiveEvaluationResultsService")
public class ComprehensiveEvaluationResultsServiceImpl implements ComprehensiveEvaluationResultsService {

    @Autowired
    private ComprehensiveEvaluationResultsMapper resultsMapper;

    @Override
    public int countComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO) {
        return resultsMapper.countComprehensiveEvaluationResultByDto(resultDTO);
    }

    @Override
    public ComprehensiveEvaluationResultVO findComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO) {
        return resultsMapper.findComprehensiveEvaluationResultByDto(resultDTO);
    }
}
