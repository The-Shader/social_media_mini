package com.fireblade.persistence.post

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostModule @Inject constructor(private val postDAO: PostDAO) {

  fun insertPosts(posts: List<DatabasePost>) = postDAO.insertPosts(posts).subscribeOn(Schedulers.io())

  fun getPostsMaybe() = postDAO.getAllPostMaybe().subscribeOn(Schedulers.io())

  fun getPosts() = postDAO.getAllPost().subscribeOn(Schedulers.io())
}