package com.fireblade.minisocialmedia.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fireblade.minisocialmedia.persistence.comment.CommentDAO
import com.fireblade.minisocialmedia.persistence.comment.DatabaseComment
import com.fireblade.minisocialmedia.persistence.post.DatabasePost
import com.fireblade.minisocialmedia.persistence.post.PostDAO
import com.fireblade.minisocialmedia.persistence.user.DatabaseUser
import com.fireblade.minisocialmedia.persistence.user.UserDAO

@Database(entities = [DatabaseUser::class, DatabasePost::class, DatabaseComment::class], version = 1, exportSchema = false)
abstract class SocialMediaDatabase : RoomDatabase() {

  abstract fun userDao(): UserDAO

  abstract fun postDao(): PostDAO

  abstract fun commentDao(): CommentDAO

  companion object {
    @Volatile
    private var INSTANCE: SocialMediaDatabase? = null

    fun getInstance(context: Context): SocialMediaDatabase {

      return INSTANCE ?: synchronized(this) {

        INSTANCE ?: createDatabase(context).also { instance ->
          INSTANCE = instance
        }
      }
    }

    private fun createDatabase(context: Context): SocialMediaDatabase {

      return Room.databaseBuilder(
        context.applicationContext,
        SocialMediaDatabase::class.java,
        "Social_Media_Database"
      )
        .build()
    }
  }

}