package com.jdkgroup.suryanamaskar.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.tipjar.R;
import com.tipjar.activity.activityView.FaqActivity;
import com.tipjar.activity.activityView.FeedbackActivity;
import com.tipjar.fragment.fragmentModel.ModelFeedback;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by smart on 6/20/2017.
 */

public class AdapterFeedback extends RecyclerView.Adapter<AdapterFeedback.MyViewHolder> {

    private List<ModelFeedback> alFeedback;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvNameFeedbackElement, tvDescriptionFeedbackElement, tvDateFeedbackElement, tvShowMoreFeedbackElement;
        private RatingBar rbFeedbackElement;
        private CircleImageView cvFeedbackElement;

        public MyViewHolder(View view) {
            super(view);
            tvNameFeedbackElement = (AppCompatTextView) view.findViewById(R.id.tvNameFeedbackElement);
            tvDescriptionFeedbackElement = (AppCompatTextView) view.findViewById(R.id.tvDescriptionFeedbackElement);
            tvDateFeedbackElement = (AppCompatTextView) view.findViewById(R.id.tvDateFeedbackElement);
            rbFeedbackElement = (RatingBar) view.findViewById(R.id.rbFeedbackElement);
            cvFeedbackElement = (CircleImageView) view.findViewById(R.id.cvFeedbackElement);
            tvShowMoreFeedbackElement  = (AppCompatTextView) view.findViewById(R.id.tvShowMoreFeedbackElement);
        }
    }

    public AdapterFeedback(Activity activity, List<ModelFeedback> alFeedback) {
        this.activity = activity;
        this.alFeedback = alFeedback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_feedback, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        ModelFeedback feedback = alFeedback.get(position);

        holder.tvNameFeedbackElement.setText(feedback.getName());
        holder.tvDescriptionFeedbackElement.setText(feedback.getDescription());
        holder.tvDateFeedbackElement.setText(feedback.getDate());
        holder.rbFeedbackElement.setRating(position);

        //holder.cvFeedbackElement

        holder.tvShowMoreFeedbackElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.tvShowMoreFeedbackElement.getText().toString().equalsIgnoreCase("Read less"))
                {
                    holder.tvDescriptionFeedbackElement.setMaxLines(4);
                    holder.tvShowMoreFeedbackElement.setText("Read more");
                }
                else
                {
                    holder.tvDescriptionFeedbackElement.setMaxLines(Integer.MAX_VALUE);
                    holder.tvShowMoreFeedbackElement.setText("Read less");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return alFeedback.size();
    }
}
