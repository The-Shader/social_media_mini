package com.fireblade.persistence.user

import com.fireblade.core.schedulers.ISchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class UserModule @Inject constructor(private val userDAO: UserDAO, private val schedulers: ISchedulers) {

  fun insertUsers(users: List<DatabaseUser>): Completable = userDAO.insertUsers(users).subscribeOn(schedulers.io())

  fun getUsersMaybe(): Maybe<List<DatabaseUser>> = userDAO.getAllUsersMaybe().subscribeOn(schedulers.io())

  fun getUsers(): Flowable<List<DatabaseUser>> = userDAO.getAllUsers().subscribeOn(schedulers.io())
}