package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.listview.PostItem
import com.fireblade.minisocialmedia.network.IPlaceholderApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentPresenter @Inject constructor(
  private val view: ICommentView,
  private val placeholderApiService: IPlaceholderApiService,
  private val eventHandler: ICommentEvents
) : ICommentPresenter {

  override fun fetchComments() {
    eventHandler.fetchComments()
  }

  override fun loadCommentsForPost(postItem: PostItem) {

    placeholderApiService.getComments().observeOn(AndroidSchedulers.mainThread()).subscribeOn(
      Schedulers.io()
    ).subscribe { comments ->
      view.setComments(comments.filter { it.postId == postItem.id }.map { CommentItem(it.body) })
    }
  }
}