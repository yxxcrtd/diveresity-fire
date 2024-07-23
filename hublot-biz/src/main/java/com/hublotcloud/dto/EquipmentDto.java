package com.hublotcloud.dto;

import java.util.Date;

/**
 * EquipmentVo
 */
public interface EquipmentDto {

    Long getId();

    String getAreaId();

    String getName();

    String getModel();

    String getCode();

    String getLng();

    String getLat();

    Integer getStatus();

    String getAddress();

    String getIp();

    String getCal();

    String getRemark();

    Date getCreateTime();

    String getProvince();

    String getCity();

    String getCounty();

    String getTown();

    Long getStationId();
    
    String getStationName();

}
