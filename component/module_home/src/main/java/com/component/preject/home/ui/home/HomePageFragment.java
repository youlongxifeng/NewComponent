package com.component.preject.home.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.common.base.fragment.BaseFragment;
import com.component.preject.common.constants.Constants;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.home
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
public class HomePageFragment extends BaseFragment {
    private final static String TAG = HomePageFragment.class.getSimpleName();
    @BindView(R2.id.tv_home_value)
    TextView mHome;

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

    }


    @OnClick({R2.id.tv_home_value})
    public void onClickView(View view) {
        LogUtils.i(TAG, "==========");
        if (view.getId() == R.id.tv_home_value) {
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
}
