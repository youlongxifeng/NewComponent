package com.component.preject.home.rx;

import com.component.preject.common.utils.GsonUtil;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.home.bean.ResponseBean;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.rx
 * @ClassName: ApiException
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ApiException  extends RuntimeException {
    private final static String TAG = ApiException.class.getSimpleName();

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    //token过期
    private static final int TOKEN_OVERDUE = 1001;

    private int mCode;
    private String mMsg;

    public ApiException(String msg, int code ) {
        this.mMsg = msg;
        this.mCode = code;
    }

    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public int getCode() {
        return mCode;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public static ApiException handleException(Throwable e) {
        LogUtils.i(TAG, "handleException   e==" + e.getMessage() + "  =" + (e instanceof HttpException));
        ApiException apiEx = null;
        //HTTP错误
        if (e instanceof HttpException) {
            try {
                ResponseBody body = ((HttpException) e).response().errorBody();
                ResponseBean responseBean2 = GsonUtil.GsonToBean(body.string(), ResponseBean.class);
                LogUtils.i(TAG, "handleException   responseBean2==" + responseBean2);
                apiEx = new ApiException(responseBean2.getErrorMsg(), responseBean2.getErrorCode());
                return apiEx;
            } catch (IOException e1) {
                e1.printStackTrace();
                // 均视为网络错误
                apiEx = new ApiException("网络错误", ServerConstant.ERROR_HTTP);
                return apiEx;
            }


        } else if (e instanceof ApiException) {
            //服务器返回的错误
            // 4019 token 失效，统一弹框处理
            if (ServerConstant.CODE_TOKEN_INVALID == ((ApiException) e).getCode()) {
                ((ApiException) e).setMsg("");
                //  UserInfoManager.getInstance().deleteUserBeanAsy();//这段代码需要修改，登录失败需要发送消息
                LogUtils.i(TAG, "CODE_TOKEN_INVALID  =" + ((ApiException) e).getCode());            }
            return (ApiException) e;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof MalformedJsonException
                || e instanceof ParseException) {
            // 均视为解析错误
            apiEx = new ApiException("解析错误", ServerConstant.ERROR_PARSE);
            return apiEx;
        } else if (e instanceof ConnectException) {
            // 均视为网络错误
            apiEx = new ApiException("网络异常，请检查网络后点击重试", ServerConstant.ERROR_NETWORK);
            return apiEx;
        } else if (e instanceof SocketTimeoutException
                || e instanceof SocketException) {
            // 均视为网络错误
            apiEx = new ApiException("网络异常，请检查网络后点击重试", ServerConstant.ERROR_NETWORK);
            return apiEx;
        } else if (e instanceof UnknownHostException) {
            // 均视为网络错误
            apiEx = new ApiException("网络异常，请检查网络后点击重试", ServerConstant.ERROR_NETWORK);
            return apiEx;
        } else {
            e.printStackTrace();
            apiEx = new ApiException("服务器异常，请稍后重试.", ServerConstant.ERROR_UNKNOWN);
            return apiEx;
        }
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "mCode=" + mCode +
                ", mMsg='" + mMsg + '\'' +
                '}';
    }
}

