package com.chintansoni.buddies.util

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setInfiniteScroll(func: RecyclerView.() -> Unit) {
    this.adapter?.let {
        this.addOnScrollListener(object : InfiniteScrollListener() {
            override fun fetchNext() {
                func.invoke(this@setInfiniteScroll)
            }
        })
    }
}