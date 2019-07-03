package com.fireblade.persistence.post

import com.fireblade.core.schedulers.ISchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class PostModule @Inject constructor(private val postDAO: PostDAO, private val schedulers: ISchedulers) {

  fun insertPosts(posts: List<DatabasePost>): Completable = postDAO.insertPosts(posts).subscribeOn(schedulers.io())

  fun getPostsMaybe(): Maybe<List<DatabasePost>> = postDAO.getAllPostMaybe().subscribeOn(schedulers.io())

  fun getPosts(): Flowable<List<DatabasePost>> = postDAO.getAllPost().subscribeOn(schedulers.io())
}