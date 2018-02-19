package com.jdkgroup.customview.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.view.View

class CustomRecyclerView : RecyclerView {
    private var mOnLoadMoreListener: OnLoadMoreListener? = null
    private var isLoading: Boolean = false
    private var mLoadMoreView: View? = null

    val isRecyclerScrollable: Boolean
        get() = computeVerticalScrollRange() > height

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
    }

    private fun init() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val staggeredGridLayoutManager = recyclerView!!.layoutManager as StaggeredGridLayoutManager
                if (staggeredGridLayoutManager.spanCount == adapter.itemCount - 1 && isRecyclerScrollable && mOnLoadMoreListener != null) {
                    isLoading = true

                    if (mLoadMoreView != null) {
                        mLoadMoreView!!.visibility = View.VISIBLE
                        recyclerView.scrollToPosition(adapter.itemCount - 1)
                    }
                    mOnLoadMoreListener!!.onLoadMore()
                }
            }
        })

    }

    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener, mLoadMoreView: View) {
        this.mOnLoadMoreListener = mOnLoadMoreListener
        this.mLoadMoreView = mLoadMoreView
    }

    fun loadMoreComplete() {
        isLoading = false
        if (mLoadMoreView != null) {
            if (mLoadMoreView!!.visibility == View.VISIBLE) {
                mLoadMoreView!!.visibility = View.GONE
            }
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}
