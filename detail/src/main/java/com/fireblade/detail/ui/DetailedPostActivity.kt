package com.fireblade.detail.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.babylon.orbit2.livedata.state
import com.fireblade.core.comment.CommentItem
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import com.fireblade.detail.R
import com.fireblade.detail.business.DetailedPostViewModel
import com.fireblade.persistence.ISocialMediaRepository
import com.fireblade.persistence.user.AvatarColor
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailedPostActivity : AppCompatActivity(), HasAndroidInjector {

  private val commentsAdapter: GroupAdapter<GroupieViewHolder> by lazy { GroupAdapter<GroupieViewHolder>() }

  @Inject
  lateinit var socialMediaRepository: ISocialMediaRepository

  private val viewModel: DetailedPostViewModel by viewModels {
    DetailedPostViewModel.Factory(this, socialMediaRepository, intent.extras)
  }

  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): DispatchingAndroidInjector<Any> = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

    comments_recyclerview.adapter = commentsAdapter

    comments_recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
      ResourcesCompat.getDrawable(
        resources,
        R.drawable.divider_item_decoration,
        null
      )?.let { decoration ->
        setDrawable(decoration)
      }
    })
  }

  override fun onStart() {
    super.onStart()
    val post: PostItem = intent.getParcelableExtra(PostItem.TAG) ?: PostItem.empty()
    setPostDetails(post)
    viewModel.container.state.observe(this, {
      when (it.detailState) {
        State.Ready -> setComments(it.commentItems)
        State.Error -> showErrorState()
        else -> {}
      }
    })
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

  private fun setComments(comments: List<CommentItem>) {
    commentsAdapter.addAll(
      comments.map {
        CommentViewItem(it)
      }.toList()
    )
  }

  private fun showErrorState() {
    Toast.makeText(this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show()
  }
}