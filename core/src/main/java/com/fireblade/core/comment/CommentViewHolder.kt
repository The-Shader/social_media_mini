package com.fireblade.core.comment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comment_list_item.view.*

class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  fun bindPostItem(comment: CommentItem) {
    itemView.comment_textview.text = comment.content
  }
}