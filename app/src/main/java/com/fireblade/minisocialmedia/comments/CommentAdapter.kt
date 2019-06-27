package com.fireblade.minisocialmedia.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fireblade.minisocialmedia.R

class CommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  var items: List<CommentItem> = listOf()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun getItemCount(): Int = items.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return CommentViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.comment_list_item,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is CommentViewHolder) {
      holder.bindPostItem(items[position])
    }
  }
}