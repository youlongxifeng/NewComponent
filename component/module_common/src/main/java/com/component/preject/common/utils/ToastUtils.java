package com.component.preject.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.component.preject.common.R;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.utils
 * @ClassName: ToastUtils
 * @Author: xzg
 * @CreateDate: 2019-08-29 10:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 10:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ToastUtils {
    private  static ToastUtils instance=null;
    TextView mTextView;
    ImageView mImageView;
    View mView=null;
    public static ToastUtils getInstance()
    {
        if(instance==null)
        {
            instance=new ToastUtils();
        }
        return instance;
    }
    public  void  initView(Context context)
    {
        if(mView==null)
        {
            LayoutInflater mInflater = LayoutInflater.from(context);
            mView = mInflater.inflate(R.layout.toast_information, null);
            mTextView = (TextView) mView.findViewById(R.id.tv_message);
            mImageView = (ImageView) mView.findViewById(R.id.iv_icon);
        }
    }
    public  void  show(Context context,int toastRes){

        initView(context);
        Toast mToast = new Toast(context.getApplicationContext());
        mTextView.setText(context.getResources().getText(toastRes));
        mToast.setView(mView);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }
    public  void  show(Context context,String message){

        initView(context);
        Toast mToast = new Toast(context.getApplicationContext());
        mTextView.setText(message);
        mToast.setView(mView);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }
}
