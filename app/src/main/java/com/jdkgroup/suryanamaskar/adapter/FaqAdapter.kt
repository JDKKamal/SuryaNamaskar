package com.jdkgroup.suryanamaskar.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdkgroup.customview.recyclerview.BaseRecyclerView
import com.jdkgroup.customview.recyclerview.BaseViewHolder
import com.jdkgroup.model.api.faq.ListFaqSection
import com.jdkgroup.suryanamaskar.R

class FaqAdapter(context: Context, listFaq: List<ListFaqSection>) : BaseRecyclerView<ListFaqSection>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val listFaq: List<ListFaqSection> = listFaq
    private var mIsViewExpanded = -1

    override fun getItem(position: Int): ListFaqSection {
        return listFaq[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ListFaqSection> {
        return ViewHolder(inflater.inflate(R.layout.itemview_faq, parent, false))
    }

    override fun getItemCount(): Int {
        return listFaq.size
    }

    internal inner class ViewHolder(itemView: View) : BaseViewHolder<ListFaqSection>(itemView), View.OnClickListener  {

        var appTvQuestion: AppCompatTextView? = null
        var appTvAnswer: AppCompatTextView? = null
        var appIvArrowDown: AppCompatImageView? = null

        init {
            appTvQuestion = itemView.findViewById(R.id.appTvQuestion)
            appTvAnswer = itemView.findViewById(R.id.appTvAnswer)
            appIvArrowDown = itemView.findViewById(R.id.appIvArrowDown)

            appIvArrowDown!!.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            when (view.id) {
                R.id.appIvArrowDown -> {
                    mIsViewExpanded = if (mIsViewExpanded == adapterPosition) {
                        -1;
                    } else {
                        adapterPosition;
                    }
                    notifyDataSetChanged();
                }
            }
        }

        override fun populateItem(listFaq: ListFaqSection) {
            appTvQuestion!!.text = listFaq.question

            if (listFaq.faqlist!!.isNotEmpty()) {
                appTvAnswer!!.text = listFaq.faqlist!![0].answer
            }

            if (mIsViewExpanded == adapterPosition) {
                appIvArrowDown!!.setImageResource(R.drawable.ic_arrow_down)
            } else {
                appIvArrowDown!!.setImageResource(R.drawable.ic_arrow_up)
            }

        }
    }
}
