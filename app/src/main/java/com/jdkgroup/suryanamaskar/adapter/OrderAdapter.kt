package com.jdkgroup.suryanamaskar.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jdkgroup.customview.recyclerview.BaseRecyclerView
import com.jdkgroup.customview.recyclerview.BaseViewHolder
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.model.ModelProduct

import butterknife.BindView

class OrderAdapter(private val context: Context, private val profiles: MutableList<ModelProduct>, private val onCartItemChange: OnCartItemChange?) : BaseRecyclerView<ModelProduct>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int): ModelProduct {
        return profiles[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ModelProduct> {
        return ViewHolder(inflater.inflate(R.layout.itemview_order, parent, false))
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    internal inner class ViewHolder(itemView: View) : BaseViewHolder<ModelProduct>(itemView) {
        @BindView(R.id.appTvProductName)
        var appTvProductName: AppCompatTextView? = null
        @BindView(R.id.ivDelete)
        var removeProduct: AppCompatImageView? = null
        @BindView(R.id.ivMinusQty)
        var ivMinus: AppCompatImageView? = null
        @BindView(R.id.ivPlusQty)
        var ivPlus: AppCompatImageView? = null
        @BindView(R.id.tvPrice)
        var price: AppCompatTextView? = null
        @BindView(R.id.tvQty)
        var quantity: AppCompatTextView? = null

        override fun populateItem(profile: ModelProduct) {
            appTvProductName!!.text = profile.name!! + ""
            price!!.text = profile.price.toString() + ""
            quantity!!.text = profile.quantity.toString() + ""

            ivMinus!!.setOnClickListener { v ->
                val strQty = quantity!!.text.toString()
                var qty = 1
                try {
                    qty = Integer.parseInt(strQty)
                    if (qty > 1)
                        qty--
                } catch (e: Exception) {
                }

                quantity!!.text = qty.toString() + ""
                profiles[adapterPosition].quantity = qty
                updateData()
            }

            ivPlus!!.setOnClickListener { v ->
                val strQty = quantity!!.text.toString()
                var qty = 0
                try {
                    qty = Integer.parseInt(strQty)
                } catch (e: Exception) {
                }

                qty++
                quantity!!.text = qty.toString() + ""
                profiles[adapterPosition].quantity = qty
                updateData()
            }

            removeProduct!!.setOnClickListener { view ->
                profiles[adapterPosition].quantity
                profiles.remove(profiles[adapterPosition])
                notifyDataSetChanged()
                updateData()
            }
        }
    }

    fun updateData() {
        var grandTotal = 0.00
        for (product in profiles) {
            try {
                grandTotal += product.quantity * product.price
            } catch (e: Exception) {
            }

        }
        onCartItemChange?.onCartChange(grandTotal, profiles)
    }

    interface OnCartItemChange {
        fun onCartChange(grandTotal: Double, cartItems: List<ModelProduct>)
    }
}
