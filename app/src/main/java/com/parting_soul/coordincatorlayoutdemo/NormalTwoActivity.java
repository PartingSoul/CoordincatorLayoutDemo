package com.parting_soul.coordincatorlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.parting_soul.coordincatorlayoutdemo.base.BaseActivity;
import com.parting_soul.coordincatorlayoutdemo.base.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author parting_soul
 * @date 2019/3/5
 */
public class NormalTwoActivity extends BaseActivity {
    @BindView(R.id.mRv)
    RecyclerView mRecyclerView;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.flSearch)
    ViewGroup mSearchLayout;
    @BindView(R.id.llSearch)
    ViewGroup mSearchView;

    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.ty_title)
    TextView mTvTitle;
    @BindView(R.id.tv_main_title)
    TextView mTvMainTitle;
    @BindView(R.id.ivCover)
    ImageView mIvCover;

    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;

    @Override
    protected int getContentViewId() {
        return R.layout.act_normal_two;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            lists.add("android" + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_item, lists) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_msg, item);
            }
        });


        final int paddingWidth = DensityUtil.dip2px(this, 28);
        final int dp36 = DensityUtil.dip2px(this, 36);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                //根据AppBarLayout垂直滚动的距离和AppBarLayout可以滚动的总距离计算出滚动距离的百分比
                float paddingRatio = Math.abs(verticalOffset) * 1.0f / appBarLayout.getTotalScrollRange();
                int padding = (int) (paddingRatio * paddingWidth);
                //使得搜索布局在AppBarLayout网上滚动时逐渐缩短距离
                mSearchLayout.setPadding(padding, 0, padding, 0);


                //渐变的比率 当搜索框顶部碰到主标题底部时标题完全变为透明
                float alphaRatio = Math.abs(verticalOffset) * 1.0f / (appBarLayout.getTotalScrollRange() - dp36);
                float alpha = 1 - alphaRatio;
                mTvDesc.setAlpha(alpha);
                mTvTitle.setAlpha(alpha);
                mIvCover.setAlpha(alpha);
                mTvMainTitle.setAlpha(alpha);

                //当appbarLayout完全折叠时显示灰色的搜索框
                if (verticalOffset + appBarLayout.getTotalScrollRange() == 0) {
                    mSearchView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.shape_ebebeb_radius8));
                    mTvSearch.setTextColor(Color.parseColor("#a8a8a8"));
                    mIvSearch.setImageResource(R.mipmap.home_search);
                } else {
                    mSearchView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.shape_5c360b_radius4));
                    mTvSearch.setTextColor(Color.parseColor("#5C360B"));
                    mIvSearch.setImageResource(R.mipmap.home_iocn_search_b);
                }

            }
        });

    }

    @Override
    protected void initData() {

    }

}
