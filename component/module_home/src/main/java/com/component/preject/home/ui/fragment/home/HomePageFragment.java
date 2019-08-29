package com.component.preject.home.ui.fragment.home;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.constants.Constants;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.common.utils.ToolsUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;
import com.component.preject.home.ui.fragment.homefirst.HomeFirstTabFragment;
import com.component.preject.home.ui.fragment.homesecond.HomeSecondTabFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.home
 * @ClassName: HomePageFragment
 * @Author: xzg
 * @CreateDate: 2019-08-27 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-27 16:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
@Route(path = Constants.ROUTER_HOME)
public class HomePageFragment extends BaseMvpFragment {
    private final static String TAG = HomePageFragment.class.getSimpleName();
    @BindView(R2.id.project_tab)
    TabLayout mHomeTab;
    @BindView(R2.id.view_pager_project)
    ViewPager mViewPager;
    List<String> mTitle;
    List<Fragment> mFragments;
    @Override
    protected int getLayout() {
        return R.layout.home_page_fragment_layout;
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
        mTitle.add(getString(R.string.page_home_recommend));
        mTitle.add(getString(R.string.latest_project));
        mFragments.add(HomeFirstTabFragment.newInstance(mTitle.get(0)));
        mFragments.add(HomeSecondTabFragment.newInstance(mTitle.get(1),-1));
        //下划线间距
        ToolsUtils.setIndicatorWidth(mHomeTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
        // 在fragment中使用时需要传入getChildFragmentManager()作为参数
        mViewPager.setAdapter(new HomeTabPageAdapter(getChildFragmentManager(),mTitle,mFragments));
        //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mEhomeTab.getTabLayout()));
        mHomeTab.setupWithViewPager(mViewPager);
    }


    @OnClick({R2.id.ll_home})
    public void onClickView(View view) {
        LogUtils.i(TAG, "==========");
        if (view.getId() == R.id.ll_home) {
            routerProject();
            LogUtils.i(TAG, "==========");
        }
    }

    /**
     * 跳转到项目页面
     */
    private void routerProject() {
        ARouter.getInstance().build(Constants.ROUTER_PROJECT).navigation(getActivity(), new NavCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Log.d("ARouter", "找到了");
            }

            @Override
            public void onLost(Postcard postcard) {
                Log.d("ARouter", "找不到了");
            }

            @Override
            public void onArrival(Postcard postcard) {
                Log.d("ARouter", "跳转完了");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                Log.d("ARouter", "被拦截了");
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
