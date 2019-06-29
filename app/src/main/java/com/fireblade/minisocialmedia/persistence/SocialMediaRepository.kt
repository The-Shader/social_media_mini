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

    return placeholderApiService.getUsers()
      .map { it.map(NetworkUser::toDatabaseUser) }
      .flatMapCompletable(userModule::insertUsers)
      .onErrorComplete()
      .toFlowable()
  }

  fun getUsers(): Flowable<List<User>> {

    return Flowable.concat(
      userModule.getUsers().map {
      it.map(DatabaseUser::toModel)
    }, syncUsers())
      .distinctUntilChanged()
  }

  fun syncPosts(): Flowable<List<Post>> {

    return placeholderApiService.getPosts().map { it.map(NetworkPost::toDatabasePost) }
      .flatMapCompletable(postModule::insertPosts)
      .onErrorComplete()
      .toFlowable()
  }

  fun getPosts(): Flowable<List<Post>> {

    return Flowable.concat(
      postModule.getPosts().map {
        it.map(DatabasePost::toModel)
      }, syncPosts()
    )
      .distinctUntilChanged()
  }

  fun syncComments(): Flowable<List<Comment>> {

    return placeholderApiService.getComments().map { it.map(NetworkComment::toDatabaseComment) }
      .flatMapCompletable(commentModule::insertComments)
      .onErrorComplete()
      .toFlowable()
  }

  fun getComments(): Flowable<List<Comment>> {

    return Flowable.concat(
      commentModule.getComments().map {
        it.map(DatabaseComment::toModel)
      }, syncComments()
    )
      .distinctUntilChanged()
  }

  fun getCommentsByPost(postId: Int): Flowable<List<Comment>> {

    return commentModule.getCommentsByPost(postId).map {
        it.map(DatabaseComment::toModel)
      }
  }
}