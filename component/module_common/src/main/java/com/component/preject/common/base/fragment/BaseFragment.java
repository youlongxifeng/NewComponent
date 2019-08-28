package com.component.preject.common.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.base.fragment
 * @ClassName: BaseFragment
 * @Author: xzg
 * @CreateDate: 2019-08-28 11:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 11:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public abstract class BaseFragment extends Fragment {
    Unbinder mUnbinder;
    /**
     * 判断当前页面是否在顶层
     */
    public boolean supportVisible=true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        onViewCreated(view);
        initEventAndData();

    }
    @Override
    public void onResume() {
        super.onResume();
        supportVisible = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        supportVisible = false;
    }
    /**
     * 判断当前页面是否隐藏
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        supportVisible=!hidden;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder=null;
    }
    /**
     * 获取布局对象 留给子类实现
     * @return
     */
    protected abstract int getLayout();
    /**
     * 初始化 toolbar
     */
    protected abstract void initToolbar();
    /**
     * view 的创建 留给子类实现
     */
    protected abstract void onViewCreated(View view);

    /**
     * 初始化数据留给子类实现
     */
    protected abstract void initEventAndData();

}
