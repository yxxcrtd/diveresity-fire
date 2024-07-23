package com.hublotcloud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hublotcloud.domain.Device;

/**
 * DeviceRepository
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device> {

    @Query(
        value = "SELECT * " +
                "FROM Device " +
                "WHERE name like %:keyword% OR name LIKE %:keyword% " +
                "ORDER BY create_time DESC",
        countQuery = "SELECT COUNT(1) FROM Device WHERE name LIKE %:keyword% OR name LIKE %:keyword% ",
        nativeQuery = true)
    Page<Device> findAllListWithPage(Pageable pageable, @Param("keyword") String keyword);

}
