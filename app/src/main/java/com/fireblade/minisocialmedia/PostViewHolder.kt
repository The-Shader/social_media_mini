package com.fireblade.minisocialmedia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  val title = view.title_textview
  val body = view.body_textview
  val author = view.author_textview
  val numOfComments = view.num_of_comments_textview
}