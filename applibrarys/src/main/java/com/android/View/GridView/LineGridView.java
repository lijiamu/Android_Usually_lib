package com.android.View.GridView;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.android.R;


public class LineGridView extends GridView {

    public LineGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public LineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        View localView1 = getChildAt(0);
       //int column = getWidth() / localView1.getWidth();//计算出一共有多少列，假设有3列
        int column= getNumColumns();
        int childCount = getChildCount();//子view的总数
        //int childCount = 32;

     
        Paint localPaint;//画笔
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setColor(getContext().getResources().getColor(R.color.light_gray));//设置画笔的颜色
        for (int i = 0; i < childCount; i++) {//遍历子view
            View cellView = getChildAt(i);//获取子view
            if (i < column) {//第一行 画顶部横线
                canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
            }
            if (i % column == 0) {//第一列 画左边一列的竖线
                canvas.drawLine(cellView.getLeft()+1, cellView.getTop(), cellView.getLeft()+1, cellView.getBottom(), localPaint);
            }
            if ((i + 1) % column == 0) {//第三列
                //画子view底部横线
                canvas.drawLine(cellView.getLeft(), cellView.getBottom()-1, cellView.getRight(), cellView.getBottom()-1, localPaint);
                //右边竖线
                canvas.drawLine(cellView.getRight()-1, cellView.getTop(), cellView.getRight()-1, cellView.getBottom(), localPaint);
            } else if ((i + 1) > (childCount - (childCount % column))) {//如果view是最后一行
                //画子view的右边竖线
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                canvas.drawLine(cellView.getLeft(), cellView.getBottom()-1, cellView.getRight(), cellView.getBottom()-1, localPaint);
            } else {//如果view不是最后一行
                //画子view的右边竖线
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                //画子view的底部横线
                canvas.drawLine(cellView.getLeft(), cellView.getBottom()-1, cellView.getRight(), cellView.getBottom()-1, localPaint);
            }
        }
    }
    private void draw(){

    }
}