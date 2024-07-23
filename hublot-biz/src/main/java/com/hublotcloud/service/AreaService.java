package com.hublotcloud.service;

import java.util.List;

import com.hublotcloud.domain.Area;

/**
 * AreaService
 */
public interface AreaService extends BaseService<Area, Long> {

    List<Area> findByCode(String code);

}
