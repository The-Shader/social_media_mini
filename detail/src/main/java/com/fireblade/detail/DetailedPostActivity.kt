package com.fireblade.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.fireblade.core.comment.CommentItem
import com.fireblade.core.post.PostItem
import com.fireblade.persistence.user.AvatarColor
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailedPostActivity : AppCompatActivity(), HasAndroidInjector, IDetailedPostView {

  private val commentsAdapter: GroupAdapter<GroupieViewHolder> by lazy { GroupAdapter<GroupieViewHolder>() }

  @Inject
  lateinit var presenter: DetailedPostPresenter

  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): DispatchingAndroidInjector<Any> = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

    comments_recyclerview.adapter = commentsAdapter

    comments_recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
      setDrawable(resources.getDrawable(R.drawable.divider_item_decoration, null))
    })
  }

  override fun onStart() {
    super.onStart()
    val post: PostItem = intent.getParcelableExtra(resources.getString(R.string.post_key)) ?: PostItem(-1, "", "", "")
    setPostDetails(post)
    presenter.loadCommentsForPost(post)
  }

  override fun onStop() {
    presenter.destroy()
    super.onStop()
  }

  private fun setPostDetails(postItem: PostItem) {
    author_textview.text = postItem.author
    body_textview.text = postItem.body
    title_textview.text = postItem.title
    num_of_comments_textview.text = resources.getString(R.string.number_of_comments, postItem.numOfComments)
    user_avatar.setColorFilter(postItem.avatarColor)
    user_avatar.setBackgroundColor(AvatarColor.generateBackgroundColor(postItem.avatarColor))
    Picasso.get().load(R.drawable.ic_user_avatar).placeholder(R.drawable.ic_user_avatar_placeholder).fit().centerCrop().into(user_avatar)
  }

  override fun setComments(comments: List<CommentItem>) {

    commentsAdapter.addAll(
      comments.map {
        CommentViewItem(it)
      }.toList()
    )
  }

  override fun handleError(error: Throwable) {
    Toast.makeText(this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show()
  }
}