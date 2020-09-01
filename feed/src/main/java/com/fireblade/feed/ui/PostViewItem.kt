package com.fireblade.feed.ui

import com.fireblade.core.post.PostItem
import com.fireblade.feed.business.FeedViewModel
import com.fireblade.feed.R
import com.fireblade.persistence.user.AvatarColor
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostViewItem(private val postItem: PostItem, private val viewModel: FeedViewModel): Item() {
    override fun getLayout(): Int = R.layout.post_list_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
      viewHolder.itemView.title_textview.text = postItem.title
      viewHolder.itemView.body_textview.text = postItem.body
      viewHolder.itemView.author_textview.text = postItem.author
      viewHolder.itemView.num_of_comments_textview.text =
          viewHolder.itemView.context.resources.getString(R.string.number_of_comments, postItem.numOfComments)
      viewHolder.itemView.user_avatar.setColorFilter(postItem.avatarColor)
      viewHolder.itemView.user_avatar.setBackgroundColor(AvatarColor.generateBackgroundColor(postItem.avatarColor))
      viewHolder.itemView.setOnClickListener {
        viewModel.onPostItemClicked(postItem)
      }
      Picasso
          .get()
          .load(R.drawable.ic_user_avatar)
          .placeholder(R.drawable.ic_user_avatar_placeholder)
          .fit()
          .centerCrop()
          .into(viewHolder.itemView.user_avatar)
    }
}