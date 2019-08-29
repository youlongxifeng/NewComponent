package com.component.preject.home.bean;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.bean
 * @ClassName: ResponseBean
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ResponseBean<T> {
    /**
     *  服务端返回码，成功（兼容以前 api）
     */
    public final static int CODE_SUCCESS0 = 0;
    /**
     *  // 服务端返回码，成功
     */
    public final static int CODE_SUCCESS = 200;
    /**
     *  // 服务端返回码，成功
     */
    public final static int CODE_SUCCESS2 = 201;
    /**
     * data : ...
     * errorCode : 0
     * errorMsg :
     */

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
