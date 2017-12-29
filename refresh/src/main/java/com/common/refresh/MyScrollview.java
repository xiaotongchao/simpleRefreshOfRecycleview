package com.common.refresh;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by xiaotongchao on 2017/1/12.
 */

public class MyScrollview extends ScrollView {
    public MyScrollview(Context context) {
        super(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener!=null){
            onScrollListener.onScroll(l, t, oldl, oldt);
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
                                  boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if(scrollY != 0 && null != onScrollListener){
            if(clampedY){
                onScrollListener.onScrollBottom();
            }

        }
    }
    public OnScrollListener onScrollListener;
    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }
    public interface OnScrollListener{
        void onScroll(int l, int t, int oldl, int oldt);
        void onScrollBottom();
    }
}
