package com.component.preject.home.ui.fragment.project;

import com.component.preject.common.base.mvp.BaseModel;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;
import com.component.preject.home.bean.ProjectClassifyData;
import com.component.preject.home.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.project
 * @ClassName: ProjectContract
 * @Author: xzg
 * @CreateDate: 2019-08-29 11:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 11:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface ProjectContract {
    interface View extends BaseView {

        /**
         * 获取项目数据列表成功
         *
         * @param active
         */
        void onProjectClassifyDataSucceed(List<ProjectClassifyData> active);

        /**
         * 获取项目数据列表失败
         *
         * @param fail
         */
        void onProjectClassifyDataFail(String fail);

    }

    interface Model extends BaseModel {
        /**
         * 获取项目列表
         * @return
         */
        Observable<ResponseBean<List<ProjectClassifyData>>> getProjectClassifyData();




    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 退出登录
         */
        abstract void getProjectClassifyData( );

    }
}
