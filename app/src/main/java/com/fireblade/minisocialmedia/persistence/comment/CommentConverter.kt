package com.fireblade.minisocialmedia.persistence.comment

fun NetworkComment.toDatabaseComment(): DatabaseComment =

    DatabaseComment(
      id = id,
      postId = postId,
      name = name,
      email = email,
      body = body
    )

fun DatabaseComment.toModel(): Comment =
    Comment(
      commentId = id,
      postId = postId,
      name = name,
      email = email,
      body = body
    )