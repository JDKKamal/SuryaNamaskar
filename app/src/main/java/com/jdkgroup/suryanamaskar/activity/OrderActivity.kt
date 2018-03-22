package com.jdkgroup.suryanamaskar.activity

import android.os.Bundle
import android.support.v7.widget.RecyclerView

import com.jdkgroup.baseclass.BaseActivity
import com.jdkgroup.suryanamaskar.R
import com.jdkgroup.model.ModelProduct
import com.jdkgroup.suryanamaskar.adapter.OrderAdapter
import kotlinx.android.synthetic.main.activity_faq.*

import java.util.ArrayList

class OrderActivity : BaseActivity() {

    internal var recyclerView: RecyclerView? = null

    private val listProduct: MutableList<ModelProduct>
        get() {
            val listProduct = ArrayList<ModelProduct>()
            listProduct.add(ModelProduct(1, "Name 1", 1, 10.0))
            listProduct.add(ModelProduct(2, "Name 2", 1, 20.0))
            listProduct.add(ModelProduct(3, "Name 3", 1, 10.0))
            listProduct.add(ModelProduct(4, "Name 4", 1, 10.0))
            listProduct.add(ModelProduct(5, "Name 5", 1, 30.0))
            listProduct.add(ModelProduct(6, "Name 6", 1, 10.0))
            listProduct.add(ModelProduct(7, "Name 7", 1, 10.0))
            listProduct.add(ModelProduct(8, "Name 8", 1, 40.0))
            listProduct.add(ModelProduct(8, "Name 9", 1, 10.0))
            listProduct.add(ModelProduct(10, "Name 10", 1, 10.0))

            return listProduct
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        recyclerView = findViewById(R.id.recyclerView)
        setRecyclerView(rvFaq, 0, recyclerViewLinearLayout)

        val adapter = OrderAdapter(this, listProduct, object : OrderAdapter.OnCartItemChange {
            override fun onCartChange(grandTotal: Double, cartItems: List<ModelProduct>) {
                println("Tag" + grandTotal)
            }
        })
        recyclerView!!.adapter = adapter

    }

}
