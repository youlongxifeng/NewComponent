package com.component.preject.home.ui.fragment.official;

import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.bean.ResponseBean;
import com.component.preject.home.http.ApiEngine;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.official
 * @ClassName: OfficialAccountsPageModel
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class OfficialAccountsPageModel implements OfficialAccountsPageContract.Model {
    @Override
    public Observable<ResponseBean<List<KnowledgeHierarchyData>>> getWxArticle() {
        return ApiEngine.getInstance().getApiService().getWxArticle();
    }
}
