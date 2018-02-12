package com.google.dao;

import com.google.entity.dto.RewardsAndPunishmentsRecordDTO;
import com.google.entity.vo.RewardsAndPunishmentsRecordVO;

import java.util.List;

/**
 * Created by zengxiangyuan on 2018/2/6.
 * Description
 */
public interface RewardsAndPunishmentsRecordsMapper {

    int countReAndPuListByDto(RewardsAndPunishmentsRecordDTO recordDTO);

    List<RewardsAndPunishmentsRecordVO> findReAndPuListByDto(RewardsAndPunishmentsRecordDTO recordDTO);

    void saveReAndPuByDto(RewardsAndPunishmentsRecordDTO recordDTO);

    int updateReAndPu(RewardsAndPunishmentsRecordDTO recordDTO);
}
