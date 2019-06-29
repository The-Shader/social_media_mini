package com.fireblade.feed

import com.fireblade.core.post.PostItem

interface IFeedView {

  fun setPostItems(postItems: List<PostItem>)

  fun handleError(error: Throwable)
}

interface IFeedPresenter {

  fun loadPostItems()

  fun destroy()
}