package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.service.ComprehensiveEvaluationResultsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zengxiangyuan on 2018/2/9.
 * Description
 */
@RequestMapping("/result")
@Controller
public class ScoreController {

    @Autowired
    private ComprehensiveEvaluationResultsService resultsService;

    @RequestMapping("/findResultList")
    @ResponseBody
    public Result findResultListByDto(ComprehensiveEvaluationResultDTO resultDTO, @RequestParam(value = "classId", defaultValue = "0") long classId) {
        Result result = new Result();

        if (Objects.isNull(resultDTO.getCourseId()) && StringUtils.isEmpty(resultDTO.getStudentNo()) && classId == 0) {
            result.addAttribute("count", 0);
            return result;
        }

        int count = resultsService.countComprehensiveEvaluationResultByDto(resultDTO, classId);
        result.addAttribute("count", count);

        if (count != 0) {
            result.addAttribute("resultList", resultsService.findComprehensiveEvaluationResultByDto(resultDTO, classId));
        }

        return result;
    }

    @RequestMapping("/updateResult")
    @ResponseBody
    public Result updateResult(ComprehensiveEvaluationResultDTO resultDTO) {
        Result result = new Result();

        if (!Objects.isNull(resultDTO.getCourseId()) && !Objects.isNull(resultDTO.getStudentId()) && !Objects.isNull(resultDTO.getResultScore())) {
            result.setSuccess(resultsService.updateResult(resultDTO));
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }
        return result;
    }

    @RequestMapping("/avgResult")
    @ResponseBody
    public Result avgResultGroupByCourseId(Long courseId) {
        Result result = new Result();

        if (!Objects.isNull(courseId)) {
            List<Map<String, Object>> avgScoreList = resultsService.avgResultGroupByCourseId(courseId);
            result.addAttribute("avgScoreList", avgScoreList);
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }
}
