package com.component.preject.home.ui.fragment.nowledge;

import com.component.preject.common.base.mvp.BaseModel;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;
import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.nowledge
 * @ClassName: KnowledgeContract
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface KnowledgeContract {
    interface View extends BaseView {

        void getKnowledgeListSuccess(  List<KnowledgeHierarchyData> data);

        void getKnowledgeListFail(  String msg);

    }

    interface Model extends BaseModel {

        /**
         * 请求公众号数据
         *
         * @description 描述一下方法的作用
         * @author: xiezhenggen
         */
        Observable<ResponseBean<List<KnowledgeHierarchyData>>> getKnowledgeList();


    }

    abstract class Presenter extends BasePresenter<View, Model> {

        abstract void getKnowledgeListCache();

        abstract void getKnowledgeList();

    }
}
