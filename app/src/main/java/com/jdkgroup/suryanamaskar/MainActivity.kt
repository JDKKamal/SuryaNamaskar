package com.jdkgroup.suryanamaskar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity(), MyAdapter.ItemListener {
    private var rvKotlin: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvKotlin = findViewById(R.id.rvKotlin)

        val listString = ArrayList<SeriesModel>()
        listString.add(SeriesModel("kamalesh"))
        listString.add(SeriesModel("nayan"))

        val adapter = MyAdapter(this, listString)
        adapter.setOnListener(this)
        rvKotlin!!.setHasFixedSize(true)
        rvKotlin!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvKotlin!!.adapter = adapter
    }

    override fun onClothEdit(id: Int, seriesModel: SeriesModel) {
        System.out.println(seriesModel.name)
    }
}
