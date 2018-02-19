package com.jdkgroup.suryanamaskar

import android.app.Activity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyAdapter(private val activity: Activity, private val listString: MutableList<SeriesModel>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var listener: ItemListener? = null

    fun setOnListener(listener: ItemListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemview_name, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listString[position])
    }

    override fun getItemCount(): Int {
        return listString.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var appTvName: AppCompatTextView? = null

        init {
            appTvName = itemView.findViewById(R.id.appTvName) as AppCompatTextView
            appTvName!!.setOnClickListener(this)
        }

        fun bindItems(user: SeriesModel) {
            appTvName!!.text = user.name
        }

        override fun onClick(view: View) {
            when (view.id) {
                R.id.appTvName -> {
                    listener!!.onClothEdit(adapterPosition, listString[adapterPosition])
                }
            }
        }
    }

    interface ItemListener {
        fun onClothEdit(id: Int, seriesModel : SeriesModel)
    }
}