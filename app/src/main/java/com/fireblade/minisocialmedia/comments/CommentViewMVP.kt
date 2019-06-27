package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.listview.PostItem

interface ICommentView {

  fun setComments(comments: List<CommentItem>)

  fun handleError(error: Throwable)
}

interface ICommentPresenter {

  fun fetchComments()

  fun loadCommentsForPost(postItem: PostItem)
}