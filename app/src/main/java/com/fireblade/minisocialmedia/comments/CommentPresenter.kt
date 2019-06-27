package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.listview.PostItem
import com.fireblade.minisocialmedia.network.IRequestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentPresenter @Inject constructor(
  private val view: ICommentView,
  private val requestService: IRequestService,
  private val eventHandler: ICommentEvents
) : ICommentPresenter {

  override fun fetchComments() {
    eventHandler.fetchComments()
  }

  override fun loadCommentsForPost(postItem: PostItem) {

    requestService.getComments().observeOn(AndroidSchedulers.mainThread()).subscribeOn(
      Schedulers.io()
    ).subscribe { comments ->
      view.setComments(comments.filter { it.postId == postItem.id }.map { CommentItem(it.body) })
    }
  }
}