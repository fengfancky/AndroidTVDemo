package com.open.androidtvwidget.anim;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by office on 2017/6/14.
 */

public class AnimatorUtils {

    private static float  cyc=(float) (3.14*8);
    //最左边控件振动动画
    public static void initLeftAnim(final View view,final int offset){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,cyc);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float) animation.getAnimatedValue();
                float finalValue=(float) (offset/cyc*(cyc-value)*Math.sin(value));
                view.setTranslationX(-finalValue);
            }
        });
        valueAnimator.start();
    }

    //最右边控件振动动画
    public static void initRightAnim(final View view,final int offset){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,cyc);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float) animation.getAnimatedValue();
                float finalValue=(float) (offset/cyc*(cyc-value)*Math.sin(value));
                view.setTranslationX(finalValue);
            }
        });
        valueAnimator.start();
    }
}
