package com.fireblade.persistence.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class DatabaseUser(
  @PrimaryKey
  @ColumnInfo(name = "userId")
  val id: Int,
  val name: String,
  val username: String,
  val email: String,
  val avatarColor: Int
)