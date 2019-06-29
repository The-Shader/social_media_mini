package com.fireblade.persistence.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comments")
data class DatabaseComment(
  @PrimaryKey
  @ColumnInfo(name = "commentId")
  val id: Int,
  val postId: Int,
  val name: String,
  val email: String,
  val body: String
)