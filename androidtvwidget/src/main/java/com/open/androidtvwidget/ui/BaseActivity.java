package com.open.androidtvwidget.ui;

import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

/**
 * Created by fengfan on 2017/6/28.
 * 封装焦点框在RecyclerView中的运动的Activity
 * 主要处理焦点框和RecyclerViewTV，其他细节在继承的子类中完成。
 */

public abstract class BaseActivity extends AppCompatActivity implements  RecyclerViewTV.OnItemListener,RecyclerViewTV.OnItemClickListener {

    public RecyclerViewBridge mRecyclerViewBridge;
    private RecyclerViewTV recyclerViewTV;
    public MainUpView mainUpView;
    public View oldView;
    private int drawableRes;
    private float left, top, right, bottom;
    private boolean autoFocus=true;
    private float scale=1.2f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        loadData();
    }

    /**
     * 初始化需要处理的RecyclerViewTV和MainUpView
     * @param recyclerViewTV
     * @param mainUpView
     * @param drawableRes 焦点框的样式图片
     * @param left 用于调整焦点框左边距离
     * @param top 用于调整焦点框上边距离
     * @param right 用于调整焦点框右边距离
     * @param bottom 用于调整焦点框下边距离
     */
    public void init(RecyclerViewTV recyclerViewTV, MainUpView mainUpView,int drawableRes,float left,float top,float right,float bottom){
        this.recyclerViewTV=recyclerViewTV;
        this.mainUpView=mainUpView;
        this.drawableRes=drawableRes;
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom=bottom;
        initRecyclerViewBridge();
        initRecyclerViewTV();
    }

    /**
     * 初始化焦点框
     */
    private void initRecyclerViewBridge(){
        mainUpView.setEffectBridge(new RecyclerViewBridge());
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(drawableRes);
        float density = getResources().getDisplayMetrics().density;
        RectF receF = new RectF(
                left* density,
                top*density,
                right* density,
                bottom*density);
        mRecyclerViewBridge.setDrawUpRectPadding(receF);
    }

    /**
     * 初始化RecyclerViewTV
     */
    private void initRecyclerViewTV(){
        recyclerViewTV.setLayoutManager(getLayoutManager());
        recyclerViewTV.setFocusable(false);
        recyclerViewTV.setSelectedItemAtCentered(true);
        recyclerViewTV.setAdapter(getAdapter());
        if (autoFocus){
            mFocusHandler.sendEmptyMessageDelayed(10, 1000);
        }
        recyclerViewTV.setOnItemListener(this);
        recyclerViewTV.setOnItemClickListener(this);
    }

    /**
     * 获取布局
     * @return
     */
    public abstract int getLayout();

    /**
     * 初始化页面布局
     */
    public abstract void initView();

    /**
     * 通过接口拉取数据
     */
    public abstract void loadData();

    /**
     * 获取
     */
    public abstract RecyclerView.LayoutManager getLayoutManager();

    /**
     * 设置adapter
     * @return
     */
    public abstract RecyclerView.Adapter getAdapter();

    /**
     * 每项的点击事件
     * @param position 当前项
     */
    public abstract void onItemClick(View itemView, int position);

    /**
     * 选中子项的操作
     * @param itemView
     * @param position
     */
    public abstract void onItemSelected(View itemView, int position);

    /**
     * 取消选中的操作
     * @param itemView
     * @param position
     */
    public abstract void onItemUnSelected( View itemView, int position);


    private Handler mFocusHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //默认选中第一项
            recyclerViewTV.setDefaultSelect(0);
        }
    };

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setVisibleWidget(false);
        mRecyclerViewBridge.setFocusView(itemView, scale);
        oldView = itemView;
    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setVisibleWidget(false);
        mRecyclerViewBridge.setFocusView(itemView, scale);
        oldView = itemView;
        onItemSelected(itemView,position);
    }

    @Override
    public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setVisibleWidget(true);
        mRecyclerViewBridge.setUnFocusView(oldView);
        onItemUnSelected(itemView,position);
    }

    @Override
    public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
        onItemClick(itemView,position);
    }

    public void setAutoFocus(boolean auto){
        autoFocus=auto;
    }

    public void setAnimScale(float scale){
        this.scale=scale;
    }

}
