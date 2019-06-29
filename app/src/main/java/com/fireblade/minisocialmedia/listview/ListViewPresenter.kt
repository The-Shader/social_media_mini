package com.fireblade.minisocialmedia.listview

import android.annotation.SuppressLint
import com.fireblade.minisocialmedia.persistence.user.AvatarColor
import com.fireblade.minisocialmedia.persistence.SocialMediaRepository
import com.fireblade.minisocialmedia.persistence.comment.Comment
import com.fireblade.minisocialmedia.persistence.post.Post
import com.fireblade.minisocialmedia.persistence.user.User
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewPresenter @Inject constructor(
  private val view: IListView,
  private val socialMediaRepository: SocialMediaRepository
) : IListPresenter {

  private val subscribers = CompositeDisposable()

  @SuppressLint("CheckResult")
  override fun loadPostItems() {

    val users = socialMediaRepository.getUsers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    val posts = socialMediaRepository.getPosts().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    val comments = socialMediaRepository.getComments().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    Flowable.zip(users, posts, comments,
      Function3<List<User>, List<Post>, List<Comment>, List<PostItem>> { userList, postList, commentList ->
        return@Function3 createPostItems(userList, postList, commentList)
      }
    )
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
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