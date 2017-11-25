package cn.cbg.androidtvdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by office on 2017/11/24.
 */

public class ExamAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> stringList;

    public ExamAdapter(Context context,List<String> stringList){
        this.context=context;
        this.stringList=stringList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.exam_item,parent,false);
        return new ExamHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExamHolder examHolder= (ExamHolder) holder;
        examHolder.textView.setText(stringList.get(position)+"");

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ExamHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public  ExamHolder(View view){
            super(view);
            textView= (TextView) view.findViewById(R.id.item_text);
        }
    }
}
