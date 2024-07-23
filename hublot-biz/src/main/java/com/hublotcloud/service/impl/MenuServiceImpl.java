package com.hublotcloud.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.hublotcloud.domain.Menu;
import com.hublotcloud.domain.Meta;
import com.hublotcloud.domain.Router;
import com.hublotcloud.repository.MenuRepository;
import com.hublotcloud.service.MenuService;

/**
 * MenuServiceImpl
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public List<Menu> selectMenuTreeByUserId(Long userId) {
        List<Menu> menus = null;
        if (ObjectUtils.nullSafeEquals(userId, 1L)) {
            menus = menuRepository.selectMenuTreeAll();
        } else {
            // menus = menuRepository.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<Menu> getChildPerms(List<Menu> list, int parentId) {
        List<Menu> returnList = new ArrayList<Menu>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();) {
            Menu t = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t    子节点
     */
    private void recursionFn(List<Menu> list, Menu t) {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> list, Menu t) {
        List<Menu> tlist = new ArrayList<Menu>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext()) {
            Menu n = (Menu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu t) {
        return getChildList(list, t).size() > 0;
    }

    @Override
    public List<Router> buildMenus(List<Menu> menus) {
        List<Router> routers = new LinkedList<Router>();
        for (Menu menu : menus) {
            Router router = new Router();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new Meta(menu.getMenuName(), menu.getIcon(), ObjectUtils.nullSafeEquals("1", menu.getIsCache()), menu.getPath()));

            List<Menu> cMenus = menu.getChildren();
            if (!ObjectUtils.isEmpty(cMenus) && ObjectUtils.nullSafeEquals("M", menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<Router> childrenList = new ArrayList<Router>();
                Router children = new Router();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new Meta(menu.getMenuName(), menu.getIcon(), ObjectUtils.nullSafeEquals("1", menu.getIsCache()), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new Meta(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<Router> childrenList = new ArrayList<Router>();
                Router children = new Router();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent("InnerLink");
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new Meta(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(Menu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = "";
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(Menu menu) {
        return menu.getParentId().intValue() == 0 && ObjectUtils.nullSafeEquals("C", menu.getMenuType())
                && ObjectUtils.nullSafeEquals("1", menu.getIsFrame());
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && ObjectUtils.nullSafeEquals("M", menu.getMenuType()) && ObjectUtils.nullSafeEquals(1, menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(Menu menu) {
        return ObjectUtils.nullSafeEquals("1", menu.getIsFrame()) && ishttp(menu.getPath());
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean ishttp(String link) {
        return link.startsWith("http://") || link.startsWith("https://");
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(Menu menu) {
        String component = "Layout";
        if (!ObjectUtils.isEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (ObjectUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0
                && isInnerLink(menu)) {
            component = "InnerLink";
        } else if (ObjectUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = "ParentView";
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(Menu menu) {
        return menu.getParentId().intValue() != 0 && ObjectUtils.nullSafeEquals("M", menu.getMenuType());
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return 替换后的内链域名
     */
    public String innerLinkReplaceEach(String path) {
        return path.replaceAll("http://", "").replaceAll("https://", "").replaceAll("www.", "");
    }

}
