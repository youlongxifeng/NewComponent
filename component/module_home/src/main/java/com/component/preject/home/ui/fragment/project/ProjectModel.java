package com.component.preject.home.ui.fragment.project;

import com.component.preject.home.bean.ProjectClassifyData;
import com.component.preject.home.bean.ResponseBean;
import com.component.preject.home.http.ApiEngine;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.project
 * @ClassName: ProjectModel
 * @Author: xzg
 * @CreateDate: 2019-08-29 11:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 11:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ProjectModel implements ProjectContract.Model {
    @Override
    public Observable<ResponseBean<List<ProjectClassifyData>>> getProjectClassifyData() {
        return ApiEngine.getInstance().getApiService().getProjectClassifyData();
    }
}
