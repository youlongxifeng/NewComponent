package com.component.preject.common.constants;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.constants
 * @ClassName: Constants
 * @Author: xzg
 * @CreateDate: 2019-08-27 16:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-27 16:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface Constants {
    String TAG_HOME = "HomePageFragment";
    String TAG_KNOWLEGER = "KnowledgeHierarchyPageFragment";
    String TAG_OFFICIAL = "OfficialAccountsPageFragment";
    String TAG_PROJECT = "ProjectFragment";
    String TAG_NAVIGATION = "NavigationFragment";
    String TAG_COLLECTION = "CollectionFragment";

    /**
     * 首页路由
     */
    String ROUTER_HOME = "/home/HomePageFragment";
    /**
     * 项目
     */
    String ROUTER_PROJECT = "/home/ProjectActivity";
    /**
     * 公众号 OfficialAccountsPageFragment
     */
    String ROUTER_OFFICIALACCOUNTSPAGE = "/home/OfficialAccountsPageFragment";
    /**
     * 知识体系
     * KnowledgeHierarchyPageFragment
     */
    String ROUTER_KNOWLEDGEHIERARCHYPAGE= "/home/KnowledgeHierarchyPageFragment";
    /**
     * 项目列表 ProjectFragment
     */
    String ROUTER_PROJECTFRAGMENT= "/home/ProjectFragment";



















    /**
     * 默认日期格式
     */
    String DATE_FORMAT_SLASH = "yyyy/MM/dd";
    /**
     * 默认日期格式
     */
    String DATE_FORMAT_LINE = "yyyy-MM-dd";
    /**
     * 默认日期格式
     */
    String DATE_FORMAT_DEFAULT = DATE_FORMAT_SLASH + " HH:mm:ss";

}
