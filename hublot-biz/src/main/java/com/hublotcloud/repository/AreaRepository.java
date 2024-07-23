package com.hublotcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hublotcloud.domain.Area;

/**
 * AreaRepository
 */
@Repository
public interface AreaRepository extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {

    Area findByCode(String code);

    List<Area> findByLevelAndCodeStartsWith(int level, String code);

}
