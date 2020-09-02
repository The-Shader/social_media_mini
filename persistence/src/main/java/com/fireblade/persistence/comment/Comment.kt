package com.fireblade.persistence.comment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment (
  val commentId: Int,
  val postId: Int,
  val name: String,
  val email: String,
  val body: String
) : Parcelable