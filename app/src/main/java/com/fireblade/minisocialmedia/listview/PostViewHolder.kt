package com.fireblade.minisocialmedia.listview

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fireblade.minisocialmedia.R
import com.fireblade.minisocialmedia.core.Colorizer
import com.fireblade.minisocialmedia.details.DetailedPostActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_list_item.view.*
class PostViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

  lateinit var postItem: PostItem

  init {
    itemView.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    val context = itemView.context.applicationContext
    val showPostIntent = Intent(context, DetailedPostActivity::class.java)
    showPostIntent.putExtra(itemView.context.resources.getString(R.string.post_key), postItem)
    showPostIntent.flags += Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(showPostIntent)
  }

  fun bindPostItem(post: PostItem) {

    postItem = post

    itemView.title_textview.text = postItem.title
    itemView.body_textview.text = postItem.body
    itemView.author_textview.text = postItem.author
    itemView.num_of_comments_textview.text = itemView.context.resources.getString(R.string.number_of_comments, postItem.numOfComments)
    itemView.user_avatar.setColorFilter(postItem.avatarColor)
    itemView.user_avatar.setBackgroundColor(Colorizer.generateBackgroundColor(postItem.avatarColor))

    Picasso.get().load(R.drawable.ic_user_avatar).placeholder(R.drawable.ic_user_avatar_placeholder).fit().centerCrop().into(itemView.user_avatar)
  }
}