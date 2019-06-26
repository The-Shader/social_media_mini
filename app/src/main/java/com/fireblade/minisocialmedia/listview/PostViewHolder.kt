package com.fireblade.minisocialmedia.listview

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fireblade.minisocialmedia.details.DetailsActivity
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

  /*val title = view.title_textview
  val body = view.body_textview
  val author = view.author_textview
  val numOfComments = view.num_of_comments_textview*/
  lateinit var postItem: PostItem

  init {
    itemView.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    val context = itemView.context.applicationContext
    val showPostIntent = Intent(context, DetailsActivity::class.java)
    showPostIntent.putExtra(POST_KEY, postItem)
    context.startActivity(showPostIntent)
  }

  fun bindPostItem(post: PostItem) {

    postItem = post

    itemView.title_textview.text = postItem.title
    itemView.body_textview.text = postItem.body
    itemView.author_textview.text = postItem.author
    itemView.num_of_comments_textview.text = postItem.numOfComments.toString()
  }

  companion object {
    private val POST_KEY = "POST"
  }
}