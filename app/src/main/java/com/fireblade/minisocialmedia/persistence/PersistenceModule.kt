package com.fireblade.minisocialmedia.persistence

import android.content.Context
import com.fireblade.minisocialmedia.persistence.network.IPlaceholderApiService
import com.fireblade.minisocialmedia.persistence.network.NetworkModule
import com.fireblade.minisocialmedia.persistence.comment.CommentDAO
import com.fireblade.minisocialmedia.persistence.comment.CommentModule
import com.fireblade.minisocialmedia.persistence.post.PostDAO
import com.fireblade.minisocialmedia.persistence.post.PostModule
import com.fireblade.minisocialmedia.persistence.user.UserDAO
import com.fireblade.minisocialmedia.persistence.user.UserModule
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class PersistenceModule {

  @Provides
  fun provideDatabase(context: Context): SocialMediaDatabase =
    SocialMediaDatabase.getInstance(context)

  @Provides
  fun provideUserDAO(socialMediaDatabase: SocialMediaDatabase): UserDAO = socialMediaDatabase.userDao()

  @Provides
  fun provideUserModule(userDAO: UserDAO): UserModule = UserModule(userDAO)

  @Provides
  fun providePostDAO(socialMediaDatabase: SocialMediaDatabase): PostDAO = socialMediaDatabase.postDao()

  @Provides
  fun providePostModule(postDAO: PostDAO): PostModule = PostModule(postDAO)

  @Provides
  fun provideCommentDAO(socialMediaDatabase: SocialMediaDatabase): CommentDAO = socialMediaDatabase.commentDao()

  @Provides
  fun provideCommentModule(commentDAO: CommentDAO): CommentModule = CommentModule(commentDAO)

  @Provides
  fun provideRepository(placeholderApiService: IPlaceholderApiService, userModule: UserModule, postModule: PostModule, commentModule: CommentModule): SocialMediaRepository =
    SocialMediaRepository(
      placeholderApiService,
      userModule,
      postModule,
      commentModule
    )
}