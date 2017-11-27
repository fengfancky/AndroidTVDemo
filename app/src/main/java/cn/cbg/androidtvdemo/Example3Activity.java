package cn.cbg.androidtvdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by office on 2017/11/27.
 */

public class Example3Activity extends AppCompatActivity {

    FragmentManager fragmentManager=getSupportFragmentManager();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam3);
        fragmentManager.beginTransaction().replace(R.id.frame,new Example3Fragment()).commitAllowingStateLoss();
    }
}
