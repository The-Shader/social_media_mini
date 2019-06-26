package com.fireblade.minisocialmedia.listview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fireblade.minisocialmedia.R

class PostAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  var items: List<PostItem> = listOf()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun getItemCount(): Int = items.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return PostViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.post_list_item,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is PostViewHolder) {
      holder.bindPostItem(items[position])
    }
  }
}