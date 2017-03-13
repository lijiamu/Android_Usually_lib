package com.android.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/29.
 */
public class CountDownTimerUtil extends CountDownTimer {
    private TextView btn_getCode;
    public CountDownTimerUtil(long millisInFuture, long countDownInterval,TextView btn_getCode) {
        super(millisInFuture, countDownInterval);
        // TODO Auto-generated constructor stub
        this.btn_getCode = btn_getCode;
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
        btn_getCode.setEnabled(true);

        btn_getCode.setText("重新获取验证码");
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // TODO Auto-generated method stub
        // btn_getCode.setTextColor(getResources().getColor(R.color.HintColor));
        if((millisUntilFinished / 1000)<10){
            btn_getCode.setText("请等待(" + millisUntilFinished / 1000 + ")");
        }else{
            btn_getCode.setText("请等待(" + millisUntilFinished / 1000 + ")");
        }
    }
}
