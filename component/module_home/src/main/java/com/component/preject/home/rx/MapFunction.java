package com.component.preject.home.rx;

import com.component.preject.home.bean.ResponseBean;

import io.reactivex.functions.Function;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.rx
 * @ClassName: MapFunction
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class MapFunction<F> implements Function<ResponseBean<F>, F> {
    @Override
    public F apply(ResponseBean<F> fResponseBean) throws Exception {
        int code = fResponseBean.getErrorCode();
        if (code == ResponseBean.CODE_SUCCESS || code == ResponseBean.CODE_SUCCESS0) {
            return fResponseBean.getData();
        } else {
            throw new ApiException(fResponseBean.getErrorMsg(),code);
        }
    }
}