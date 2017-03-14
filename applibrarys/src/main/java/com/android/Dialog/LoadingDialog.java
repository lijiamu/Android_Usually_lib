package com.android.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.R;


public class LoadingDialog extends AlertDialog {
	public TextView text_progress;
	public ProgressBar MyProgressBar;
	private  Boolean isShowProgress=false;
	private String hint;
	private Boolean touch_hide=true;//true 点击消失  false  点击不消失
	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}
	public LoadingDialog(Boolean touch_hide, Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		this.touch_hide = touch_hide;
	}
	public LoadingDialog(Context context, int theme, Boolean isShowProgress) {
		super(context, theme);
		// TODO Auto-generated constructor
		this.isShowProgress =isShowProgress;
	}
	public LoadingDialog(Context context, int theme, String hint) {
		super(context, theme);
		// TODO Auto-generated constructor
		this.hint=hint;
	}
	public LoadingDialog(Context context, int theme, Boolean isShowProgress, Boolean touch_hide) {
		super(context, theme);
		// TODO Auto-generated constructor
		this.isShowProgress =isShowProgress;
		this.touch_hide = touch_hide;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.loading_dialog);
		LinearLayout myload_wait = (LinearLayout)findViewById(R.id.myload_wait);
	    myload_wait.getBackground().setAlpha(197);
		text_progress = (TextView)findViewById(R.id.text_progress);
		 MyProgressBar = (ProgressBar) findViewById(R.id.MyProgressBar);
		if(isShowProgress==true){
			text_progress.setVisibility(View.VISIBLE);
			MyProgressBar.setVisibility(View.VISIBLE);
		}else{
			MyProgressBar.setVisibility(View.VISIBLE);
			text_progress.setVisibility(View.GONE);
			if(!TextUtils.isEmpty(hint)){
				text_progress.setText(hint);
			}
		}

				Log.i("mylog","----设置不可以点击消失"+touch_hide);
				setCancelable(touch_hide);
	}
	public void setProgress(int progress){
		text_progress.setText(progress+"%");
	}
	public void setProgress(String progress){
		text_progress.setText(progress);
	}
}
