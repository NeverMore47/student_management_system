package com.google.service.impl;

import com.google.dao.RewardsAndPunishmentsRecordsMapper;
import com.google.entity.dto.RewardsAndPunishmentsRecordDTO;
import com.google.entity.vo.RewardsAndPunishmentsRecordVO;
import com.google.service.RewardsAndPunishmentsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanjiahuan on 2018/2/6.
 * Description
 */
@Service("rewardsAndPunishmentsRecordService")
public class RewardsAndPunishmentsRecordServiceImpl implements RewardsAndPunishmentsRecordService {

    @Autowired
    private RewardsAndPunishmentsRecordsMapper recordsMapper;

    @Override
    public int countReAndPuListByDto(RewardsAndPunishmentsRecordDTO recordDTO) {
        return recordsMapper.countReAndPuListByDto(recordDTO);
    }

    @Override
    public List<RewardsAndPunishmentsRecordVO> findReAndPuListByDto(RewardsAndPunishmentsRecordDTO recordDTO) {
        return recordsMapper.findReAndPuListByDto(recordDTO);
    }

    @Override
    public boolean saveReAndPuByDto(RewardsAndPunishmentsRecordDTO recordDTO) {
        recordsMapper.saveReAndPuByDto(recordDTO);
        if (recordDTO.getId() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReAndPu(RewardsAndPunishmentsRecordDTO recordDTO) {
        int falg = recordsMapper.updateReAndPu(recordDTO);
        if (falg == 1) {
            return true;
        }

        return false;
    }
}
