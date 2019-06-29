package com.fireblade.persistence.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface PostDAO {

  @Query("select * from Posts")
  fun getAllPost(): Flowable<List<DatabasePost>>

  @Query("select * from Posts")
  fun getAllPostMaybe(): Maybe<List<DatabasePost>>

  @Query("select * from Posts where postId = :id")
  fun getPostById(id: Int): Flowable<DatabasePost>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPost(post: DatabasePost): Completable

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPosts(posts: List<DatabasePost>): Completable

  @Query("delete from Posts")
  fun deletePosts()
}