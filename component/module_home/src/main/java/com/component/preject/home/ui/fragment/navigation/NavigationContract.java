package com.component.preject.home.ui.fragment.navigation;

import com.component.preject.common.base.mvp.BaseModel;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;
import com.component.preject.home.bean.KnowledgeBean;
import com.component.preject.home.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.navigation
 * @ClassName: NavigationContract
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface NavigationContract {
    interface View extends BaseView {

        void getKnowledgeListSuccess(int code, List<KnowledgeBean> data);

        void getKnowledgeListFail(int code, String msg);

    }

    interface Model extends BaseModel {

        /**
         * 请求公众号数据
         *
         * @description 描述一下方法的作用
         * @author: xiezhenggen
         */
        Observable<ResponseBean<List<KnowledgeBean>>> getKnowledgeList();


    }

    abstract class Presenter extends BasePresenter<View, Model> {

        abstract void getKnowledgeListCache();

        abstract void getKnowledgeList();

    }
}
