package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.persistence.comment.Comment

data class CommentItem(val content: String)

fun Comment.toViewItem(): CommentItem =
  CommentItem(
    content = body
  )