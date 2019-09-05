package com.component.preject.home.ui.fragment.knowledge;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.constants.Constants;
import com.component.preject.common.utils.ToolsUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;
import com.component.preject.home.ui.fragment.home.HomeTabPageAdapter;
import com.component.preject.home.ui.fragment.navigation.NavigationFragment;
import com.component.preject.home.ui.fragment.nowledge.KnowledgeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    @BindView(R2.id.project_tab)
    TabLayout mHomeTab;
    @BindView(R2.id.view_pager_project)
    ViewPager mViewPager;
    List<String> mTitle;
    List<Fragment> mFragments;
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
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitle.add(getString(R.string.hierarchy));
        mTitle.add(getString(R.string.navigation));
        mFragments.add(NavigationFragment.newInstance(mTitle.get(0)));
        mFragments.add(KnowledgeFragment.newInstance(mTitle.get(1)));
        //下划线间距
        ToolsUtils.setIndicatorWidth(mHomeTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
        // 在fragment中使用时需要传入getChildFragmentManager()作为参数
        mViewPager.setAdapter(new HomeTabPageAdapter(getChildFragmentManager(),mTitle,mFragments));
        //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mEhomeTab.getTabLayout()));
        mHomeTab.setupWithViewPager(mViewPager);
    }
}
