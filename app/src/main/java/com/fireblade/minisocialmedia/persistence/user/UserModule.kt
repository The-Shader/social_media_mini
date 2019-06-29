package com.fireblade.minisocialmedia.persistence.user

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserModule @Inject constructor(private val userDAO: UserDAO) {

  fun insertUsers(users: List<DatabaseUser>) = userDAO.insertUsers(users).subscribeOn(Schedulers.io())

  fun getUsersMaybe() = userDAO.getAllUsersMaybe().subscribeOn(Schedulers.io())

  fun getUsers() = userDAO.getAllUsers().subscribeOn(Schedulers.io())
}