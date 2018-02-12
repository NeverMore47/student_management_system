package com.google.service;

import com.google.entity.dto.RewardsAndPunishmentsRecordDTO;
import com.google.entity.vo.RewardsAndPunishmentsRecordVO;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
public interface RewardsAndPunishmentsRecordService {

    int countReAndPuListByDto(RewardsAndPunishmentsRecordDTO recordDTO);

    List<RewardsAndPunishmentsRecordVO> findReAndPuListByDto(RewardsAndPunishmentsRecordDTO recordDTO);

    boolean saveReAndPuByDto(RewardsAndPunishmentsRecordDTO recordDTO);

    boolean updateReAndPu(RewardsAndPunishmentsRecordDTO recordDTO);
}
