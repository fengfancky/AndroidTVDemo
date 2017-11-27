package cn.cbg.androidtvdemo;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.ui.BaseFragment;
import com.open.androidtvwidget.view.MainUpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by office on 2017/11/27.
 */

public class Example3Fragment extends BaseFragment {

    private RecyclerViewTV recyclerViewTV;
    private MainUpView mainUpView;
    private List<String> list=new ArrayList<>();
    private ExamAdapter examAdapter;

    @Override
    public int getLayout() {
        return R.layout.exam3_fragment_layout;
    }

    @Override
    public void initView(View view) {
        recyclerViewTV= (RecyclerViewTV) view.findViewById(R.id.recycler);
        mainUpView= (MainUpView) view.findViewById(R.id.mainUpView);
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
    public void onItemClick(View itemView, int position) {

    }

    @Override
    public void onItemSelected(View itemView, int position) {

    }

    @Override
    public void unItemSelected(View itemView, int position) {

    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        examAdapter=new ExamAdapter(getActivity(),list);
        return examAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),6);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        return gridLayoutManager;
    }
}
