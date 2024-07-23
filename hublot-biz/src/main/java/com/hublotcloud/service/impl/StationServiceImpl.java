package com.hublotcloud.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hublotcloud.Constants;
import com.hublotcloud.domain.Station;
import com.hublotcloud.dto.StationDto;
import com.hublotcloud.repository.StationRepository;
import com.hublotcloud.service.StationService;
import com.hublotcloud.utils.JsonResult;

/**
 * StationServiceImpl
 */
@Service
public class StationServiceImpl extends BaseServiceImpl<Station, Long> implements StationService {

    @Resource
    private StationRepository stationRepository;

    @Override
    public Page<StationDto> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword) {
        return stationRepository.findAllListWithPage(pageable, keyword);
    }

    @Override
    @Transactional
    public JsonResult<Object> save(Station station) {
        String returnString = Constants.SAVE_SUCCESS;
        if (ObjectUtils.isEmpty(station.getId())) {
            station.setId(Constants.LONG_0);
        }
        if (0 < station.getId()) {
            returnString = Constants.EDIT_SAVE;
        }
        stationRepository.save(station);
        return JsonResult.jsonResultSuccess(returnString, "");
    }

    @Override
    public Long getCount() {
        return stationRepository.count();
    }

}
