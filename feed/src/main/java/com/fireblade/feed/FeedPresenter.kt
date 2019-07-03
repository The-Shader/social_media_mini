package com.fireblade.feed

import com.fireblade.core.post.PostItem
import com.fireblade.core.schedulers.ISchedulers
import com.fireblade.persistence.user.AvatarColor
import com.fireblade.persistence.SocialMediaRepository
import com.fireblade.persistence.comment.Comment
import com.fireblade.persistence.post.Post
import com.fireblade.persistence.user.User
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import javax.inject.Inject

class FeedPresenter @Inject constructor(
  private val view: IFeedView,
  private val socialMediaRepository: SocialMediaRepository,
  private val schedulers: ISchedulers
) : IFeedPresenter {

  private val subscribers = CompositeDisposable()

  override fun loadPostItems() {

    val users = socialMediaRepository.getUsers().observeOn(schedulers.androidMain()).subscribeOn(schedulers.io())
    val posts = socialMediaRepository.getPosts().observeOn(schedulers.androidMain()).subscribeOn(schedulers.io())
    val comments = socialMediaRepository.getComments().observeOn(schedulers.androidMain()).subscribeOn(schedulers.io())

    Flowable.zip(users, posts, comments,
      Function3<List<User>, List<Post>, List<Comment>, List<PostItem>> { userList, postList, commentList ->
        return@Function3 createPostItems(userList, postList, commentList)
      }
    )
      .observeOn(schedulers.androidMain())
      .subscribeOn(schedulers.io())
      .subscribe(view::setPostItems, view::handleError)
      .apply {
        subscribers.add(this)
      }
  }

  override fun destroy() {
    subscribers.clear()
  }

  private fun createPostItems(users: List<User>, posts: List<Post>, comments: List<Comment>) : List<PostItem> {

    return posts.map { post ->

      val user = users.find { user -> user.id == post.userId }
      PostItem(
        post.postId,
        post.title,
        post.body,
        user?.name ?: "Unknown NetworkUser",
        comments.count { comment -> comment.postId == post.postId },
        user?.avatarColor ?: AvatarColor.generateColor()
      )
    }
  }
}