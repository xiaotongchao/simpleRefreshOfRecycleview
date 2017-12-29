package com.common.refresh.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by xtc on 2015/12/1.
 */
public class AdapterUtils {
    /**
     * list显示

     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    public static void loadListData(Context context , RecyclerView mRecyclerView, RecyclerView.Adapter adapter, boolean reverse, boolean vertical) {

        //LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(context);
        //设置是否反向显示
        layoutManager.setReverseLayout(reverse);
        layoutManager.setOrientation(vertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * grid显示
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    public static void loadGridData(Context context , RecyclerView mRecyclerView, final RecyclerView.Adapter adapter, boolean reverse, boolean vertical, int spanCount) {
        final FullyGridLayoutManager layoutManager = new FullyGridLayoutManager(context, spanCount);

        //设置是否反向显示
        layoutManager.setReverseLayout(reverse);
        layoutManager.setOrientation(vertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 瀑布流显示
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    public static  void loadStaggered(Context context , RecyclerView mRecyclerView, RecyclerView.Adapter adapter, boolean reverse, boolean vertical, int spanCount) {
        FullyStaggeredGridLayoutManager layoutManager = new FullyStaggeredGridLayoutManager(spanCount, vertical ?
                StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(reverse);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
    /**
     * list显示   使用上拉加载的时候使用
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    public static void loadListDataContainLoadMore(Context context , RecyclerView mRecyclerView, final RecyclerAdapterWithHF adapter, boolean reverse, boolean vertical) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(context);
        //设置是否反向显示
        layoutManager.setReverseLayout(reverse);
        layoutManager.setOrientation(vertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
    /**
     * grid显示   使用上拉加载的时候使用
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    public static void loadGridDataContainLoadMore(Context context , RecyclerView mRecyclerView, final RecyclerAdapterWithHF adapter, boolean reverse, boolean vertical, int spanCount) {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, spanCount);
        //设置是否反向显示
        gridLayoutManager.setReverseLayout(reverse);
        gridLayoutManager.setOrientation(vertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);
        //根据item的position设置SpanCount
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                  return ((adapter.isHeader(position))||adapter.isFooter(position)) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }

    /**
     * 瀑布流显示 使用上拉加载的时候使用
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    public static  void loadStaggeredContainLoadMore(Context context , RecyclerView mRecyclerView, RecyclerAdapterWithHF adapter, boolean reverse, boolean vertical, int spanCount) {
        adapter.setManagerType(RecyclerAdapterWithHF.TYPE_MANAGER_STAGGERED_GRID);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, vertical ?
                StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(reverse);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

}
