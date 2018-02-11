package com.google.controller;

import com.google.base.entity.Result;
import com.google.entity.dto.RewardsAndPunishmentsRecordDTO;
import com.google.entity.vo.RewardsAndPunishmentsRecordVO;
import com.google.service.RewardsAndPunishmentsRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * Created by wanjiahuan on 2018/2/11.
 * Description
 */
@Controller
@RequestMapping("/rewardAndPun")
public class RewardsAndPunishmentsRecordController {

    @Autowired
    private RewardsAndPunishmentsRecordService recordService;

    @RequestMapping("/findRewardsAndPunishmentList")
    @ResponseBody
    public Result findRewardsAndPunishmentList(RewardsAndPunishmentsRecordDTO recordDTO) {
        Result result = new Result();

        int count = recordService.countReAndPuListByDto(recordDTO);
        result.addAttribute("count", count);

        if (count > 0) {
            List<RewardsAndPunishmentsRecordVO> recordVOList = recordService.findReAndPuListByDto(recordDTO);
            result.addAttribute("recordVOList", recordVOList);
        }

        return result;
    }

    @RequestMapping("/saveRewardsAndPunishment")
    @ResponseBody
    public Result saveRewardsAndPunishment(RewardsAndPunishmentsRecordDTO recordDTO) {
        Result result = new Result();

        if (!Objects.isNull(recordDTO.getStudentId()) && !StringUtils.isEmpty(recordDTO.getRePuName()) && !Objects.isNull(recordDTO.getRePuDate())) {
            result.setSuccess(recordService.saveReAndPuByDto(recordDTO));
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }

    @RequestMapping("/updateReAndPu")
    @ResponseBody
    public Result updateReAndPu(RewardsAndPunishmentsRecordDTO recordDTO) {
        Result result = new Result();

        if (!Objects.isNull(recordDTO.getStudentId()) && !StringUtils.isEmpty(recordDTO.getRePuName()) && !Objects.isNull(recordDTO.getRePuDate())) {
            result.setSuccess(recordService.updateReAndPu(recordDTO));
        }  else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }
}
