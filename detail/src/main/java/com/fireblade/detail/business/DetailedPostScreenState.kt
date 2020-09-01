package com.fireblade.detail.business

import android.os.Parcelable
import com.fireblade.core.comment.CommentItem
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import com.fireblade.persistence.comment.Comment
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedPostScreenState(
    val detailState: State = State.Loading,
    val postItem: PostItem = PostItem.empty(),
    val comments: List<Comment> = listOf()
) : Parcelable {
    @IgnoredOnParcel
    val commentItems = comments.map { it.toCommentItem() }
}

fun Comment.toCommentItem(): CommentItem =
    CommentItem(
        content = body
    )