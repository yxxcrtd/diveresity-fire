package com.hublotcloud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hublotcloud.domain.Data;

/**
 * DataRepository
 */
@Repository
public interface DataRepository extends JpaRepository<Data, Long>, JpaSpecificationExecutor<Data> {

    @Query(
        value = "SELECT * " +
                "FROM t_data " +
                "ORDER BY create_time DESC",
        nativeQuery = true)
    Page<Data> findAllListWithPage(Pageable pageable, @Param("keyword") String keyword);

}
