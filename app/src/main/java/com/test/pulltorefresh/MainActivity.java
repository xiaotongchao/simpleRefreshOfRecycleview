package com.test.pulltorefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.test.pulltorefresh.R.id.lv_refresh;

public class MainActivity extends AppCompatActivity {


    @BindView(lv_refresh)
    Button lvRefresh;
    @BindView(R.id.rv_refresh)
    Button rvRefresh;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({lv_refresh, R.id.rv_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case lv_refresh:
                startActivity(new Intent(this,ListviewInSwipeRefreshActivity.class));
                break;
            case R.id.rv_refresh:
                startActivity(new Intent(this,RecycleInSwipeRefreshActivity.class));
                break;
        }
    }
}
