package com.fireblade.minisocialmedia.persistence.comment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface CommentDAO {

  @Query("select * from Comments")
  fun getAllComments(): Flowable<List<DatabaseComment>>

  @Query("select * from Comments")
  fun getAllCommentsMaybe(): Maybe<List<DatabaseComment>>

  @Query("select * from Comments where postId = :postId")
  fun getCommentsByPost(postId: Int): Flowable<List<DatabaseComment>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertComments(post: DatabaseComment): Completable

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertComments(posts: List<DatabaseComment>): Completable

  @Query("delete from Comments")
  fun deleteComments()
}