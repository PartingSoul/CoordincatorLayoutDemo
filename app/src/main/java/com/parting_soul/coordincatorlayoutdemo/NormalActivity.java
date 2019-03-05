package com.parting_soul.coordincatorlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.parting_soul.coordincatorlayoutdemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author parting_soul
 * @date 2019/3/4
 */
public class NormalActivity extends BaseActivity {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.main_content)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.titlebar)
    LinearLayout mTitleBar;


    @Override
    protected int getContentViewId() {
        return R.layout.act_mormal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            lists.add("android" + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, lists) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(android.R.id.text1, item);
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                LogUtils.d("verticalOffset = " + verticalOffset + " TotalScrollRange = " + appBarLayout.getTotalScrollRange());
                if (verticalOffset == -appBarLayout.getTotalScrollRange()) {
                    mTitleBar.setBackgroundColor(Color.WHITE);
                } else {
                    mTitleBar.setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

}
