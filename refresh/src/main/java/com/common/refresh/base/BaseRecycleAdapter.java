package com.common.refresh.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaotongchao on 2016/11/14.
 * function: 通过更新数据的方法setAndNotifyData(List<T> list ,int currentPageNum)实现了分页加载数据的处理
 */

public abstract   class BaseRecycleAdapter<T ,H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {


    private List<T> datas = new ArrayList<>();

    public abstract H creatViewHolder(ViewGroup parent, int viewType);

    public abstract void bindViewHolder(H holder,T data , int position);

    /**
     * 更新适配器数据
     * @param list  全部数据
     */
    public void setData(List<T> list ){
        datas.clear();
        datas.addAll(list);
    }
    /**
     * 更新适配器数据
     * @param list  全部数据
     */
    public void setAndNotifyData(List<T> list ){
        datas.clear();
        datas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 更新适配器数据
     * @param list  第currentPageNum页下的数据
     * @param currentPageNum    数据的所在分页
     */
    public void setAndNotifyData(List<T> list ,int currentPageNum){
        if(currentPageNum==0){
            datas.clear();
        }
        datas.addAll(list);
        notifyDataSetChanged();
    }
    public List<T> getDatas(){
        return datas;
    }


    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        return creatViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        bindViewHolder(holder,datas.get(position),position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
