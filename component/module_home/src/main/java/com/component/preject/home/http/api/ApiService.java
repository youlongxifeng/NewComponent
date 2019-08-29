package com.component.preject.home.http.api;

import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.bean.ProjectClassifyData;
import com.component.preject.home.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.http.api
 * @ClassName: ApiService
 * @Author: xzg
 * @CreateDate: 2019-08-28 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 15:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface ApiService {

    /**
     * 获取公众号列表
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Observable<ResponseBean<List<KnowledgeHierarchyData>>> getWxArticle();

    /**
     * 项目分类 项目为包含一个分类，该接口返回整个分类。
     * @return
     */
    @GET("/project/tree/json")
    Observable<ResponseBean<List<ProjectClassifyData>>>getProjectClassifyData();

    /**
     * 最新项目tab (首页的第二个tab)
     * @param pageNum 页码，拼接在连接中，从0开始。
     * @return
     */
    @GET("/article/listproject/{pageNum}/json")
    Observable<ResponseBean<HomeArticleListData>>getHomeArticleListProjectData(@Path("pageNum")int pageNum );
}
