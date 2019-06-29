package com.fireblade.minisocialmedia.details

import com.fireblade.minisocialmedia.persistence.comment.Comment

data class CommentItem(val content: String)

fun Comment.toViewItem(): CommentItem =
  CommentItem(
    content = body
  )