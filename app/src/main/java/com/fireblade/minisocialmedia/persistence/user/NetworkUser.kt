package com.fireblade.minisocialmedia.persistence.user

data class NetworkUser(
  val id: Int,
  val name: String,
  val username: String,
  val email: String,
  val address: Address,
  val phone: String,
  val website: String,
  val company: Company
)