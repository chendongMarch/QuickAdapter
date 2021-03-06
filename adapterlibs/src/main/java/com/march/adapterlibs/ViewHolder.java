package com.march.adapterlibs;


import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.march.adapterlibs.quick.Quick;

import java.lang.reflect.Field;

/**
 * @author chendong ViewHolder类
 */
public class ViewHolder {

    /**
     * SparseArray稀疏数组
     */
    private SparseArray<View> cacheViews;
    private View itemView;

    public ViewHolder(View itemView, int viewCount) {
        super();
        this.itemView = itemView;
        cacheViews = new SparseArray<View>(viewCount);
    }

    public View getParentView() {
        return itemView;
    }

    /**
     * 使用资源id找到view
     *
     * @param resId 资源id
     * @param <T>   泛型,View的子类
     * @return 返回泛型类
     */
    public <T extends View> T getView(int resId) {
        T v = (T) cacheViews.get(resId);
        if (v == null) {
            v = (T) itemView.findViewById(resId);
            if (v != null) {
                cacheViews.put(resId, v);
            }
        }
        return v;
    }

    /**
     *  使用类反射找到字符串id代表的view
     *
     * @param idName String类型ID
     * @return 返回View
     * @param <T> View泛型
     */
    public <T extends View> T getView(String idName) {
        T view = null;
        if (idName != null) {
            Class<R.id> idClass = R.id.class;
            try {
                Field field = idClass.getDeclaredField(idName);
                field.setAccessible(true);
                int id = field.getInt(idClass);
                view = getView(id);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    /**
     * 设置是否可见
     *
     * @param resId    资源ID
     * @param visiable visiable
     * @return this
     */
    public ViewHolder setVisibility(int resId, int visiable) {
        getView(resId).setVisibility(visiable);
        return this;
    }




    /**
     * 为checkbox设置是否选中
     *
     * @param resId     资源id
     * @param isChecked 是否选中
     * @return VH
     */
    public ViewHolder setChecked(int resId, boolean isChecked) {
        ((CheckBox) getView(resId)).setChecked(isChecked);
        return this;
    }


    /**
     * 设置背景
     * @param resId 资源id
     * @param bgRes 背景id
     * @return this
     */
    public ViewHolder setBg(int resId, int bgRes) {
        getView(resId).setBackgroundResource(bgRes);
        return this;
    }

    /**
     * 为textview设置文本
     *
     * @param resId 控件资源id
     * @param txt   设置的文本
     * @return VH
     */
    public ViewHolder setText(int resId, String txt) {
        ((TextView) getView(resId)).setText(txt);
        return this;
    }

    public ViewHolder setText(int resId, SpannableString txt) {
        ((TextView) getView(resId)).setText(txt);
        return this;
    }

    public ViewHolder setText(String resId, String txt) {
        ((TextView) getView(resId)).setText(txt);
        return this;
    }

    /***********imageview*****************************/
    /**
     * 为ImageView使用图片资源id设置图片
     *
     * @param resId    控件资源id
     * @param imgResId 图片资源id
     * @return VH
     */
    public ViewHolder setImg(int resId, int imgResId) {
        ((ImageView) getView(resId)).setImageResource(imgResId);
        return this;
    }

    /**
     * 为ImageView使用位图设置图片
     *
     * @param resId 控件资源id
     * @param bit   图片资源位图
     * @return VH
     */
    public ViewHolder setImg(int resId, Bitmap bit) {
        ((ImageView) getView(resId)).setImageBitmap(bit);
        return this;
    }

    public ViewHolder setImg(Context context, int resId, String url) {
        if (!"".equals(url) && url != null) {
            Quick.get().load(context, url, (ImageView) getView(resId));
        }
        return this;
    }

    public ViewHolder setLis(int resId,View.OnClickListener listener,Object tag){
        getView(resId).setTag(R.string.quick_key,tag);
        getView(resId).setOnClickListener(listener);
        return this;
    }
    public ViewHolder setLis(int resId,View.OnClickListener listener){
        getView(resId).setOnClickListener(listener);
        return this;
    }

    public ViewHolder setTag(int resId, Object tag) {
        getView(resId).setTag(R.string.quick_key,tag);
        return this;
    }


    public <T> T getTag(int resId){
        return (T) getView(resId).getTag(R.string.quick_key);
    }

}
