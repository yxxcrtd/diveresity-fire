package com.hublotcloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hublotcloud.domain.Area;
import com.hublotcloud.repository.AreaRepository;
import com.hublotcloud.service.AreaService;

/**
 * AreaServiceImpl
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl<Area, Long> implements AreaService {

    @Resource
    private AreaRepository areaRepository;

    @Override
    public List<Area> findByCode(String code) {
        List<Area> newList = new ArrayList<>();
        Area area = areaRepository.findByCode(code);
        if (ObjectUtils.isEmpty(area)) {
            area = areaRepository.findByCode("340000000");
            area.setLocation(area.getProvince());
            newList.add(area);
            return newList;
        }
        int level = 1;
        String location = area.getTown();
        if (ObjectUtils.nullSafeEquals(code.substring(2, 4), "00")) {
            level = 2;
            code = code.substring(0, 2);
        } else if (ObjectUtils.nullSafeEquals(code.substring(4, 6), "00")) {
            level = 3;
            code = code.substring(0, 4);
        } else if (ObjectUtils.nullSafeEquals(code.substring(6, 9), "000")) {
            level = 4;
            code = code.substring(0, 6);
        }
        List<Area> list = areaRepository.findByLevelAndCodeStartsWith(level, code);
        for (Area area2 : list) {
            switch (level) {
                case 2:
                    location = area2.getCity();
                    break;
                case 3:
                    location = area2.getCounty();
                    break;
                case 4:
                    location = area2.getTown();
                    break;
                default:
                    break;
            }
            area2.setLocation(location);
            newList.add(area2);
        }
        if (ObjectUtils.isEmpty(newList)) {
            area.setLocation(location);
            newList.add(area);
        }
        return newList;
    }

}
