package com.march.quickadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.march.adapterlibs.QuickTypeAdapter;
import com.march.adapterlibs.ViewHolder;
import com.march.adapterlibs.helper.Convertor;
import com.march.adapterlibs.model.QuickModel;

import java.util.ArrayList;
import java.util.List;

import static com.march.quickadapter.R.id.listview;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private List<Demo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(listview);
        list = new ArrayList<>();
        list.add(new Demo("type1"));
        list.add(new Demo("type1"));
        list.add(new Demo("type2"));
        list.add(new Demo("type1"));
        list.add(new Demo("type2"));
        list.add(new Demo("type1"));
        list.add(new Demo("type1"));
        list.add(new Demo("type2"));
        list.add(new Demo("type1"));
        list.add(new Demo("type2"));
        list.add(new Demo("type1"));
        list.add(new Demo("type1"));
        list.add(new Demo("type2"));
        list.add(new Demo("type1"));
        list.add(new Demo("type1"));
        list.add(new Demo("type1"));
        list.add(new Demo("type2"));
        list.add(new Demo("type1"));
        list.add(new Demo("type2"));
        list.add(new Demo("type1"));
        list.add(new Demo("type1"));

        QuickTypeAdapter<Demo> adapter = new QuickTypeAdapter<Demo>(MainActivity.this, list) {
            @Override
            public void bindData4View(ViewHolder holder, Demo data, int type, int pos) {
                if(type==0)
                holder.setImg(MainActivity.this, R.id.aaaa, "aaaa").setLis(R.id.aaaa, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        adapter.addType(0, R.layout.item_a).addType(1, R.layout.item_b);
        listView.setAdapter(adapter);


        List<Integer> asd = new ArrayList<>();

        QuickTypeAdapter<QuickModel> adapter1 = new QuickTypeAdapter<QuickModel>(MainActivity.this, Convertor.convert(asd)) {
            @Override
            public void bindData4View(ViewHolder holder, QuickModel data, int type, int pos) {

            }
        };


        QuickTypeAdapter<QuickModel> adapter2 = new QuickTypeAdapter<QuickModel>(MainActivity.this,
                Convertor.convert(list)) {

            @Override
            public void bindData4View(ViewHolder holder, QuickModel data, int type, int pos) {
                Demo demo = data.<Demo>get();
            }
        };

    }
}
