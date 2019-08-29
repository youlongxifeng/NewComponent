package com.component.preject.home.ui.fragment.knowledge;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.constants.Constants;
import com.component.preject.home.R;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.knowledge
 * @ClassName: KnowledgeHierarchyPageFragment
 * @Author: xzg
 * @CreateDate: 2019-08-29 11:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 11:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （知识体系）
 */
@Route(path = Constants.ROUTER_KNOWLEDGEHIERARCHYPAGE)
public class KnowledgeHierarchyPageFragment extends BaseMvpFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge_hierarchy_layout;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void onViewCreated(View view) {

    }

    @Override
    protected void initEventAndData() {

    }
}
