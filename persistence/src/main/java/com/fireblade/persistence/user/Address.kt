package com.fireblade.persistence.user

data class Address(
  val street: String,
  val suite: String,
  val city: String,
  val zipcode: String,
  val geo: Geo
)