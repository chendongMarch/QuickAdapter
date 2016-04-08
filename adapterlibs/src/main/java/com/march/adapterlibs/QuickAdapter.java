package com.march.adapterlibs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽象适配器，使用了模板方法模式，将设置item显示内容的部分抽象到了类外 这是单类型的抽象适配
 *
 * 已经过时,建议使用public QuickTypeAdapter(Context context, D[] ds, int res);
 *               public QuickTypeAdapter(Context context, List datas, int res);
 *
 * @param <T> 泛型
 * @author chendong
 */
@Deprecated
public abstract class QuickAdapter<T> extends BaseAdapter {
    private LayoutInflater layoutInflater;
    protected Context context;
    protected int resId;
    protected List<T> datas;


    /**
     * @param context 上下文对象，建议使用getApplicationContext();
     * @param resId   item布局id
     * @param datas   数据集
     */
    public QuickAdapter(Context context, int resId, List<T> datas) {
        super();
        this.layoutInflater = LayoutInflater.from(context);
        this.resId = resId;
        this.datas = datas;
        this.context = context;
    }

    public QuickAdapter(Context context, int resId, T[] ts) {
        datas = new ArrayList<>();
        Collections.addAll(datas, ts);
        this.layoutInflater = LayoutInflater.from(context);
        this.resId = resId;
        this.context = context;
    }

    public T getData(int pos) {
        return datas.get(pos);
    }


    public void swapData(List<T> ds) {
        this.datas.clear();
        Collections.copy(datas, ds);
        notifyDataSetChanged();
    }


    public void swapData(T[] ds) {
        this.datas.clear();
        Collections.addAll(datas, ds);
        notifyDataSetChanged();
    }

    public int getCount() {
        return datas.size();
    }

    public Object getItem(int position) {
        return datas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    int prePos = -1;

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(resId, parent, false);
            int viewCount = 3;
            holder = new ViewHolder(convertView, viewCount);
            convertView.setTag(holder);
            //在这里绑定监听
            bindListener4View(holder, datas.get(position), position);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //if (parent.getChildCount() == position&&prePos!=position)
        bindData4View(holder, datas.get(position), position);
        prePos = position;

        return convertView;
    }

    /**
     * 绑定数据
     * @param holder
     * @param data
     */
    /**
     * 绑定数据
     *
     * @param holder ViewHolder数据持有者
     * @param data   数据集
     * @param pos    数据集中的位置
     */
    public abstract void bindData4View(ViewHolder holder, T data, int pos);

    /**
     * 绑定监听器
     *
     * @param holder ViewHolder数据持有者
     * @param data   数据集
     * @param pos    数据集中的位置
     */
    public void bindListener4View(ViewHolder holder, T data, int pos) {
    }
}
