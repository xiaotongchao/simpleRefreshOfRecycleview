package com.test.pulltorefresh;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.common.refresh.loadmore.OnLoadMoreListener;
import com.common.refresh.loadmore.SwipeRefreshHelper;
import com.common.refresh.recyclerview.AdapterUtils;
import com.common.refresh.recyclerview.RecyclerAdapterWithHF;
import com.test.pulltorefresh.adapter.RecycleAdapterTest;
import com.test.pulltorefresh.adapter.WorldAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecycleInSwipeRefreshActivity extends AppCompatActivity {


    @BindView(R.id.rv_refresh)
    RecyclerView rvRefresh;
    @BindView(R.id.sryt_swipe_listview)
    SwipeRefreshLayout  mSryt;

    private SwipeRefreshHelper mSwipeRefreshHelper;
    private List<String> mDatas = new ArrayList<>();
    private int page = 0;
    private Handler mHandler = new Handler();

    Activity activity;
    RecycleAdapterTest recycleAdapterTest;
    private RecyclerAdapterWithHF mAdapter;
//    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_recycle_layout);
        ButterKnife.bind(this);
        activity = this;
        inite();
    }

    private void inite() {
        initView();
        initData();
    }

    private void initView() {
        //        mSryt = (SwipeRefreshLayout) this.findViewById(R.id.sryt_swipe_listview);
        //        mListView = (ListView) findViewById(R.id.lv_swipe_listview);
        mSryt.setColorSchemeColors(Color.BLUE,Color.CYAN,Color.GRAY,Color.GREEN);


    }

    WorldAdapter worldAdapter;
    private void initData() {
        mSwipeRefreshHelper = new SwipeRefreshHelper(mSryt,rvRefresh);

//        adapter = new RecyclerAdapter(this, mDatas);
        worldAdapter = new WorldAdapter(this);
        mAdapter = new RecyclerAdapterWithHF(worldAdapter);
//        mAdapter.setManagerType(RecyclerAdapterWithHF.TYPE_MANAGER_STAGGERED_GRID);
        View view = View.inflate(this, R.layout.loadmore_default_footer, null);
//        mAdapter.addHeader(view);
        mAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                Toast.makeText(activity,"=="+position,Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("====ManagerType======",mAdapter.getManagerType()+"=");
//        AdapterUtils.loadGridDataContainLoadMore(activity,rvRefresh,mAdapter,false,true,3);
        AdapterUtils.loadListDataContainLoadMore(activity,rvRefresh,mAdapter,false,true);
//        AdapterUtils.loadGridDataContainLoadMore(activity,rvRefresh,mAdapter,false,true,3);
        mSryt.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });

        mSwipeRefreshHelper.setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
            @Override
            public void onfresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.clear();
                        page = 0;
                        for (int i = 0; i < 27; i++) {
                            mDatas.add(new String("  SwipeListView item  -" + i));
                        }
                        worldAdapter.setAndNotifyData(mDatas);
//                        mAdapter.notifyDataSetChanged();
//                        adapter.notifyDataSetChanged();
//                        rvRefresh.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,true));
                        mSwipeRefreshHelper.refreshComplete();
                        mSwipeRefreshHelper.setLoadMoreEnable(true);
                    }
                }, 1500);
            }
        });

        mSwipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.add(new String("  SwipeListVniew item  - add " + page));
                        mDatas.add(new String("  SwipeListView itemitem  - add " + page));
                        mDatas.add(new String("  SwipeListView itaaaaaaaaaaaaaaaaaaem  - add " + page));
                        mDatas.add(new String("  SwipeListView ititem  em  - add " + page));
                        worldAdapter.setAndNotifyData(mDatas);
//                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshHelper.loadMoreComplete(true);
                        page++;
                        Toast.makeText(RecycleInSwipeRefreshActivity.this, "load more complete", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });

    }


    /*public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> datas;
        private LayoutInflater inflater;

        public RecyclerAdapter(Context context, List<String> data) {
            super();
            inflater = LayoutInflater.from(context);
            datas = data;
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            ChildViewHolder holder = (ChildViewHolder) viewHolder;
            holder.itemTv.setText(datas.get(position));
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewHolder, int position) {
            View view = inflater.inflate(R.layout.listitem_layout, null);
            return new ChildViewHolder(view);
        }

    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTv;

        public ChildViewHolder(View view) {
            super(view);
            itemTv = (TextView) view;
        }

    }*/

    SwipeRefreshHelper.OnSwipeRefreshListener mOnSwipeRefreshListener = new SwipeRefreshHelper.OnSwipeRefreshListener() {
        @Override
        public void onfresh() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDatas.clear();
                    page = 0;
                    for (int i = 0; i < 17; i++) {
                        mDatas.add(new String("  SwipeListView item    aa-" + i));
                    }
                    recycleAdapterTest.notifyData(mDatas);
                    mSwipeRefreshHelper.refreshComplete();
                    mSwipeRefreshHelper.setLoadMoreEnable(true);
                }
            }, 1000);
        }
    };




}
