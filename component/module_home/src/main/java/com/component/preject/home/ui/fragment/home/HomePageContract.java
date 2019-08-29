package com.component.preject.home.ui.fragment.home;

import com.component.preject.common.base.mvp.BaseModel;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;
import com.component.preject.home.bean.ProjectClassifyData;
import com.component.preject.home.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.home
 * @ClassName: HomePageContract
 * @Author: xzg
 * @CreateDate: 2019-08-29 14:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 14:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface HomePageContract {
    interface HomePageView extends BaseView {
        /**
         * 显示首页数据
         *
         * @param
         */
        void showHomePageView();
    }
    interface Model extends BaseModel {
        /**
         * 获取项目列表
         * @return
         */
        Observable<ResponseBean<List<ProjectClassifyData>>> getProjectClassifyData();




    }

    abstract class HomePageFragmentPresenter extends BasePresenter<HomePageView, Model> {


    }
}
