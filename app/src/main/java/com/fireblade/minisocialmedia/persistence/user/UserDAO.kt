package com.fireblade.minisocialmedia.persistence.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface UserDAO {

  @Query("select * from Users")
  fun getAllUsers(): Flowable<List<DatabaseUser>>

  @Query("select * from Users")
  fun getAllUsersMaybe(): Maybe<List<DatabaseUser>>

  @Query("select * from Users where userId = :id")
  fun getUserById(id: Int): Flowable<DatabaseUser>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertUser(user: DatabaseUser): Completable

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertUsers(users: List<DatabaseUser>): Completable

  @Query("delete from Users")
  fun deleteUsers()
}