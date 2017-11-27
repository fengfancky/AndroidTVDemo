package cn.cbg.androidtvdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.open.androidtvwidget.leanback.mode.OpenPresenter;

/**
 * Created by office on 2017/11/27.
 */

public class CustomHeadPresenter extends OpenPresenter {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_header, parent, false);
        return new CustomHeadPresenter.ItemHeadViewHolder(headview);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewHolder.view.getLayoutParams();
        lp.weight = LinearLayout.LayoutParams.MATCH_PARENT;
        ((TextView) viewHolder.view).setText((String)item);
    }

    static class ItemHeadViewHolder extends OpenPresenter.ViewHolder {
        public ItemHeadViewHolder(View view) {
            super(view);
        }
    }
}
