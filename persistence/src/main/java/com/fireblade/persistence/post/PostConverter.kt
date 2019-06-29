package com.fireblade.persistence.post

fun NetworkPost.toDatabasePost(): DatabasePost =

  DatabasePost(
    id = id,
    userId = userId,
    title = title,
    body = body
  )

fun DatabasePost.toModel(): Post =
  Post(
    postId = id,
    userId = userId,
    title = title,
    body = body
  )