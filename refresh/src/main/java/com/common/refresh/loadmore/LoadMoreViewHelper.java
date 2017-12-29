package com.common.refresh.loadmore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

import com.common.refresh.MyScrollview;

/**
 * SwipeRefreshHelper
 * Created by xiaotongchao on 2016-11-4.
 */
public class LoadMoreViewHelper {

    private View mContentView;


    private LoadMoreHandler mLoadMoreHandler;

    private boolean isLoadingMore = false;
    private boolean isAutoLoadMoreEnable = true;
    private boolean isLoadMoreEnable = false;
    private boolean hasInitLoadMoreView = false;
    private ILoadMoreViewFactory loadMoreViewFactory = new DefaultLoadMoreViewFooter();

    private OnLoadMoreListener mOnLoadMoreListener;
    private ILoadMoreViewFactory.ILoadMoreView mLoadMoreView;


    public LoadMoreViewHelper(View view) {
        init(view);
    }

    private void init(View view) {
        mContentView = view;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setFooterView(ILoadMoreViewFactory factory) {
        if (null == factory || (null != loadMoreViewFactory && loadMoreViewFactory == factory)) {
            return;
        }

        loadMoreViewFactory = factory;

        if (hasInitLoadMoreView) {
            mLoadMoreHandler.removeFooter();
            mLoadMoreView = loadMoreViewFactory.madeLoadMoreView();
            hasInitLoadMoreView = mLoadMoreHandler.handleSetAdapter(mContentView, mLoadMoreView,
                    onClickLoadMoreListener);
            if (!isLoadMoreEnable) {
                mLoadMoreHandler.removeFooter();
            }
        }
    }

    public void setLoadMoreEnable(boolean enable) {
        if (this.isLoadMoreEnable == enable) {
            return;
        }
        this.isLoadMoreEnable = enable;
        if (!hasInitLoadMoreView && isLoadMoreEnable) {
            mLoadMoreView = loadMoreViewFactory.madeLoadMoreView();

            if (null == mLoadMoreHandler) {
                if (mContentView instanceof GridView) {
                    mLoadMoreHandler = new GridViewHandler();
                } else if (mContentView instanceof AbsListView) {
                    mLoadMoreHandler = new ListViewHandler();
                } else if (mContentView instanceof RecyclerView) {
                    mLoadMoreHandler = new RecyclerViewHandler();
                }
            }

            if (null == mLoadMoreHandler) {
                throw new IllegalStateException("unSupported contentView !");
            }

            hasInitLoadMoreView = mLoadMoreHandler.handleSetAdapter(mContentView, mLoadMoreView,
                    onClickLoadMoreListener);
            mLoadMoreHandler.setOnScrollBottomListener(mContentView, onScrollBottomListener);
            return;
        }

        if (hasInitLoadMoreView) {
            if (isLoadMoreEnable) {
                mLoadMoreHandler.addFooter();
            } else {
                mLoadMoreHandler.removeFooter();
            }
        }
    }

    public boolean isLoadMoreEnable() {
        return isLoadMoreEnable;
    }

    public void setAutoLoadMoreEnable(boolean isAutoLoadMoreEnable) {
        this.isAutoLoadMoreEnable = isAutoLoadMoreEnable;
    }

    private OnScrollBottomListener onScrollBottomListener = new OnScrollBottomListener() {
        @Override
        public void onScorllBootom() {
            if (isAutoLoadMoreEnable && isLoadMoreEnable && !isLoadingMore()) {
                // can check network here
                loadMore();
            }
        }

    };

    private View.OnClickListener onClickLoadMoreListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (isLoadMoreEnable && !isLoadingMore()) {
                loadMore();
            }
        }
    };

    private void loadMore() {
        isLoadingMore = true;
        mLoadMoreView.showLoading();
        if (null != mOnLoadMoreListener) {
            mOnLoadMoreListener.loadMore();
        }
    }

    public void loadMoreComplete(boolean hasMore) {
        isLoadingMore = false;
        if (hasMore) {
            mLoadMoreView.showNormal();
        } else {
            setNoMoreData();
        }
    }

    public void setNoMoreData() {
        isLoadingMore = false;
        mLoadMoreView.showNomore();
    }
    public void setLoadMoreFail(){
        mLoadMoreView.showFail(null);
    }

    public boolean isLoadingMore() {
        return isLoadingMore;
    }

}
