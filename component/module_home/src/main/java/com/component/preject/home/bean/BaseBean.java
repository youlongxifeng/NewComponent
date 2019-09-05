package com.component.preject.home.bean;

import com.component.preject.common.utils.JsonFormatUtils;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.bean
 * @ClassName: BaseBean
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class BaseBean implements Serializable {

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }
}
