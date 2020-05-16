package com.e.myquizz.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e.myquizz.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicCustomViewHolder> {

    Context mContext;
    ArrayList<String> mArrayList;
    ArrayList<GradientDrawable> mGradientDrawable;

    public TopicAdapter(Context context,ArrayList<String> arrayList){
        mContext=context;
        mArrayList=arrayList;
        mGradientDrawable = new ArrayList<>();
        fillGradientList(mContext);
    }
    public void addTopic(String topic){
        mArrayList.add(topic);
    }
    @NonNull
    @Override
    public TopicCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topics_item_list_view,parent,false);
       return new TopicCustomViewHolder(view);
    }

    private void fillGradientList(Context context) {
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context, R.color.gradient_1_start), ContextCompat.getColor(context, R.color.gradient_1_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context, R.color.gradient_2_start), ContextCompat.getColor(context, R.color.gradient_2_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context, R.color.gradient_3_start), ContextCompat.getColor(context, R.color.gradient_3_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context, R.color.gradient_4_start), ContextCompat.getColor(context, R.color.gradient_4_end)));
    }

    private GradientDrawable getTempGradientDrawable(int startColor, int endColor){
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BR_TL,new int[]{startColor,endColor});
        drawable.setDither(true);
        drawable.setGradientCenter(drawable.getIntrinsicWidth()/8,drawable.getIntrinsicHeight()/2);
        drawable.setCornerRadius(20);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setUseLevel(true);
        return drawable;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicCustomViewHolder holder, int position) {
            String topicName = mArrayList.get(position);
            holder.mTextView.setText(topicName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            holder.mTopicRelativeLayout.setBackground(mGradientDrawable.get(position % 4));
        } else {
            holder.mTopicRelativeLayout.setBackground(mGradientDrawable.get(position % 4));
        }
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    static class TopicCustomViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextView;
        private RelativeLayout mTopicRelativeLayout;

        public TopicCustomViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView=itemView.findViewById(R.id.topicsImageView);
            mTextView=itemView.findViewById(R.id.topicsTextView);
            mTopicRelativeLayout=itemView.findViewById(R.id.topicRelativeLayout);
        }
    }
}
