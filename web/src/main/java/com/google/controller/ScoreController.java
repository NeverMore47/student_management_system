package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.ComprehensiveEvaluationResultDTO;
import com.google.service.ComprehensiveEvaluationResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wanjiahuan on 2018/2/9.
 * Description
 */
@RequestMapping("/result")
@Controller
public class ScoreController {

    @Autowired
    private ComprehensiveEvaluationResultsService resultsService;

    @RequestMapping("/findResultList")
    @ResponseBody
    public Result findResultListByDto(ComprehensiveEvaluationResultDTO resultDTO) {
        Result result = new Result();

        int count = resultsService.countComprehensiveEvaluationResultByDto(resultDTO);
        result.addAttribute("count", count);

        if (count != 0) {
            result.addAttribute("resultList", resultsService.findComprehensiveEvaluationResultByDto(resultDTO));
        }

        return result;
    }


}
