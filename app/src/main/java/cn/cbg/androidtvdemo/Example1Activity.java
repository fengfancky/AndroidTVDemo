package cn.cbg.androidtvdemo;

import android.os.Bundle;

import com.open.androidtvwidget.ui.MainUpViewActivity;

/**
 * Created by office on 2017/11/24.
 */

public class Example1Activity extends MainUpViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(R.drawable.white_light,12,12,12,12);
    }
}
