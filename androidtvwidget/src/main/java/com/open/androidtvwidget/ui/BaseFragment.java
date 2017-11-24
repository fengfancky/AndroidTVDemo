package com.open.androidtvwidget.ui;

import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.androidtvwidget.R;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

/**
 * Created by fengfan on 2017/6/28.
 * 封装焦点框在RecyclerView中的运动的Fragment
 * 主要处理焦点框和RecyclerViewTV，其他细节在继承的子类中完成。
 */

public abstract class BaseFragment extends Fragment implements  RecyclerViewTV.OnItemListener,RecyclerViewTV.OnItemClickListener{

    public RecyclerViewBridge mRecyclerViewBridge;
    private RecyclerViewTV recyclerViewTV;
    public MainUpView mainUpView;//运动焦点框
    public View oldView;
    private int drawableRes;
    private float left, top, right, bottom;
    private boolean autoFocus=true;
    private float scale=1.2f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayout(),null);
        initView(view);
        return view;
    }

    public abstract int getLayout();

    public abstract void initView(View view);

    public abstract void onItemClick(View itemView,int position);

    public abstract void onItemSelected(View itemView, int position);

    public abstract void unItemSelected(View itemView,int position);

    public abstract RecyclerView.Adapter getAdapter();

    public abstract RecyclerView.LayoutManager getLayoutManager();


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
     * 初始化RecyclerViewTV
     */

    private void initRecyclerViewTV(){
        recyclerViewTV.setLayoutManager(getLayoutManager());
        recyclerViewTV.setFocusable(false);
        recyclerViewTV.setSelectedItemAtCentered(true); // 设置item在中间移动.
        recyclerViewTV.setAdapter(getAdapter());
        if (autoFocus){
            mFocusHandler.sendEmptyMessageDelayed(10, 1000);
        }
        recyclerViewTV.setOnItemListener(this);
        recyclerViewTV.setOnItemClickListener(this);
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

    private Handler mFocusHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //默认选中第一项
            recyclerViewTV.setDefaultSelect(0);
        }
    };

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setFocusView(itemView, 1.2f);
        oldView = itemView;
    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setFocusView(itemView, 1.2f);
        oldView = itemView;
        onItemSelected(itemView,position);
    }

    @Override
    public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setUnFocusView(oldView);
        unItemSelected(itemView,position);
    }

    @Override
    public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
        onItemClick(itemView,position);
    }

    public MainUpView getMainUpView(){
        return mainUpView;
    }

    public void setAutoFocus(boolean auto){
        autoFocus=auto;
    }
    public void setAnimScale(float scale){
        this.scale=scale;
    }


}
