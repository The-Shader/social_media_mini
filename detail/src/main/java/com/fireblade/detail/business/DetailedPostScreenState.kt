package com.fireblade.detail.business

import android.os.Parcelable
import com.fireblade.core.comment.CommentItem
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedPostScreenState(
    val detailState: State = State.Loading,
    val postItem: PostItem = PostItem.empty(),
    val comments: List<CommentItem> = listOf()
) : Parcelable