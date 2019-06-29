package com.fireblade.minisocialmedia.persistence.comment

data class NetworkComment(
  val postId: Int,
  val id: Int,
  val name: String,
  val email: String,
  val body: String
)