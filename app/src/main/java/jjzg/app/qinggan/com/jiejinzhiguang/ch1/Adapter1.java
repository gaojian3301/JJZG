package jjzg.app.qinggan.com.jiejinzhiguang.ch1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import jjzg.app.qinggan.com.jiejinzhiguang.R;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder1>{
    private static final String TAG = "Adapter1";
    private Context mContext;
    private List<String> datas;

    private OnItemClickListener clickListener;

    public Adapter1(Context context,List<String> lists){
        mContext = context;
        datas = lists;
    }

    @Override
    public Adapter1.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ch1_recycler_item, parent, false);
        return new ViewHolder1(view);
    }

    Random random = new Random(20);
    @Override
    public void onBindViewHolder(final Adapter1.ViewHolder1 holder, int position) {
        Log.d(TAG,"onBindViewHolder holder.content = "+holder.content);
        holder.content.setText(datas.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    int pos = holder.getLayoutPosition();
                    clickListener.onItemClick(holder.view,pos);
                }
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(clickListener != null){
                    int pos = holder.getLayoutPosition();
                    clickListener.onItemLongClick(holder.view,pos);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(datas != null){
            return datas.size();
        }
        return 0;
    }

    public void removeData(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        View view;
        TextView content;

        public ViewHolder1(View itemView) {
            super(itemView);
            view = itemView;
            content = itemView.findViewById(R.id.content);
            Log.d(TAG,"ViewHolder1 itemView = "+itemView);
            Log.d(TAG,"ViewHolder1 content = "+content);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
