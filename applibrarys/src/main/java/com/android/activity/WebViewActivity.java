package com.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.R;


public class WebViewActivity extends BaseActivity implements OnClickListener{

	private WebView webview;
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		initID();
		init();
	}
	private void initID() {
		// TODO Auto-generated method stub
		String temp_title = getIntent().getStringExtra("title");
		if(!TextUtils.isEmpty(temp_title)){
			initTitle(temp_title, "关闭", -1, -1, View.VISIBLE,View.INVISIBLE, -1,true);
		}else{
			initTitle("", "关闭", -1, -1, View.VISIBLE, View.INVISIBLE, -1,true);
		}
		initrefresh();
		webview=(WebView)findViewById(R.id.webview);
		btn_title_left.setOnClickListener(this);
		btn_title_right.setOnClickListener(this);
	}

	private void init() {
		// TODO Auto-generated method stub
		showLoading(this);
		//获取链接
		Intent intent = getIntent();
		url=intent.getStringExtra("url");
		WebSettings webSettings = webview.getSettings();
		//设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
		//设置可以访问文件
		webSettings.setAllowFileAccess(true);
		//设置支持缩放
		//  webSettings.setBuiltInZoomControls(true);

		webview.loadUrl(url);
		webview.setWebViewClient(new WebViewClient(){
			@Override //加载的链接
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				/*if(url.equals("http://app_action/feedback_success/")){ //点击了返回
					MyFlg.all_activitys.remove(WebViewActivity.this);
					finish();
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
					return true;
				}*/
				return super.shouldOverrideUrlLoading(view, url);
			}
			@Override //加载失败
			public void onReceivedError(WebView view, int errorCode,
										String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
				ShowRefresh();
				webview.setVisibility(View.GONE);
			}
			@Override //加载完成
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);

			}
		});
		webview.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onReceivedTitle(WebView view, String title) {
				// TODO Auto-generated method stub
				super.onReceivedTitle(view, title);
				title_text.setText(title);
			}
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				if(newProgress==100){
					closeLoading();
					webview.setVisibility(View.VISIBLE);

					if(webview.canGoBack()){
						btn_title_right.setVisibility(View.VISIBLE);
					}else{
						btn_title_right.setVisibility(View.INVISIBLE);
					}
				}
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			setBack();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.refresh_btn) {
			closeLoading();
			webview.loadUrl(url);
			ShowContent();
		} else if (i == R.id.btn_title_left) {
			setBack();
		} else if (i == R.id.btn_title_right) {
			finish();
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		}
	}
	private void setBack(){
		if(webview.canGoBack())
		{
			webview.goBack();//返回上一页面
		}
		else
		{
			finish();
			overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
		}
	}
}
