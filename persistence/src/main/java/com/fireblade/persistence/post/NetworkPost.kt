package com.fireblade.persistence.post

data class NetworkPost(
  val userId: Int,
  val id: Int,
  val title: String,
  val body: String
)