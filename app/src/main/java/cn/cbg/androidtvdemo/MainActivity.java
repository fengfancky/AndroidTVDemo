package cn.cbg.androidtvdemo;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    @Bind(R.id.cardView1)
    ReflectItemView cardView1;
    @Bind(R.id.cardView2)
    ReflectItemView cardView2;
    @Bind(R.id.cardView3)
    ReflectItemView cardView3;
    @Bind(R.id.cardView4)
    ReflectItemView cardView4;
    @Bind(R.id.cardView5)
    ReflectItemView cardView5;
    @Bind(R.id.cardView6)
    ReflectItemView cardView6;
    @Bind(R.id.cardView7)
    ReflectItemView cardView7;
    @Bind(R.id.cardView8)
    ReflectItemView cardView8;
    @Bind(R.id.cardView9)
    ReflectItemView cardView9;
    @Bind(R.id.mainUpView)
    MainUpView mainUpView;
    private EffectNoDrawBridge mEffectNoDrawBridge;
    private View mOldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMoveBridge();
        initListener();
    }

    private void initMoveBridge() {
        float density = getResources().getDisplayMetrics().density;
        mEffectNoDrawBridge = new EffectNoDrawBridge();
        mainUpView.setEffectBridge(mEffectNoDrawBridge);
        mEffectNoDrawBridge.setUpRectResource(R.drawable.white_light); // 设置移动边框图片.
        RectF rectF = new RectF(15 * density, 15 * density, 14 * density, 15 * density);
        mEffectNoDrawBridge.setDrawUpRectPadding(rectF);
    }

    private void initListener(){
        cardView1.setOnFocusChangeListener(this);
        cardView2.setOnFocusChangeListener(this);
        cardView3.setOnFocusChangeListener(this);
        cardView4.setOnFocusChangeListener(this);
        cardView5.setOnFocusChangeListener(this);
        cardView6.setOnFocusChangeListener(this);
        cardView7.setOnFocusChangeListener(this);
        cardView8.setOnFocusChangeListener(this);
        cardView9.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mOldView = v;
            mEffectNoDrawBridge.setVisibleWidget(false);


            //可以对某个view进行特殊处理
//            if (v.getId()==R.id.card_img) {
//                mEffectNoDrawBridge.setFocusView(v, 1.05f);
//            } else {
//                mEffectNoDrawBridge.setFocusView(v, 1.15f);
//            }

            mEffectNoDrawBridge.setFocusView(v, 1.15f);
            v.bringToFront();
        } else {
            mEffectNoDrawBridge.setUnFocusView(mOldView);
            mEffectNoDrawBridge.setVisibleWidget(true);
        }
    }

}
