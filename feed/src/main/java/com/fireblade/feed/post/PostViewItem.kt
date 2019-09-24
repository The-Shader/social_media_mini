package com.fireblade.feed.post

import android.content.Intent
import android.view.View
import com.fireblade.core.post.PostItem
import com.fireblade.detail.DetailedPostActivity
import com.fireblade.feed.R
import com.fireblade.persistence.user.AvatarColor
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostViewItem(private val postItem: PostItem): Item(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.post_list_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
      viewHolder.itemView.title_textview.text = postItem.title
      viewHolder.itemView.body_textview.text = postItem.body
      viewHolder.itemView.author_textview.text = postItem.author
      viewHolder.itemView.num_of_comments_textview.text = viewHolder.itemView.context.resources.getString(R.string.number_of_comments, postItem.numOfComments)
      viewHolder.itemView.user_avatar.setColorFilter(postItem.avatarColor)
      viewHolder.itemView.user_avatar.setBackgroundColor(AvatarColor.generateBackgroundColor(postItem.avatarColor))
      viewHolder.itemView.setOnClickListener(this)
      Picasso.get().load(R.drawable.ic_user_avatar).placeholder(R.drawable.ic_user_avatar_placeholder).fit().centerCrop().into(viewHolder.itemView.user_avatar)
    }

  override fun onClick(v: View) {
    val context = v.context.applicationContext
    val showPostIntent = Intent(context, DetailedPostActivity::class.java)
    showPostIntent.putExtra(v.context.resources.getString(R.string.post_key), postItem)
    showPostIntent.flags += Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(showPostIntent)
  }
}