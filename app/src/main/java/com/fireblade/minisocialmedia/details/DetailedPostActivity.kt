package com.fireblade.minisocialmedia.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.fireblade.minisocialmedia.R
import com.fireblade.minisocialmedia.persistence.user.AvatarColor
import com.fireblade.minisocialmedia.listview.PostItem
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailedPostActivity : AppCompatActivity(), HasSupportFragmentInjector, IDetailedPostView {

  private val commentsAdapter: CommentAdapter by lazy { CommentAdapter() }

  @Inject
  lateinit var presenter: DetailedPostPresenter

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

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
    val post: PostItem = intent.getParcelableExtra(resources.getString(R.string.post_key))
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

    commentsAdapter.items = comments
  }

  override fun handleError(error: Throwable) {
    Toast.makeText(this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show()
  }
}