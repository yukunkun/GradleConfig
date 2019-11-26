package com.yk.gradleconfig.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yk.gradleconfig.R;
import java.util.List;

/**
 * author: kun .
 * date:   On 2018/12/28
 */
public class RVVerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    List<String> mPlayAllBeans;

    public RVVerticalAdapter(Context context, List<String> playAllBeans) {
        mContext = context;
        mPlayAllBeans=playAllBeans;
    }

    public void notifyData(List<String> mPlayAllBeans){
        this.mPlayAllBeans=mPlayAllBeans;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rv_layout_item, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyHolder) {
            String s = mPlayAllBeans.get(position);
            Glide.with(mContext).load(s).into(((MyHolder) holder).mImageView);
//            Glide.with(mContext).load(R.mipmap.ic_launcher).into(((MyHolder) holder).mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mPlayAllBeans.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public MyHolder(View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.iv_cover);

            ViewGroup.LayoutParams layoutParams=mImageView.getLayoutParams();
            layoutParams.width= getWidth(mContext);
            layoutParams.height= getHeight(mContext);
            mImageView.setLayoutParams(layoutParams);
        }
    }

    public int getWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return  width;
    }

    /**
     * @return screen height
     */
    public int getHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

}
