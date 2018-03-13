package com.jdkgroup.suryanamaskar.stickytimeline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jdkgroup.customview.stickytimelineview.RecyclerSectionItemDecoration
import com.jdkgroup.customview.stickytimelineview.TimeLineRecyclerView
import com.jdkgroup.customview.stickytimelineview.model.SectionInfo
import com.jdkgroup.suryanamaskar.R;

class ActivityStickyTimeLine : AppCompatActivity() {

    companion object {
        private val LAYOUT = R.layout.activity_sticky_time_line
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)


        val timeLineRecyclerView: TimeLineRecyclerView = findViewById(R.id.recycler_view)

        //Currently only LinearLayoutManager is supported.
        timeLineRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //Get data
        val singerList = getSingerList()

        //Add RecyclerSectionItemDecoration.SectionCallback
        timeLineRecyclerView.addItemDecoration(getSectionCallback(singerList))

        //Set Adapter
        timeLineRecyclerView.adapter = SingerAdapter(layoutInflater, singerList, R.layout.recycler_row)
    }

    //Get data method
    private fun getSingerList(): List<Singer> = SingerRepo().singerList


    //Get SectionCallback method
    private fun getSectionCallback(singerList: List<Singer>): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            //In your data, implement a method to determine if this is a section.
            override fun isSection(position: Int): Boolean = singerList[position].debuted != singerList[position - 1].debuted

            //Implement a method that returns a SectionHeader.
            override fun getSectionHeader(position: Int): SectionInfo? = SectionInfo(singerList[position].debuted, singerList[position].group)
        }
    }
}
