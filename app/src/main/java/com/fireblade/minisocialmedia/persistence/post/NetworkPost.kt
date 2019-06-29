package com.fireblade.minisocialmedia.persistence.post

data class NetworkPost(
  val userId: Int,
  val id: Int,
  val title: String,
  val body: String
)