package com.fireblade.core.comment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentItem(val content: String) : Parcelable