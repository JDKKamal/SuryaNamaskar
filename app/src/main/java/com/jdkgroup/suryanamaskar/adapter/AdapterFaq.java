package com.jdkgroup.suryanamaskar.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jdkgroup.suryanamaskar.R;
import java.util.List;


public class AdapterFaq extends RecyclerView.Adapter<AdapterFaq.MyViewHolder> {

    private Activity activity;
    private List<FaqData> alFaqData;
    private int mIsViewExpanded = -1;

    public AdapterFaq(Activity activity, List<FaqData> alFaqData) {
        this.activity = activity;
        this.alFaqData = alFaqData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvQuestionNoFAQElement, tvQuestionFrequentlyAskedQuestionsElement, tvQuestionAnswerFrequentlyAskedQuestionsElement;
        private AppCompatImageView ivArrowUpDownFrequentlyAskedQuestionsElement;
        private LinearLayout llListFAQ, llQuestionListFAQ;

        public MyViewHolder(View view) {
            super(view);
            tvQuestionNoFAQElement = (AppCompatTextView) view.findViewById(R.id.tvQuestionNoFAQElement);
            tvQuestionFrequentlyAskedQuestionsElement = (AppCompatTextView) view.findViewById(R.id.tvQuestionFrequentlyAskedQuestionsElement);
            tvQuestionAnswerFrequentlyAskedQuestionsElement = (AppCompatTextView) view.findViewById(R.id.tvQuestionAnswerFrequentlyAskedQuestionsElement);
            ivArrowUpDownFrequentlyAskedQuestionsElement = (AppCompatImageView) view.findViewById(R.id.ivArrowUpDownFrequentlyAskedQuestionsElement);
            llQuestionListFAQ= (LinearLayout) view.findViewById(R.id.llQuestionListFAQ);
            llListFAQ = (LinearLayout) view.findViewById(R.id.llListFAQ);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.itemview_faq, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        FaqData faqData = alFaqData.get(position);
        holder.tvQuestionNoFAQElement.setText((holder.getAdapterPosition() + 1) + ". ");
        holder.tvQuestionFrequentlyAskedQuestionsElement.setText(faqData.getQuestion());
        holder.tvQuestionAnswerFrequentlyAskedQuestionsElement.setText(faqData.getAnswer());

        if (mIsViewExpanded == position) {
            holder.tvQuestionAnswerFrequentlyAskedQuestionsElement.setVisibility(View.VISIBLE);
            holder.llListFAQ.setBackgroundColor(Color.parseColor("#F3F3F3"));
            holder.ivArrowUpDownFrequentlyAskedQuestionsElement.setImageResource(R.drawable.arrow_up);
        } else {
            holder.llListFAQ.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tvQuestionAnswerFrequentlyAskedQuestionsElement.setVisibility(View.GONE);
            holder.ivArrowUpDownFrequentlyAskedQuestionsElement.setImageResource(R.drawable.arrow_down);
        }

        holder.llQuestionListFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsViewExpanded == position) {
                    mIsViewExpanded = -1;
                } else {
                    mIsViewExpanded = position;
                    FaqActivity.rvFaq.smoothScrollToPosition(position);
                }
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return alFaqData.size();
    }
    public void updateFaqList(List<FaqData> searchCoursesDataList) {
        this.alFaqData = searchCoursesDataList;
        notifyDataSetChanged();
    }
}
