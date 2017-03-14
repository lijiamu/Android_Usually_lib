package com.android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.application.BaseApplication;
import com.android.dialog.LoadingDialog;
import com.android.R;

/**
 * Created by ${Andy.li} on 2017/3/13.
 */

public class BaseActivity extends AppCompatActivity {
    //退出
    public boolean isExit=false;
    public boolean isNeedExit=false;//是否需要连按2次退出程序
    private ExitHandler exitHandler;
    /**
     * 标题 左右按钮
     */
    LinearLayout btn_title_left ;
    LinearLayout btn_title_right ;
    /**
     * 标题 文字
     */
    TextView title_text;
    /**
     * 刷新
     */
    private LinearLayout refresh_linear;
    private Button refresh_btn;
    /**
     * 没数据
     */
    private LinearLayout linear_no_data;
    /**
     * Content 布局
     */
    View contentView;
    /**
     * 加载等待
     */
    private LoadingDialog dialog;


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
    protected void showLoading(Context context){
        if(dialog == null){
            dialog =  new LoadingDialog(context, R.style.loading_dialog,false,true);
        }
        dialog.show();
    }
    protected void closeLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    /**
     * 初始化刷新
     */
    public void initrefresh() {
        refresh_linear = (LinearLayout) findViewById(R.id.refresh_linear);
        refresh_linear.setBackgroundColor(Color.WHITE);
        refresh_btn = (Button) findViewById(R.id.refresh_btn);
    }

    /**
     * 初始化没数据
     */
    public void initNoData(){
        linear_no_data = (LinearLayout)findViewById(R.id.linear_no_data);
        linear_no_data.setBackgroundColor(Color.WHITE);
    }

    /**
     * 显示没数据
     */
    public void ShowNoData(){
        if(refresh_linear!=null){
            refresh_linear.setVisibility(View.GONE);
        }
        if(linear_no_data!=null) {
            linear_no_data.setVisibility(View.VISIBLE);
        }
        if(contentView!=null) {
            contentView.setVisibility(View.GONE);
        }
    }

    /**
     * 显示刷新
     */
    public void ShowRefresh(){
        if(linear_no_data!=null){
            linear_no_data.setVisibility(View.GONE);
        }
        if(refresh_linear!=null) {
            refresh_linear.setVisibility(View.VISIBLE);
        }
        if(contentView!=null) {
            contentView.setVisibility(View.GONE);
        }
    }

    /**
     * 显示Content
     */
    public void ShowContent() {
        if (linear_no_data != null) {
            linear_no_data.setVisibility(View.GONE);
        }
        if(refresh_linear!=null) {
            refresh_linear.setVisibility(View.GONE);
        }
        if (contentView != null) {
            contentView.setVisibility(View.VISIBLE);
        }
    }
    public void setContentView(View contentView) {
        this.contentView = contentView;
        this.contentView.setVisibility(View.GONE);
    }
    /**
     *
     * @param title 标题名称
     * @param text_right 右边文字名称
     * @param right_imgid 右边图片资源ID	-1 表示不需要 不能为null
     * @param right_imgid 右边图片资源ID	-1 表示不需要 不能为null
     * @param 	title_image_id 中间图片
     * @param left		左边返回键是否显示
     * @param right		右边返回键是否显示
     */
    public void initTitle(String title,String text_right, int right_imgid,int title_image_id,int left,int right,int titlebg_color,Boolean isNeedTitleXian){
        LinearLayout sublayout_title_bg = (LinearLayout) findViewById(R.id.sublayout_title_bg);
        if(titlebg_color==-1){
            sublayout_title_bg.setBackgroundColor(getResources().getColor(R.color.WHITE));
        }else{
            sublayout_title_bg.setBackgroundColor(getResources().getColor(titlebg_color));
        }
         btn_title_left = (LinearLayout) findViewById(R.id.btn_title_left);
         btn_title_right = (LinearLayout) findViewById(R.id.btn_title_right);
         title_text = (TextView) findViewById(R.id.title_text);
        TextView title_right_text=(TextView)findViewById(R.id.title_right_text);
        //title_left_text=(TextView)findViewById(R.id.title_left_text);
        ImageView  title_right_image = (ImageView)findViewById(R.id.title_right_image);
        ImageView title_image = (ImageView)findViewById(R.id.title_image);
        View title_xian = findViewById(R.id.title_xian);
        if(title_xian!=null&&isNeedTitleXian==true){
            title_xian.setVisibility(View.VISIBLE);
        }else{
            title_xian.setVisibility(View.GONE);
        }
        if(title_image_id!=-1&&title_image!=null){
            title_image.setImageResource(title_image_id);
            title_image.setVisibility(View.VISIBLE);
        }
        if(null!=title){
            title_text.setText(title);
        }
        if(null!=text_right){
            //title_left_text.setText(text_right);
            title_right_text.setText(text_right);
            title_right_text.setVisibility(View.VISIBLE);
        }
        if(right_imgid!=-1){
            title_right_image.setImageResource(right_imgid);
            title_right_image.setVisibility(View.VISIBLE);
        }
        if(left==View.VISIBLE){
            btn_title_left.setVisibility(View.VISIBLE);
            btn_title_left.setOnClickListener(new Title_click());
        }else{
            btn_title_left.setVisibility(View.INVISIBLE);
        }
        if(right==View.VISIBLE){
            btn_title_right.setVisibility(View.VISIBLE);
        }else{
            btn_title_right.setVisibility(View.INVISIBLE);
        }
    }
    class Title_click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id=v.getId();
            if(id==R.id.btn_title_left){
                thisFinish();
            }
        }
    }
    /**
     * activity 关闭动画
     */
    public void thisFinish() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    /**
     * activity跳转动画
     */
    public void activityAmin() {
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
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
