package com.jdkgroup.suryanamaskar.adapter

import android.content.Context
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

import com.jdkgroup.model.ModelMultipleSelect
import com.jdkgroup.suryanamaskar.R

import butterknife.BindView
import butterknife.ButterKnife

class MultipleSelectAdapter(private val context: Context, private val listMultipleSelect: List<ModelMultipleSelect>, private val onCheckedChange: OnCheckedChange) : RecyclerView.Adapter<MultipleSelectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemview_multiple_select, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cbImage!!.text = listMultipleSelect[position].categoryName
        holder.cbImage!!.id = position

        holder.cbImage!!.isChecked = listMultipleSelect[position].isChecked

        /*if(list.get(position).isChecked()) {
            holder.cbImage.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.cbImage.setChecked(true);
        }
        else {
            holder.cbImage.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.cbImage.setChecked(false);
        }*/
    }

    override fun getItemCount(): Int {
        return listMultipleSelect.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.cbImage)
        var cbImage: AppCompatCheckBox? = null

        init {
            cbImage = itemView.findViewById(R.id.cbImage)

            cbImage!!.setOnCheckedChangeListener { buttonView, isChecked ->
                listMultipleSelect[cbImage!!.id].isChecked = isChecked
                if (isChecked)
                    cbImage!!.isChecked = true
                else
                    cbImage!!.isChecked = false
                onCheckedChange.onLoadedList(listMultipleSelect)
            }
        }
    }

    fun resetData() {
        for (c in listMultipleSelect) {
            c.isChecked = false
        }
        notifyDataSetChanged()
    }

    interface OnCheckedChange {
        fun onLoadedList(categories: List<ModelMultipleSelect>)
    }

}