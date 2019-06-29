package com.fireblade.persistence.comment

data class Comment (
  val commentId: Int,
  val postId: Int,
  val name: String,
  val email: String,
  val body: String
)