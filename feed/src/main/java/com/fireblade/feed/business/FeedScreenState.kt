package com.fireblade.feed.business

import android.os.Parcelable
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeedScreenState(
    val feedState: State = State.Loading,
    val posts: List<PostItem> = listOf()
) : Parcelable