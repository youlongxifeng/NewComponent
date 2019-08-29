package com.component.preject.common.utils;

import android.content.Context;
import android.widget.ImageView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.utils
 * @ClassName: GlideUtils
 * @Author: xzg
 * @CreateDate: 2019-08-29 14:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 14:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class GlideUtils {
    public static void showBannerImage(Context context, ImageView imageView, String url){
      /*  RequestOptions requestOptions =new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);*/
    }

}
