package com.android.listener;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.android.R;

/**
 * 设置剩余字数的
 */
public class EditText_TextWatcherLister implements TextWatcher{
	private TextView textView;
	private int sum = 0;
	private Context context;

	/**
	 *
	 * @param context
	 * @param textView
     * @param sum 总数
     */
	public EditText_TextWatcherLister(Context context, TextView textView, int sum) {
		// TODO Auto-generated constructor stub
		this.context =context;
		this.textView = textView;
		this.sum = sum;
	}
	@Override
	public void afterTextChanged(Editable editable) {
		// TODO Auto-generated method stub
		int length = editable.length();
		String st = length + " / " + sum;
		SpannableStringBuilder builder = new SpannableStringBuilder(st);
		builder.setSpan(new TextAppearanceSpan(context, R.style.text_red), 0,
				st.indexOf("/"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		textView.setText(builder);
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

}
