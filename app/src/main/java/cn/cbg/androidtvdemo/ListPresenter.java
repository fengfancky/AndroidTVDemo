package cn.cbg.androidtvdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.open.androidtvwidget.leanback.mode.DefualtListPresenter;


public class ListPresenter extends DefualtListPresenter {

    Context context;
    PresenterItemFocusListener presenterItemFocusListener;

    public interface PresenterItemFocusListener{
        void itemFocusListener(View view,int position,boolean hasFocus,int count);
    }
    public void setPresenterItemFocusListener(PresenterItemFocusListener presenterItemFocusListener){
        this.presenterItemFocusListener=presenterItemFocusListener;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManger(Context context) {
        this.context=context;
        return super.getLayoutManger(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.presenter_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        FrameLayout frameLayout = (FrameLayout) viewHolder.view;
        ((TextView)frameLayout.findViewById(R.id.item_text)).setText(getItem(position)+"");
        frameLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (presenterItemFocusListener!=null)
                    presenterItemFocusListener.itemFocusListener(v,position,hasFocus,getItemCount());
            }
        });

    }

}
