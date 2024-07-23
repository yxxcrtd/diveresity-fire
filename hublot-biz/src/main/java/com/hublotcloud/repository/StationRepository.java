package com.hublotcloud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hublotcloud.domain.Station;
import com.hublotcloud.dto.StationDto;

/**
 * StationRepository
 */
@Repository
public interface StationRepository extends JpaRepository<Station, Long>, JpaSpecificationExecutor<Station> {

    @Query(
        value = "SELECT s.*, a.province, a.city, a.county, a.town, u.id AS uid, u.username " +
                "FROM t_station s " +
                "LEFT JOIN t_area a ON s.area_id = a.code " +
                "LEFT JOIN t_user u ON s.user_id = u.id " +
                "WHERE name like %:keyword% OR name LIKE %:keyword% " +
                "ORDER BY create_time DESC",
        nativeQuery = true)
    Page<StationDto> findAllListWithPage(Pageable pageable, @Param("keyword") String keyword);

}
