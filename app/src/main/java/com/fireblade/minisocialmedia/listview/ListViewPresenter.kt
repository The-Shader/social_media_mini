package com.fireblade.minisocialmedia.listview

import android.annotation.SuppressLint
import com.fireblade.minisocialmedia.model.Comment
import com.fireblade.minisocialmedia.model.Post
import com.fireblade.minisocialmedia.model.User
import com.fireblade.minisocialmedia.network.IRequestService
import io.reactivex.Observable
import io.reactivex.functions.Function3
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewPresenter @Inject constructor(
  private val view: IListView,
  private val requestService: IRequestService
) : IListPresenter {

  @SuppressLint("CheckResult")
  override fun loadPostItems() {
    val observablePosts = requestService.getPosts().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    val observableUsers = requestService.getUsers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    val observableComments = requestService.getComments().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    Observable.zip(observableUsers, observablePosts, observableComments,
      Function3<List<User>, List<Post>, List<Comment>, List<PostItem>> { userList, postList, commentList ->
        return@Function3 createPostItem(userList, postList, commentList)
      })
      .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(view::setPostItems, view::handleError)
  }

  private fun createPostItem(userList: List<User>, postList: List<Post>, commentList: List<Comment>) : List<PostItem> {

    val postItems = mutableListOf<PostItem>()

    postList.map { post ->
      postItems.add(
        PostItem(post.title,
          post.body,
          userList.find { user ->
            user.id == post.userId
          }?.name ?: "Unknown User",
          commentList.count { comment ->
            comment.postId == post.id
          })
      )
    }

    return postItems
  }
}