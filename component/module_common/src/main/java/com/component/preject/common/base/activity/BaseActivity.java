package com.component.preject.common.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.base.activity
 * @ClassName: BaseActivity
 * @Author: xzg
 * @CreateDate: 2019-08-27 14:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-27 14:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public abstract class BaseActivity extends SupportActivity {
    private Unbinder mUnbinder;
    protected BaseActivity mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        // 禁止所有的activity横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mUnbinder= ButterKnife.bind(this);
        mContext=this;
        initToolbar();
        onViewCreated();
        initEventAndData();
    }
    @Override
    protected void onDestroy() {
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
    protected abstract void onViewCreated();

    /**
     * 初始化数据留给子类实现
     */
    protected abstract void initEventAndData();





}
