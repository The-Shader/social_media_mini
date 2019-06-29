package com.fireblade.minisocialmedia.persistence

import com.fireblade.minisocialmedia.persistence.network.IPlaceholderApiService
import com.fireblade.minisocialmedia.persistence.comment.*
import com.fireblade.minisocialmedia.persistence.post.*
import com.fireblade.minisocialmedia.persistence.user.*
import io.reactivex.Flowable
import javax.inject.Inject

class SocialMediaRepository @Inject constructor(
  private val placeholderApiService: IPlaceholderApiService,
  private val userModule: UserModule,
  private val postModule: PostModule,
  private val commentModule: CommentModule
) {

  fun syncUsers(): Flowable<List<User>> {

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

  fun getUsers(): Flowable<List<User>> {

    return Flowable.concat(
      syncUsers(),
      userModule.getUsers().map {
      it.map(DatabaseUser::toModel)
    })
  }

  fun syncPosts(): Flowable<List<Post>> {

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

  fun getPosts(): Flowable<List<Post>> {

    return Flowable.concat(
      syncPosts(),
      postModule.getPosts().map {
        it.map(DatabasePost::toModel)
      }
    )
      .distinctUntilChanged()
  }

  fun syncComments(): Flowable<List<Comment>> {

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

  fun getComments(): Flowable<List<Comment>> {

    return Flowable.concat(
      syncComments(),
      commentModule.getComments().map {
        it.map(DatabaseComment::toModel)
      }
    )
      .distinctUntilChanged()
  }

  fun getCommentsByPost(postId: Int): Flowable<List<Comment>> {

    return commentModule.getCommentsByPost(postId).map {
        it.map(DatabaseComment::toModel)
      }
  }
}