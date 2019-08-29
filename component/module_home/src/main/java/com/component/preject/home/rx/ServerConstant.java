package com.component.preject.home.rx;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.rx
 * @ClassName: ServerConstant
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public  interface ServerConstant {
    /**
     * // 服务端返回码，成功（兼容以前 api）
     */
    int CODE_SUCCESS0 = 0;
    /**
     *  // 服务端返回码，成功
     */
    int CODE_SUCCESS = 200;
    /**
     *  //  资源支付成功
     */
    int  CODE_PAY_SUCCESS = 2017188;
    /**
     * // token 失效
     */
    int CODE_TOKEN_INVALID = 4019;
    /**
     *  // 未知错误
     */
    int ERROR_UNKNOWN = 1000;
    /**
     * // 解析错误
     */
    int ERROR_PARSE = 1001;
    /**
     * // 网络错误
     */
    int ERROR_NETWORK = 1002;
    /**
     *  // 协议出错
     */
    int ERROR_HTTP = 1003;
}
