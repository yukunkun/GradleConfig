package com.yk.gradleconfig.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.yk.gradleconfig.R;
import com.yk.gradleconfig.adapter.RVFooterAdapter;
import com.yk.gradleconfig.adapter.RVHeaderAdapter;
import com.yk.gradleconfig.utils.log.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DragActivity extends AppCompatActivity {

    private RecyclerView mRvHeader;
    private RecyclerView mRvFooter;
    private GridLayoutManager mGridLayoutManagerHeader;
    private GridLayoutManager mGridLayoutManagerFooter;
    private List<String> mHeaderList=new ArrayList<>();
    private List<String> mFooterList=new ArrayList<>();
    private RVHeaderAdapter mRvHeaderAdapter;
    private RVFooterAdapter mRvFooterAdapter;

    public static void start(Context context){
        Intent intent=new Intent(context,DragActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        mRvHeader = (RecyclerView) findViewById(R.id.recycler_header);
        mRvFooter = (RecyclerView) findViewById(R.id.recycler_footer);
        mHeaderList.addAll(Arrays.asList(getResources().getStringArray(R.array.header)));
        mFooterList.addAll(Arrays.asList(getResources().getStringArray(R.array.footer)));
        initRV();
        updateRV();
        initListener();
    }

    private void initListener() {
        mRvHeaderAdapter.setOnItemClickListener(new RVHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                mFooterList.add(mHeaderList.get(position));
                mHeaderList.remove(position);
                mRvHeaderAdapter.notifyItemRemoved(position);
                mRvHeaderAdapter.notifyItemRangeChanged(position,mHeaderList.size()-position);
                mRvFooterAdapter.notifyItemInserted(mFooterList.size());
                mRvFooterAdapter.notifyItemRangeChanged(mFooterList.size(),mFooterList.size()+1);
            }
        });

        mRvFooterAdapter.setOnItemClickListener(new RVFooterAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                mHeaderList.add(mFooterList.get(position));
                mFooterList.remove(position);

                mRvHeaderAdapter.notifyDataSetChanged();
                mRvFooterAdapter.notifyDataSetChanged();
            }
        });
    }

    private void updateRV() {
        mRvHeaderAdapter.notifyDataSetChanged();
        mRvFooterAdapter.notifyDataSetChanged();
    }

    private void initRV() {

        mGridLayoutManagerHeader = new GridLayoutManager(this,5);
        mGridLayoutManagerFooter = new GridLayoutManager(this,5);
        mRvHeaderAdapter = new RVHeaderAdapter(this,mHeaderList);
        mRvFooterAdapter = new RVFooterAdapter(this,mFooterList);
        mRvHeader.setLayoutManager(mGridLayoutManagerHeader);
        mRvFooter.setLayoutManager(mGridLayoutManagerFooter);
        mRvHeader.setAdapter(mRvHeaderAdapter);
        mRvFooter.setAdapter(mRvFooterAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemDragCallBack());
        itemTouchHelper.attachToRecyclerView(mRvHeader);
    }

    class ItemDragCallBack extends ItemTouchHelper.Callback {
        //通过返回值来设置是否处理某次拖曳或者滑动事件
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                //注意：和拖曳的区别就是在这里
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
//                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

//            dragFlags 是拖拽标志，
//            swipeFlags 是滑动标志，
//            swipeFlags 都设置为0，暂时不考虑滑动相关操作。
        }

        //当长按并进入拖曳状态时，拖曳的过程中不断的回调此方法
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //拖动的 item 的下标a
            int fromPosition = viewHolder.getAdapterPosition();
            //目标 item 的下标，目标 item 就是当拖曳过程中，不断和拖动的 item 做位置交换的条目。
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mHeaderList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mHeaderList, i, i - 1);
                }
            }
            mRvHeaderAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        //滑动删除的回调
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int adapterPosition = viewHolder.getAdapterPosition();
//            mJokeAdapter.notifyItemRemoved(adapterPosition);
//            jokeInfoList.remove(adapterPosition);

        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                //给被拖曳的 item 设置一个深颜色背景
                viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color_73b4b3b3));
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            //给已经完成拖曳的 item 恢复开始的背景。
//            this.updateData(mListName);
            //这里我们设置的颜色尽量和你 item 在 xml 中设置的颜色保持一致
            viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color_34b4b3b3));
            LogUtil.i("===========",mHeaderList.toString());
            mRvHeaderAdapter.update(mHeaderList);
        }
    }
}
