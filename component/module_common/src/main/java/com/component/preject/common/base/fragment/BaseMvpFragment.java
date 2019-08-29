package com.component.preject.common.base.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.component.preject.common.base.activity.BaseMvpActivity;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.base.fragment
 * @ClassName: BaseMvpFragment
 * @Author: xzg
 * @CreateDate: 2019-08-28 16:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 16:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
    protected P mPresenter;
    @Nullable
    protected BaseMvpActivity mBaseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //创建Presenter
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (getActivity() instanceof BaseMvpActivity) {
            mBaseActivity = (BaseMvpActivity) getActivity();
        }
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unSubscribe();
        }
        super.onDestroy();
    }

    protected abstract P createPresenter();
}
