package com.fireblade.persistence

import com.fireblade.core.post.PostItem
import com.fireblade.persistence.network.IPlaceholderApiService
import com.fireblade.persistence.comment.*
import com.fireblade.persistence.post.*
import com.fireblade.persistence.user.*
import io.reactivex.Flowable
import javax.inject.Inject

class SocialMediaRepository @Inject constructor(
  private val placeholderApiService: IPlaceholderApiService,
  private val userModule: UserModule,
  private val postModule: PostModule,
  private val commentModule: CommentModule
) : ISocialMediaRepository {

  fun getUsers(): Flowable<List<User>> {

    return Flowable.concat(
      syncUsers(),
      userModule.getUsers().map {
      it.map(DatabaseUser::toModel)
    })
  }

  fun getPosts(): Flowable<List<Post>> {

    return Flowable.concat(
      syncPosts(),
      postModule.getPosts().map {
        it.map(DatabasePost::toModel)
      }
    )
      .distinctUntilChanged()
  }

  fun getComments(): Flowable<List<Comment>> {

    return Flowable.concat(
      syncComments(),
      commentModule.getComments().map {
        it.map(DatabaseComment::toModel)
      }
    )
      .distinctUntilChanged()
  }

  override fun getCommentsByPost(postId: Int): Flowable<List<Comment>> {

    return commentModule.getCommentsByPost(postId).map {
        it.map(DatabaseComment::toModel)
      }
  }

  override fun getPostItems(): Flowable<List<PostItem>> {
    return Flowable.zip(getUsers(), getPosts(), getComments(), { userList, postList, commentList ->
      return@zip createPostItems(userList, postList, commentList)
    })
  }

  private fun syncUsers(): Flowable<List<User>> {

    return Flowable.concat(
      userModule.getUsersMaybe().map {
        it.map(DatabaseUser::toModel)
      }.toFlowable(),
      placeholderApiService.getUsers()
        .map {
          it.map(NetworkUser::toDatabaseUser) }
        .flatMapCompletable(userModule::insertUsers)
        .onErrorComplete()
        .toFlowable()
    ).distinctUntilChanged()
  }

  private fun syncPosts(): Flowable<List<Post>> {

    return Flowable.concat(
      postModule.getPostsMaybe().map {
        it.map(DatabasePost::toModel)
      }.toFlowable(),
      placeholderApiService.getPosts().map { it.map(NetworkPost::toDatabasePost) }
        .flatMapCompletable(postModule::insertPosts)
        .onErrorComplete()
        .toFlowable()
    )
      .distinctUntilChanged()
  }

  private fun syncComments(): Flowable<List<Comment>> {

    return Flowable.concat(
      commentModule.getCommentsMaybe().map {
        it.map(DatabaseComment::toModel)
      }.toFlowable(),
      placeholderApiService.getComments().map { it.map(NetworkComment::toDatabaseComment) }
        .flatMapCompletable(commentModule::insertComments)
        .onErrorComplete()
        .toFlowable()
    )
      .distinctUntilChanged()
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