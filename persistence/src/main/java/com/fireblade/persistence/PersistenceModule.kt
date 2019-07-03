package com.fireblade.persistence

import android.content.Context
import com.fireblade.core.schedulers.ApplicationSchedulersModule
import com.fireblade.core.schedulers.ISchedulers
import com.fireblade.persistence.network.IPlaceholderApiService
import com.fireblade.persistence.network.NetworkModule
import com.fireblade.persistence.comment.CommentDAO
import com.fireblade.persistence.comment.CommentModule
import com.fireblade.persistence.post.PostDAO
import com.fireblade.persistence.post.PostModule
import com.fireblade.persistence.user.UserDAO
import com.fireblade.persistence.user.UserModule
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, ApplicationSchedulersModule::class])
class PersistenceModule {

  @Provides
  fun provideDatabase(context: Context): SocialMediaDatabase =
    SocialMediaDatabase.getInstance(context)

  @Provides
  fun provideUserDAO(socialMediaDatabase: SocialMediaDatabase): UserDAO = socialMediaDatabase.userDao()

  @Provides
  fun provideUserModule(userDAO: UserDAO, schedulers: ISchedulers): UserModule = UserModule(userDAO, schedulers)

  @Provides
  fun providePostDAO(socialMediaDatabase: SocialMediaDatabase): PostDAO = socialMediaDatabase.postDao()

  @Provides
  fun providePostModule(postDAO: PostDAO, schedulers: ISchedulers): PostModule = PostModule(postDAO, schedulers)

  @Provides
  fun provideCommentDAO(socialMediaDatabase: SocialMediaDatabase): CommentDAO = socialMediaDatabase.commentDao()

  @Provides
  fun provideCommentModule(commentDAO: CommentDAO, schedulers: ISchedulers): CommentModule = CommentModule(commentDAO, schedulers)

  @Provides
  fun provideRepository(placeholderApiService: IPlaceholderApiService, userModule: UserModule, postModule: PostModule, commentModule: CommentModule): SocialMediaRepository =
    SocialMediaRepository(
      placeholderApiService,
      userModule,
      postModule,
      commentModule
    )
}