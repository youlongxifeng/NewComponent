package com.component.preject.home.ui.fragment.nowledge;

import com.component.preject.home.bean.KnowledgeBean;
import com.component.preject.home.bean.ResponseBean;
import com.component.preject.home.http.ApiEngine;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.nowledge
 * @ClassName: KnowledgeModel
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class KnowledgeModel implements KnowledgeContract.Model {
    @Override
    public Observable<ResponseBean<List<KnowledgeBean>>> getKnowledgeList() {
        return ApiEngine.getInstance().getApiService().getKnowledgeList();
    }
}
