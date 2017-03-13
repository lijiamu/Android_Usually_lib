package com.android.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.Toast;

import com.android.Application.BaseApplication;

/**
 * Created by ${Andy.li} on 2017/3/13.
 */

public class BaseActivity extends AppCompatActivity {
    //退出
    public boolean isExit=false;
    public boolean isNeedExit=false;//是否需要连按2次退出程序
    private ExitHandler exitHandler;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        BaseApplication.All_activitys.add(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.All_activitys.remove(this);
    }
    /**
     * 初始记录 手机宽高（分辨率）
     */
    public void initWidthAndHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SharedPreferences sharedPreferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int Screen_width = dm.widthPixels;
        int Screen_height = dm.heightPixels;
        editor.putInt("Screen_width", Screen_width);//宽度
        editor.putInt("Screen_height", Screen_height);//高度
        //设置状态栏高度
        editor.putInt("StateHeight",getStatusBarHeight());
        editor.commit();
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK&&isNeedExit==true) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            exitHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            exitAPP();
        }
    }
    class ExitHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: //判断是否退出程序
                    isExit = false;
                    break;

                default:
                    break;
            }
        }
    }
    //设置是否需要连按2次返回退出
    public void setIsNeedExit(boolean isNeedExit) {
        this.isNeedExit = isNeedExit;
        if(exitHandler==null){
            exitHandler = new ExitHandler();
        }
    }
    /**
     * 关闭APP
     * @param
     */
    public void exitAPP(){
        for(int i=0;i<BaseApplication.All_activitys.size();i++){
            BaseApplication.All_activitys.get(i).finish();
        }
    }
}
