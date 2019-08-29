package com.component.preject.home.ui.activity.project;

import android.os.Build;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.component.preject.common.base.activity.BaseActivity;
import com.component.preject.common.constants.Constants;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.common.utils.StatusBarUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;

import butterknife.BindView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.activity.project
 * @ClassName: ProjectActivity
 * @Author: xzg
 * @CreateDate: 2019-08-28 11:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 11:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
@Route(path = Constants.ROUTER_PROJECT)
public class ProjectActivity extends BaseActivity {
    private final static String TAG=ProjectActivity.class.getSimpleName();

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @Override
    protected int getLayout() {
        return R.layout.activity_project_layout;
    }

    @Override
    protected void initToolbar() {

        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        LogUtils.i(TAG, "initToolbar:" + (supportActionBar != null));
        if (supportActionBar != null) {
            assert supportActionBar != null;
            //除去toolbar 默认显示的标题
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        //沉浸式状态栏
        StatusBarUtils.with(this).init();
       // StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, null, ContextCompat.getColor(this, R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (true) {
                //设置状态栏黑色字体
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                //恢复状态栏白色字体
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            }
        }
      }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initEventAndData() {

    }
}
