package com.fireblade.detail

import com.fireblade.core.comment.CommentItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.comment_list_item.view.*

class CommentViewItem (private val commentItem: CommentItem): Item() {

  override fun getLayout(): Int = R.layout.comment_list_item

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.itemView.comment_textview.text = commentItem.content
  }
}