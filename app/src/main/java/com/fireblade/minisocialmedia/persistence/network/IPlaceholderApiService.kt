package com.fireblade.minisocialmedia.persistence.network

import com.fireblade.minisocialmedia.persistence.comment.NetworkComment
import com.fireblade.minisocialmedia.persistence.post.NetworkPost
import com.fireblade.minisocialmedia.persistence.user.NetworkUser
import io.reactivex.Single
import retrofit2.http.GET

interface IPlaceholderApiService {

  @GET("/posts")
  fun getPosts() : Single<List<NetworkPost>>

  @GET("/users")
  fun getUsers() : Single<List<NetworkUser>>

  @GET("/comments")
  fun getComments() : Single<List<NetworkComment>>

  companion object Factory {

    fun create(placeholderApiSdk: PlaceholderApiSdk) : IPlaceholderApiService {
      return placeholderApiSdk.getClient().create(IPlaceholderApiService::class.java)
    }
  }
}