package com.fireblade.feed.business

import com.fireblade.core.post.PostItem

sealed class FeedSideEffect {
    data class NavigateToDetail(val item: PostItem) : FeedSideEffect()
}