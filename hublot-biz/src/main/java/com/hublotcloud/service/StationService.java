package com.hublotcloud.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hublotcloud.domain.Station;
import com.hublotcloud.dto.StationDto;
import com.hublotcloud.utils.JsonResult;

/**
 * StationService
 */
public interface StationService extends BaseService<Station, Long> {

    Page<StationDto> findAllWithPage(HttpServletRequest request, Pageable pageable, String keyword);

    JsonResult<Object> save(Station station);

    Long getCount();

}
