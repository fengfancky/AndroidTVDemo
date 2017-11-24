package com.open.androidtvwidget.ui;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.open.androidtvwidget.R;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;

/**
 * Created by office on 2017/8/18.
 */

public class MainUpViewActivity extends AppCompatActivity {

    private FrameLayout rootView;
    private View layoutView;
    public MainUpView mainUpView;
    public EffectNoDrawBridge mEffectNoDrawBridge;
    private float focusScale=1.2f;
    private int drawableId,left,top,right,bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainUpView=new MainUpView(this);
        ViewGroup.LayoutParams mainUpViewParams = new ViewGroup.LayoutParams(1, 1);
        mainUpView.setLayoutParams(mainUpViewParams);
        rootView = new FrameLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.setLayoutParams(params);
        rootView.setFitsSystemWindows(true);
        rootView.setClipToPadding(false);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        layoutView=LayoutInflater.from(this).inflate(layoutResID,null);
        rootView.addView(layoutView);
        rootView.addView(mainUpView);
        setContentView(rootView);
    }

    private void initMoveBridge() {
        float density = getResources().getDisplayMetrics().density;
        mEffectNoDrawBridge = new EffectNoDrawBridge();
        mainUpView.setEffectBridge(mEffectNoDrawBridge);
        mEffectNoDrawBridge.setUpRectResource(drawableId); // 设置移动边框图片.
        RectF rectF = new RectF(left * density, top * density, right * density, bottom * density);
        mEffectNoDrawBridge.setDrawUpRectPadding(rectF);
    }

    public void initFocusTree(){
        rootView.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null ) {
                    mEffectNoDrawBridge.setVisibleWidget(false);
                    mainUpView.setFocusView(newFocus, oldFocus, focusScale);
                    newFocus.bringToFront();
                } else { // 标题栏处理.
                    mainUpView.setUnFocusView(oldFocus);
                    mEffectNoDrawBridge.setVisibleWidget(true);
                }
            }
        });
    }

    /**
     * @param drawableId 焦点框的样式图片
     * @param left 用于调整焦点框左边距离
     * @param top 用于调整焦点框上边距离
     * @param right 用于调整焦点框右边距离
     * @param bottom 用于调整焦点框下边距离
     */
    public void init(int drawableId,int left,int top,int right,int bottom){
        this.drawableId=drawableId;
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom=bottom;
        initMoveBridge();
        initFocusTree();
    }

    /**
     * 在init方法前调用
     * @param focusScale 放大动画的倍数
     */
    public void setFocusScale(float focusScale){
        this.focusScale=focusScale;
    }

}
