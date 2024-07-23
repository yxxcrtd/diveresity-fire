package com.hublotcloud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hublotcloud.domain.Atachment;

/**
 * AtachmentRepository
 */
@Repository
public interface AtachmentRepository extends JpaRepository<Atachment, Long>, JpaSpecificationExecutor<Atachment> {

    @Query(
        value = "SELECT * " +
                "FROM t_atachment " +
                "WHERE name like %:keyword% OR name LIKE %:keyword% " +
                "ORDER BY create_time DESC",
        nativeQuery = true)
    Page<Atachment> findAllListWithPage(Pageable pageable, @Param("keyword") String keyword);

}
