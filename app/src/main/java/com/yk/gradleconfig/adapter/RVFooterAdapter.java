package com.yk.gradleconfig.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yk.gradleconfig.R;

import java.util.List;

/**
 * author: kun .
 * date:   On 2018/12/28
 */
public class RVFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    List<String> mPlayAllBeans;

    public RVFooterAdapter(Context context, List<String> playAllBeans) {
        mContext = context;
        mPlayAllBeans=playAllBeans;
    }

    public void notifyData(List<String> mPlayAllBeans){
        this.mPlayAllBeans=mPlayAllBeans;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rv_footer_item, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MyHolder) {
            String s = mPlayAllBeans.get(position);
            ((MyHolder) holder).mTextView.setText(s);
            ((MyHolder) holder).mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPlayAllBeans.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public MyHolder(View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.tv_test);

        }
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
