package com.component.preject;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.common.BuildConfig;
import com.component.preject.common.base.BaseApplication;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject
 * @ClassName: ComponentApplication
 * @Author: xzg
 * @CreateDate: 2019-08-27 15:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-27 15:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ComponentApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this);
        // 调试模式不是必须开启，但是为了防止有用户开启了InstantRun，但是
        // 忘了开调试模式，导致无法使用Demo，如果使用了InstantRun，必须在
        // 初始化之前开启调试模式，但是上线前需要关闭，InstantRun仅用于开
        // 发阶段，线上开启调试模式有安全风险，可以使用BuildConfig.DEBUG
        // 来区分环境
         ARouter.openDebug();
        ARouter.openDebug();
        ARouter.init(this);
    }

}
