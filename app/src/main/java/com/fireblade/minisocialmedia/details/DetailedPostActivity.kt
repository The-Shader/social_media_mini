package com.fireblade.minisocialmedia.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fireblade.minisocialmedia.R
import com.fireblade.minisocialmedia.comments.CommentViewFragment
import com.fireblade.minisocialmedia.comments.ICommentEvents
import com.fireblade.minisocialmedia.listview.PostItem
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailedPostActivity : AppCompatActivity(), HasSupportFragmentInjector, ICommentEvents {

  private val commentViewFragment : CommentViewFragment by lazy { CommentViewFragment() }

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

    supportFragmentManager.beginTransaction().apply {
      replace(R.id.comments_layout, commentViewFragment, "commentViewFragment")
    }.commitAllowingStateLoss()
  }

  override fun fetchComments() {
    val post: PostItem = intent.getParcelableExtra(resources.getString(R.string.post_key))
    setPostDetails(post)
    commentViewFragment.presenter.loadCommentsForPost(post)
  }

  private fun setPostDetails(postItem: PostItem) {
    author_textview.text = postItem.author
    body_textview.text = postItem.body
    title_textview.text = postItem.title
    num_of_comments_textview.text = resources.getString(R.string.number_of_comments, postItem.numOfComments)
  }
}