package com.component.preject.home.ui.fragment.navigation;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.home.R;
import com.component.preject.home.constant.HomeConstants;
import com.component.preject.home.ui.fragment.navigation.adapter.NaviAdapter;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.navigation
 * @ClassName: NavigationFragment
 * @Author: xzg
 * @CreateDate: 2019-08-30 11:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-30 11:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NavigationFragment extends BaseMvpFragment {


    private NaviAdapter mAdapter;
    public static Fragment newInstance(String tabName) {
        Bundle args = new Bundle();
        args.putString(HomeConstants.TAG_TAB_NAME,tabName);
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_navigation_layout;
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
