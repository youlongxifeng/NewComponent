package com.component.preject.home.ui.activity.project;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.component.preject.common.base.activity.BaseActivity;
import com.component.preject.common.constants.Constants;
import com.component.preject.home.R;

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
    @Override
    protected int getLayout() {
        return R.layout.activity_project_layout;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initEventAndData() {

    }
}
