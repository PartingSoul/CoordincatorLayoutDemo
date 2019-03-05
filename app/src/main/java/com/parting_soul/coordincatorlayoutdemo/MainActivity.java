package com.parting_soul.coordincatorlayoutdemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.parting_soul.coordincatorlayoutdemo.base.BaseActivity;
import com.parting_soul.coordincatorlayoutdemo.base.ItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mRv)
    RecyclerView mRv;
    private BaseQuickAdapter<ItemBean, BaseViewHolder> mAapter;
    private List<ItemBean> mLists;

    @Override
    protected int getContentViewId() {
        return R.layout.act_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRv();
    }

    @Override
    protected void initData() {

    }

    private void initRv() {
        mLists = getData();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAapter = new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.adapter_item, mLists) {
            @Override
            protected void convert(BaseViewHolder helper, ItemBean item) {
                helper.setText(R.id.tv_msg, item.getName());
            }
        };
        mAapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int type = mLists.get(position).getType();
                processClick(type);
            }
        });
        mRv.setAdapter(mAapter);
    }

    private void processClick(int type) {
        Class<?> clazz = null;
        switch (type) {
            case ItemBean.TYPE_NORMAL:
                clazz = NormalActivity.class;
                break;
            case ItemBean.TYPE_NORMAL_TWO:
                clazz = NormalTwoActivity.class;
                break;
            default:
                break;
        }
        startActivity(this, clazz);
    }

    private List<ItemBean> getData() {
        List<ItemBean> lists = new ArrayList<>();
        lists.add(new ItemBean("普通", ItemBean.TYPE_NORMAL));
        lists.add(new ItemBean("普通2", ItemBean.TYPE_NORMAL_TWO));
        return lists;
    }

}
