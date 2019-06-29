package com.fireblade.minisocialmedia.persistence.comment

data class Comment (
  val commentId: Int,
  val postId: Int,
  val name: String,
  val email: String,
  val body: String
)