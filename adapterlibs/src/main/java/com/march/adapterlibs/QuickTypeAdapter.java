package com.march.adapterlibs;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

/**
 * @author chendong
 *         分类型适配器, 创建实体之后, 调用addType()方法实现初始化
 */
public abstract class QuickTypeAdapter<D extends QuickInterface>
        extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private SparseArray<AdapterConfig> Res4Type;
    protected Context context;
    protected List<D> datas;
    private boolean isScrollEnd;
    /**
     * @param context 上下文对象
     * @param datas   数据集
     */
    public QuickTypeAdapter(Context context, List<D> datas) {
        super();
        this.layoutInflater = LayoutInflater.from(context);
        this.datas = datas;
        this.context = context;
    }


    /**
     * 只有一种类型时可以使用该构造方法
     *
     * @param context 上下文
     * @param datas   数据
     * @param res     资源
     */
    public QuickTypeAdapter(Context context, List<D> datas, int res) {
        super();
        this.layoutInflater = LayoutInflater.from(context);
        this.datas = datas;
        this.context = context;
        Res4Type = new SparseArray<>();
        Res4Type.put(0, new AdapterConfig(0, res));
    }


    /**
     * @param context 上下文对象
     * @param ds      数据集
     */
    public QuickTypeAdapter(Context context, D[] ds) {
        super();
        this.layoutInflater = LayoutInflater.from(context);
        Collections.addAll(datas, ds);
        this.context = context;
    }

    public void setToWithLoadMore(ListView listView, final int preLoadNum, final Runnable runnable) {
        listView.setAdapter(this);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isScrollEnd && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (runnable != null) runnable.run();
                }
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                isScrollEnd = firstVisibleItem + visibleItemCount + preLoadNum == totalItemCount;
            }
        });
    }

    /**
     * 只有一种类型时可以使用该构造方法
     *
     * @param context 上下文
     * @param ds      数据
     * @param res     资源
     */
    public QuickTypeAdapter(Context context, D[] ds, int res) {
        super();
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        Collections.addAll(datas, ds);
        Res4Type = new SparseArray<>();
        Res4Type.put(0, new AdapterConfig(0, res));
    }


    protected D getData(int pos) {
        return datas.get(pos);
    }

    /**
     * 切换数据源
     *
     * @param ds 数据源
     */
    public void swapData(List<D> ds) {
        this.datas.clear();
        Collections.copy(datas, ds);
        notifyDataSetChanged();
    }


    public void swapData(D[] ds) {
        this.datas.clear();
        Collections.addAll(datas, ds);
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return Res4Type.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
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

    public View getView(int position, View convertView, ViewGroup parent) {
        if (Res4Type == null) {
            throw new IllegalStateException("you must invoke addType first to init type config");
        }
        ViewHolder holder;
        /* get the type*/
        int type = datas.get(position).getType();
        if (convertView == null) {
            int resId = Res4Type.get(type).getResId();
            convertView = layoutInflater.inflate(resId, parent, false);
            holder = new ViewHolder(convertView, Res4Type.get(type)
                    .getViewCount());
            convertView.setTag(holder);
            bindListener4View(holder, datas.get(position), type, position);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        bindData4View(holder, datas.get(position), type, position);
        return convertView;
    }

    /**
     * 绑定数据
     * bind data
     *
     * @param holder the viewholder
     * @param type   data's type  如果是单类型,type = 0
     * @param data   data
     * @param pos    数据位置
     */
    public abstract void bindData4View(ViewHolder holder, D data, int type, int pos);

    /**
     * * 绑定监听
     * bind listener
     *
     * @param holder the viewholder
     * @param type   data's type   如果是单类型,type = 0
     * @param pos    position
     * @param data   数据集
     */
    public void bindListener4View(ViewHolder holder, D data, int type, int pos) {
    }


    /**
     * @param type  数据的类型(如果有n种类型,那么type的值需要是0 ~ n-1)
     * @param resId 该类型对应的资源文件的id
     * @return QuickTypeAdapter
     */
    public QuickTypeAdapter addType(int type, int resId) {
        if (this.Res4Type == null)
            this.Res4Type = new SparseArray<>();
        this.Res4Type.put(type, new AdapterConfig(type, resId));
        return this;
    }
}
