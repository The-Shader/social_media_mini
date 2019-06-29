package com.fireblade.minisocialmedia.persistence.user

data class User (
  val id: Int,
  val name: String,
  val username: String,
  val email: String,
  val avatarColor: Int
)