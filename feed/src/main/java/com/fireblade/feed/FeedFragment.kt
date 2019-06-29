package com.fireblade.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.fireblade.feed.post.PostAdapter
import com.fireblade.core.post.PostItem
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : Fragment(), IFeedView {

  private val postAdapter: PostAdapter by lazy { PostAdapter() }

  @Inject
  lateinit var presenter: IFeedPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidSupportInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_feed, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    list_of_posts.adapter = postAdapter

    list_of_posts.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
      setDrawable(resources.getDrawable(R.drawable.divider_item_decoration, null))
    })
  }

  override fun onStart() {
    super.onStart()
    presenter.loadPostItems()
  }

  override fun onStop() {
    presenter.destroy()
    super.onStop()
  }

  override fun setPostItems(postItems: List<PostItem>) {
    postAdapter.items = postItems
  }

  override fun handleError(error: Throwable) {
    Toast.makeText(context, getString(R.string.connection_error), Toast.LENGTH_SHORT).show()
  }
}