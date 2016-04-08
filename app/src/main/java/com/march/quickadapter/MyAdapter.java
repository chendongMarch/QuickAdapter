package com.march.quickadapter;

import android.content.Context;

import com.march.adapterlibs.QuickTypeAdapter;
import com.march.adapterlibs.ViewHolder;

import java.util.List;

/**
 * QuickAdapter     com.march.quickadapter
 * Created by 陈栋 on 16/4/8.
 * 功能:
 */
public class MyAdapter extends QuickTypeAdapter<Demo> {


    public MyAdapter(Context context, List<Demo> datas) {
        super(context, datas);
    }

    @Override
    public void bindData4View(ViewHolder holder, Demo data, int type, int pos) {

    }

    @Override
    public void bindListener4View(ViewHolder holder, Demo data, int type, int pos) {
        super.bindListener4View(holder, data, type, pos);
    }
}

