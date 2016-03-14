package com.march.quickadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.march.adapterlibs.QuickTypeAdapter;
import com.march.adapterlibs.ViewHolder;

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
        QuickTypeAdapter<Demo> adapter = new QuickTypeAdapter<Demo>(MainActivity.this, list, R.layout.item_a) {
            @Override
            public void bindData4View(ViewHolder holder, Demo data, int type, int pos) {

            }
        };
        adapter.addType(0,R.layout.item_a).addType(1,R.layout.item_b);
        listView.setAdapter(adapter);
    }
}
