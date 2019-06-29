package com.fireblade.detail

import com.fireblade.core.comment.CommentItem
import com.fireblade.core.post.PostItem
import com.fireblade.persistence.SocialMediaRepository
import com.fireblade.persistence.comment.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

fun Comment.toViewItem(): CommentItem =
  CommentItem(
    content = body
  )

class DetailedPostPresenter@Inject constructor(
  private val view: IDetailedPostView,
  private val socialMediaRepository: SocialMediaRepository
) : IDetailedPostPresenter {

  private val subscribers = CompositeDisposable()

  override fun destroy() {
    subscribers.clear()
  }

  override fun loadCommentsForPost(postItem: PostItem) {
    socialMediaRepository.getCommentsByPost(postItem.id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
      Schedulers.io()).map {
      it.map(Comment::toViewItem)
    }
      .subscribe(view::setComments, view::handleError)
      .apply {
        subscribers.add(this)
      }
  }
}