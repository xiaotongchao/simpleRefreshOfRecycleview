package com.test.pulltorefresh.recycleview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xiaotongchao on 2016/11/10.
 * function:
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private View mTarget;
    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("=Swipe=onScrollChanged=","="+l+"="+t+"="+oldl+"="+oldt);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        Log.e("==Swipe=onOverScrolled=","="+scrollX+"="+scrollY+"="+clampedX+"="+clampedY);
    }


}


