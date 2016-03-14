#单类型抽象适配
```
##方法一,使用QuickAdapter
QuickAdapter<Demo> adapter =
new QuickAdapter<Demo>(BaseApplication.getInst(), R.layout.item_quickadapter, data) {
            @Override
            public void bindData4View(ViewHolder holder, Demo data, int pos) {
                holder.setText(R.id.item_quickadapter_title, data.getmDemoTitle());
            }

            @Override
            public void bindListener4View(ViewHolder holder, Demo data, int pos) {

            }
        };

##方法二,使用QuickTypeAdapter的带layout资源参数构造方法,注意不要调用addType了
QuickTypeAdapter<Demo> typeAdapter =
new QuickTypeAdapter<Demo>(BaseApplication.getInst(), data,R.layout.item_a) {
            @Override
            public void bindData4View(ViewHolder holder, Demo data, int type, int pos) {
                     //单类型,type一直返回0
            }
            @Override
            public void bindListener4View(ViewHolder holder, Demo data, int type, int pos) {
                    //在这里给内部控件绑定监听不是必须实现的
            }
};
```
#多类型适配
```
QuickTypeAdapter<Demo> typeAdapter = 
new QuickTypeAdapter<Demo>(BaseApplication.getInst(), data) {
            @Override
            public void bindData4View(ViewHolder holder, Demo data, int type, int pos) {
                switch (type) {
                    case Demo.CODE_DETAIL:
                        holder.setText(R.id.item_quickadapter_type_title, data.getmDemoTitle());
                        break;
                    case Demo.JUST_TEST:
                        holder.setText(R.id.item_quickadapter_title, data.getmDemoTitle());
                        break;
                }
            }

            @Override
            public void bindListener4View(ViewHolder holder, Demo data, int type, int pos) {
            //在这里给内部控件绑定监听不是必须实现的
            }
        };
//添加每种类型的xml文件
typeAdapter.addType(Demo.CODE_DETAIL, R.layout.item_quickadapter_type)
                .addType(Demo.JUST_TEST, R.layout.item_quickadapter);
```

