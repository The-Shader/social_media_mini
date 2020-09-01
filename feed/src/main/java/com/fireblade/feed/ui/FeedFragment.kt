package com.fireblade.feed.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.babylon.orbit2.livedata.sideEffect
import com.babylon.orbit2.livedata.state
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import com.fireblade.detail.ui.DetailedPostActivity
import com.fireblade.feed.R
import com.fireblade.feed.business.FeedSideEffect
import com.fireblade.feed.business.FeedViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : Fragment() {

  private val postAdapter: GroupAdapter<GroupieViewHolder> by lazy { GroupAdapter<GroupieViewHolder>() }

  @Inject
  lateinit var viewModel: FeedViewModel

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
      ResourcesCompat.getDrawable(
        resources,
        R.drawable.divider_item_decoration,
        null
      )?.let { decoration ->
        setDrawable(decoration)
      }
    })

    viewModel.container.state.observe(viewLifecycleOwner, {
      when (it.feedState) {
        State.Ready -> setPostItems(it.posts)
        State.Error -> showErrorState()
        else -> {}
      }
    })

    viewModel.container.sideEffect.observe(viewLifecycleOwner, {
      when (it) {
        is FeedSideEffect.NavigateToDetail -> {
          val appContext = requireContext().applicationContext
          val showPostIntent = Intent(appContext, DetailedPostActivity::class.java)
          showPostIntent.putExtra(PostItem.TAG, it.item)
          showPostIntent.flags += Intent.FLAG_ACTIVITY_NEW_TASK
          appContext.startActivity(showPostIntent)
        }
      }
    })
  }

  private fun setPostItems(postItems: List<PostItem>) {
    postAdapter.addAll(postItems.map {
      PostViewItem(it, viewModel)
    }.toList())

  }

  private fun showErrorState() {
    Toast.makeText(context, getString(R.string.connection_error), Toast.LENGTH_SHORT).show()
  }
}