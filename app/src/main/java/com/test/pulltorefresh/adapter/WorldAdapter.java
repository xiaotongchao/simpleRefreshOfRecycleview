package com.test.pulltorefresh.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.refresh.base.BaseRecycleAdapter;
import com.test.pulltorefresh.R;

/**
 * Created by xiaotongchao on 2016/11/14.
 * function:
 */

public class WorldAdapter extends BaseRecycleAdapter<String, WorldAdapter.WorldViewHolder> {


    public Activity activity;
    public WorldAdapter(Activity activity) {
        this.activity = activity;
    }


    @Override
    public WorldViewHolder creatViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.listitem_layout, null);
        return new WorldViewHolder(view);
    }

    @Override
    public void bindViewHolder(WorldViewHolder holder, String data, int position) {
        holder.itemTv.setText(data);
    }


    class WorldViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTv;

        public WorldViewHolder(View view) {
            super(view);
            itemTv = (TextView) view;
        }
    }
}
