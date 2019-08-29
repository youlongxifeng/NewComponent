package com.component.preject.common.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.base.activity
 * @ClassName: BaseMvpActivity
 * @Author: xzg
 * @CreateDate: 2019-08-28 15:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 15:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    protected P mPresenter;
    @Nullable
    protected BaseMvpActivity mBaseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

    }

    @Override
    protected void onStart() {
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //不加这个clearFlags好像就没效果
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //设置导航栏透明(就是虚拟键那一栏)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏(或者叫通知栏)透明
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setStatusBarColor(Color.TRANSPARENT);
        }*/
        super.onStart();
    }

    public abstract P setPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unSubscribe();
        }
    }
}
