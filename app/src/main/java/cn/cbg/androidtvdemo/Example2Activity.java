package cn.cbg.androidtvdemo;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.ui.BaseActivity;
import com.open.androidtvwidget.view.MainUpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by office on 2017/11/24.
 */

public class Example2Activity extends BaseActivity implements View.OnFocusChangeListener{

    private RecyclerViewTV recyclerViewTV;
    private MainUpView mainUpView;
    private List<String> list=new ArrayList<>();
    private ExamAdapter examAdapter;
    private TextView examText;

    @Override
    public int getLayout() {
        //布局xml
        return R.layout.exam2;
    }

    @Override
    public void initView() {
        //初始化布局
        recyclerViewTV= (RecyclerViewTV) findViewById(R.id.recycler);
        mainUpView= (MainUpView) findViewById(R.id.mainupview);
        examText= (TextView) findViewById(R.id.exam_text);
        examText.setOnFocusChangeListener(this);

        init(recyclerViewTV,mainUpView,R.drawable.back2,14,12,14,12);

    }

    @Override
    public void loadData() {
        //数据加载
        for (int i=0;i<30;i++){
            list.add("item"+(i+1));
        }
        examAdapter.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        //RecyclerView的LayoutManager类型
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,6);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        return gridLayoutManager;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        //适配器
        examAdapter=new ExamAdapter(this,list);
        return examAdapter;
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Toast.makeText(this, (position+1)+"", Toast.LENGTH_SHORT).show();
        //item的点击事件
    }

    @Override
    public void onItemSelected(View itemView, int position) {
        //选中item
    }

    @Override
    public void onItemUnSelected(View itemView, int position) {
        //失去选中item
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            oldView = v;
            mRecyclerViewBridge.setVisibleWidget(false);
            mRecyclerViewBridge.setFocusView(v, 1.0f);
            v.bringToFront();
        } else {
            mRecyclerViewBridge.setUnFocusView(oldView);
            mRecyclerViewBridge.setVisibleWidget(true);
        }
    }

}
