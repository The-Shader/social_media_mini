package com.fireblade.minisocialmedia.details

import com.fireblade.minisocialmedia.listview.PostItem

interface IDetailedPostView {

  fun setComments(comments: List<CommentItem>)

  fun handleError(error: Throwable)
}

interface IDetailedPostPresenter {

  fun loadCommentsForPost(postItem: PostItem)

  fun destroy()
}