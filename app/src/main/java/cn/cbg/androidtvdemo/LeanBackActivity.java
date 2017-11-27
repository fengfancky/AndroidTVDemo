package cn.cbg.androidtvdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.mode.ItemListPresenter;
import com.open.androidtvwidget.leanback.mode.ListRow;
import com.open.androidtvwidget.leanback.mode.ListRowPresenter;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by office on 2017/11/27.
 */

public class LeanBackActivity extends AppCompatActivity {

    private RecyclerViewTV rv;
    private List<String> titles=new ArrayList<>();//标题
    List<ListRow> mListRows = new ArrayList<>();
    ListRowPresenter mListRowPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leanback_layout);
        rv= (RecyclerViewTV) findViewById(R.id.recycler);
        initData();

        for (int i=0;i<titles.size();i++){
            List<String> list=new ArrayList<>();
            list.add("item"+(i+1)+"1");
            list.add("item"+(i+1)+"2");
            list.add("item"+(i+1)+"3");
            list.add("item"+(i+1)+"4");
            list.add("item"+(i+1)+"5");
            list.add("item"+(i+1)+"6");
            list.add("item"+(i+1)+"7");
            list.add("item"+(i+1)+"8");
            list.add("item"+(i+1)+"9");
            initLeanBack(i,list);
        }
    }

    private void initData(){
        titles.add("标题一");
        titles.add("标题二");
        titles.add("标题三");
        titles.add("标题四");
        titles.add("标题五");
    }

    private void initLeanBack(int position,List<String> o){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setSelectedItemAtCentered(true);
        ListRow listRow = new ListRow(titles.get(position)); // 标题头.
        listRow.addAll(o);
        ListPresenter listPresenter=new ListPresenter();
        listPresenter.setPresenterItemFocusListener(new ListPresenter.PresenterItemFocusListener() {
            @Override
            public void itemFocusListener(View view, final int position, boolean hasFocus, final int count) {
                if (hasFocus){
                    view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
                    view.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {

                            if (keyCode==KeyEvent.KEYCODE_DPAD_LEFT&&event.getAction()==KeyEvent.ACTION_DOWN&&position==0){
                                return true;
                            }

                            if (keyCode== KeyEvent.KEYCODE_DPAD_RIGHT&&event.getAction()== KeyEvent.ACTION_DOWN&&(position==(count-1))){
                                return true;
                            }
                            return false;
                        }
                    });
                }else {
                    view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(400).start();
                }
            }
        });
        listRow.setOpenPresenter(listPresenter);
        mListRows.add(listRow);

        mListRowPresenter = new ListRowPresenter(mListRows, new CustomHeadPresenter(), new ItemListPresenter());
        mListRowPresenter.setDefaultPos(0, 0); // 设置默认选中.
        GeneralAdapter generalAdapter = new GeneralAdapter(mListRowPresenter);
        rv.setAdapter(generalAdapter);
    }
}
