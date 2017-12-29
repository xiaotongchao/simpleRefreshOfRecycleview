package com.test.pulltorefresh.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.pulltorefresh.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by xiaotongchao on 2016/11/8.
 * function:
 */

public class RecycleAdapterTest extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public Activity activity;
    public ClickListenter clickListenter;
    public List<String> list;

    public RecycleAdapterTest(Activity activity, List<String> list, ClickListenter clickListenter) {
        this.activity = activity;
        this.list = list;
        this.clickListenter = clickListenter;
    }

    public void notifyData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.recycle_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


    public interface ClickListenter {
        void onItemClickListenter(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}