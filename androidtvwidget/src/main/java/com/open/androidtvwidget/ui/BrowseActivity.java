package com.open.androidtvwidget.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by office on 2017/6/30.
 */

public abstract class BrowseActivity extends AppCompatActivity {

    private SelectTypeTabReceiver selectTypeTabReceiver;
    public static final String SELECT_TYPE_TAB="select_type_tab";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        loadData();
        registerTabReceiver();
    }

    public void init(RecyclerView recyclerView){
        this.recyclerView=recyclerView;
        initRecyclerView();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void loadData();

    public abstract RecyclerView.Adapter getAdapter();

    public abstract void setAdapterListener();

    public abstract View getOldView();

    public abstract void unFocusItemChange();


    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(getAdapter());
        setAdapterListener();
    }

    private void registerTabReceiver(){
        selectTypeTabReceiver=new SelectTypeTabReceiver();
        registerReceiver(selectTypeTabReceiver,new IntentFilter(SELECT_TYPE_TAB));
    }

    class SelectTypeTabReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SELECT_TYPE_TAB)&&(getOldView()!=null)){
                getOldView().requestFocus();
                getOldView().animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //焦点从左边列表的子项时，设置子项的文本颜色
        if ((keyCode==KeyEvent.KEYCODE_DPAD_RIGHT)&&(isRecyclerLeftTypeView())){
            unFocusItemChange();
        }
        return super.onKeyDown(keyCode, event);
    }


    //判断当前选中项是否为左边列表的item
    private boolean isRecyclerLeftTypeView(){
        return (getOldView() instanceof FrameLayout);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(selectTypeTabReceiver);
    }
}
