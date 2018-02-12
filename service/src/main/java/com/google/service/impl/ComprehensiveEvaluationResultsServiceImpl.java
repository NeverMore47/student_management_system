package com.google.service.impl;

import com.google.dao.ComprehensiveEvaluationResultsMapper;
import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.entity.vo.ComprehensiveEvaluationResultVO;
import com.google.service.ComprehensiveEvaluationResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
@Service("comprehensiveEvaluationResultsService")
public class ComprehensiveEvaluationResultsServiceImpl implements ComprehensiveEvaluationResultsService {

    @Autowired
    private ComprehensiveEvaluationResultsMapper resultsMapper;

    @Override
    public int countComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO, long classId) {
        return resultsMapper.countComprehensiveEvaluationResultByDto(resultDTO, classId);
    }

    @Override
    public List<ComprehensiveEvaluationResultVO> findComprehensiveEvaluationResultByDto(ComprehensiveEvaluationResultDTO resultDTO, long classId) {
        return resultsMapper.findComprehensiveEvaluationResultByDto(resultDTO, classId);
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean saveResultBatch(List<ComprehensiveEvaluationResultDTO> resultDTOList) {
        resultsMapper.saveResultBatch(resultDTOList);
        return true;
    }

    @Transactional("transactionManager_student")
    @Override
    public boolean updateResult(ComprehensiveEvaluationResultDTO resultDTO) {
        int flag = resultsMapper.updateResult(resultDTO);
        if (flag == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> avgResultGroupByCourseId(Long courseId) {
        return resultsMapper.avgResultGroupByCourseId(courseId);
    }
}
