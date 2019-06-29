package com.fireblade.minisocialmedia.network

import com.fireblade.minisocialmedia.model.Comment
import com.fireblade.minisocialmedia.model.Post
import com.fireblade.minisocialmedia.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface IPlaceholderApiService {

  @GET("/posts")
  fun getPosts() : Observable<List<Post>>

  @GET("/users")
  fun getUsers() : Observable<List<User>>

  @GET("/comments")
  fun getComments() : Observable<List<Comment>>

  companion object Factory {

    fun create(placeholderApiSdk: PlaceholderApiSdk) : IPlaceholderApiService {
      return placeholderApiSdk.getClient().create(IPlaceholderApiService::class.java)
    }
  }
}