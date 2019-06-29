package com.fireblade.persistence.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class DatabasePost(
  @PrimaryKey
  @ColumnInfo(name = "postId")
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String
)