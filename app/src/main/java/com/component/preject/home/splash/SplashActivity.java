package com.component.preject.home.splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.component.preject.common.base.activity.BaseActivity;
import com.component.preject.home.MainActivity;
import com.component.preject.home.R;

import butterknife.BindView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.splash
 * @ClassName: SplashActivity
 * @Author: xzg
 * @CreateDate: 2019-08-27 17:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-27 17:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.jump_btn)
    Button mJumpbtn;
    private CountDownTimer countDownTimer;
    @Override
    protected int getLayout() {
        return R.layout.activity_welcome_main;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void onViewCreated() {
        countDownTimer =new MyCountDownTimer(1000+200,1000);
        countDownTimer.start();
        mJumpbtn.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initEventAndData() {

    }
    private void goMainActivity() {
      startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    /**
     * 倒计时计时器
     */
    private class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mJumpbtn.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            mJumpbtn.setText("跳过(" + 0 + "s)");
            goMainActivity();
        }
    }


}
