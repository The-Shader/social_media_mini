package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.listview.PostItem
import com.fireblade.minisocialmedia.persistence.SocialMediaRepository
import com.fireblade.minisocialmedia.persistence.comment.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentPresenter @Inject constructor(
  private val view: ICommentView,
  private val socialMediaRepository: SocialMediaRepository,
  private val eventHandler: ICommentEvents
) : ICommentPresenter {

  private val subscribers = CompositeDisposable()

  override fun fetchComments() {
    eventHandler.fetchComments()
  }

  override fun loadCommentsForPost(postItem: PostItem) {

    socialMediaRepository.getCommentsByPost(postItem.id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map {
      it.map(Comment::toViewItem)
    }
      .subscribe(view::setComments)
      .apply { subscribers.add(this) }
  }

  override fun destroy() {
    subscribers.clear()
  }
}