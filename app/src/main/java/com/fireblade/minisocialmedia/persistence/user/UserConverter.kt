package com.fireblade.minisocialmedia.persistence.user

fun NetworkUser.toDatabaseUser(): DatabaseUser =

  DatabaseUser(
    id = id,
    name = name,
    username = username,
    email = email,
    avatarColor = AvatarColor.generateColor()
  )

fun DatabaseUser.toModel(): User =
  User(
    id = id,
    name = name,
    username = username,
    email = email,
    avatarColor = avatarColor
  )
