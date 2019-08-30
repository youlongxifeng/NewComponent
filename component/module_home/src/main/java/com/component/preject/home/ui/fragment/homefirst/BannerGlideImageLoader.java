package com.component.preject.home.ui.fragment.homefirst;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homefirst
 * @ClassName: BannerGlideImageLoader
 * @Author: xzg
 * @CreateDate: 2019-08-30 9:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-30 9:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class BannerGlideImageLoader  extends ImageLoader {

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
       Glide.with(context).load(o).into(imageView);
    }
}
