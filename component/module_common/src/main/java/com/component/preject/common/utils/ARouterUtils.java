package com.component.preject.common.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.common.base.activity.BaseActivity;
import com.component.preject.common.base.fragment.BaseFragment;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.utils
 * @ClassName: ARouterUtils
 * @Author: xzg
 * @CreateDate: 2019-08-28 11:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 11:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ARouterUtils {

    /**
     * 根据path返回Fragment
     *
     * @param path path
     * @return fragment
     */
    public static BaseFragment getFragment(String path) {
        return (BaseFragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    /**
     * 根据path返回Activity
     *
     * @param path path
     * @return Activity
     */
    public static BaseActivity getActivity(String path) {
        return (BaseActivity) ARouter.getInstance()
                .build(path)
                .navigation();
    }
}
