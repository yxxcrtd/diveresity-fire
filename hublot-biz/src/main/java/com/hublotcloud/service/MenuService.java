package com.hublotcloud.service;

import java.util.List;

import com.hublotcloud.domain.Menu;
import com.hublotcloud.domain.Router;

/**
 * MenuService
 */
public interface MenuService extends BaseService<Menu, Long> {

    public List<Menu> selectMenuTreeByUserId(Long userId);

    List<Router> buildMenus(List<Menu> menus);
    
}
