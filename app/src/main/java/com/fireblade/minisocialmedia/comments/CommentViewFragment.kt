package com.fireblade.minisocialmedia.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.fireblade.minisocialmedia.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_comment_view.*
import javax.inject.Inject

class CommentViewFragment : Fragment(), ICommentView {

  @Inject
  lateinit var presenter : ICommentPresenter

  private val commentsAdapter: CommentAdapter by lazy { CommentAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidSupportInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_comment_view, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    comments_recyclerview.adapter = commentsAdapter

    comments_recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
      setDrawable(resources.getDrawable(R.drawable.divider_item_decoration, null))
    })
  }

  override fun onStart() {
    super.onStart()
    presenter.fetchComments()
  }

  override fun onStop() {
    presenter.destroy()
    super.onStop()
  }

  override fun setComments(comments: List<CommentItem>) {
    commentsAdapter.items = comments
  }

  override fun handleError(error: Throwable) {
    Toast.makeText(context, "An error occurred while loading data from the network. Check your internet connection.", Toast.LENGTH_SHORT).show()
  }
}