package com.component.preject.home.ui.fragment.homesecond;

import com.component.preject.common.base.mvp.BaseModel;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;
import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.bean.ResponseBean;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homesecond
 * @ClassName: HomeSecondTabContracte
 * @Author: xzg
 * @CreateDate: 2019-08-29 13:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 13:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface HomeSecondTabContracte {
    interface View extends BaseView {


        /**
         * 获取数据列表成功
         *
         * @param active
         */
        void onHomeArticleListSucceed(HomeArticleListData active);

        /**
         * 获取数据列表失败
         *
         * @param fail
         */
        void onHomeArticleListFail(String fail);


    }

    interface Model extends BaseModel {

        /**
         * 获取回收数据类型
         * @param pageNum
         * @description 描述一下方法的作用
         * @author: xiezhenggen
         */
        Observable<ResponseBean<HomeArticleListData>> getHomeArticleListProjectData(int pageNum);


    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         *
         * @param pageNum
         */
        abstract void getHomeArticleListProjectData(int pageNum);


    }
}
