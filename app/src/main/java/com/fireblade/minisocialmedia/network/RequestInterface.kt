package com.fireblade.minisocialmedia.network

import com.fireblade.minisocialmedia.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface RequestInterface {

  @GET("/posts")
  fun getPosts() : Observable<List<Post>>
}