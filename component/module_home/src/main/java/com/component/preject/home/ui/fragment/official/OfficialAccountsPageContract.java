package com.component.preject.home.ui.fragment.official;

import com.component.preject.common.base.mvp.BaseModel;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;
import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.official
 * @ClassName: OfficialAccountsPageContract
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface OfficialAccountsPageContract {
    interface View extends BaseView {

        /**
         * 数据加载成功
         * @param officialAccountsListData
         */
        void showOfficialAccountsListSuccess(List<KnowledgeHierarchyData> officialAccountsListData );


        /**
         * 数据加载失败
         * @param msg
         */
        void showOfficialAccountsListFail(String msg);

    }

    interface Model extends BaseModel {

        /**
         * 请求公众号数据
         * @description 描述一下方法的作用
         * @author: xiezhenggen
         */
        Observable<ResponseBean<List<KnowledgeHierarchyData>>> getWxArticle();





    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         *请求公众号数据
         */
        abstract  void getOfficialAccountsListData();


    }
}
