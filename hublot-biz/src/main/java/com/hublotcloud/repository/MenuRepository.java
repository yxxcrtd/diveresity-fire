package com.hublotcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hublotcloud.domain.Menu;

/**
 * MenuRepository
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    @Query(
        value = "SELECT distinct m.menu_id, m.parent_id, m.menu_name, m.path, m.component, " +
                        "m.query, m.visible, m.status, nullif(m.perms, '') as perms, m.is_frame, " + 
                        "m.is_cache, m.menu_type, m.icon, m.order_num, m.create_time, m.create_by, " + 
                        "m.remark, m.update_by, m.update_time " +
                "FROM sys_menu m where m.menu_type in ('M', 'C') and m.status = 0 " +
                "ORDER BY m.parent_id, m.order_num",
        nativeQuery = true)
    List<Menu> selectMenuTreeAll();

}
