package com.hublotcloud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hublotcloud.domain.Equipment;
import com.hublotcloud.dto.EquipmentDto;

/**
 * EquipmentRepository
 */
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>, JpaSpecificationExecutor<Equipment> {


    @Query(
        value = "SELECT e.id, e.area_id AS areaId, e.name, e.model, e.code, e.lng, e.lat, e.status, e.address, e.ip, e.cal, e.remark, e.create_time AS createTime, a.province, a.city, a.county, a.town, s.id AS stationId, s.name AS stationName " +
                "FROM t_equipment e " +
                "LEFT JOIN t_area a ON e.area_id = a.code " +
                "LEFT JOIN t_station s ON s.id = e.station_id " +
                "WHERE e.name like %:keyword% OR e.name LIKE %:keyword% " +
                "ORDER BY e.create_time DESC",
        nativeQuery = true)
    Page<EquipmentDto> findAllListWithPage(Pageable pageable, @Param("keyword") String keyword);

}


