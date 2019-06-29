package com.fireblade.detail

import com.fireblade.core.comment.CommentItem
import com.fireblade.core.post.PostItem

interface IDetailedPostView {

  fun setComments(comments: List<CommentItem>)

  fun handleError(error: Throwable)
}

interface IDetailedPostPresenter {

  fun loadCommentsForPost(postItem: PostItem)

  fun destroy()
}