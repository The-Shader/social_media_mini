package com.fireblade.minisocialmedia

import android.content.Context
import com.fireblade.feed.FeedPresenter
import com.fireblade.feed.IFeedPresenter
import com.fireblade.feed.IFeedView
import com.fireblade.persistence.SocialMediaDatabase
import com.fireblade.persistence.SocialMediaRepository
import com.fireblade.persistence.comment.CommentModule
import com.fireblade.persistence.network.IPlaceholderApiService
import com.fireblade.persistence.post.PostModule
import com.fireblade.persistence.user.UserModule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

class FeedPresenterTest {

  private val view = TestFeedView()

  private lateinit var socialMediaRepository: SocialMediaRepository

  private lateinit var placeholderApiService: IPlaceholderApiService

  private lateinit var feedPresenter: IFeedPresenter

  @Mock
  private lateinit var mockContext: Context

  @Before
  fun setup() {
    RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }

    RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }

    placeholderApiService = Retrofit.Builder()
      .baseUrl("http://jsonplaceholder.typicode.com")
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(OkHttpClient.Builder().build())
      .build().create(IPlaceholderApiService::class.java)

    val socialMediaDatabase = SocialMediaDatabase.getInstance(mockContext)

    socialMediaRepository = SocialMediaRepository(
      placeholderApiService,
      UserModule(socialMediaDatabase.userDao()),
      PostModule(socialMediaDatabase.postDao()),
      CommentModule(socialMediaDatabase.commentDao())
    )

    feedPresenter = FeedPresenter(view, socialMediaRepository)
  }

  @After
  fun shutDown() {
    RxJavaPlugins.reset()
    RxAndroidPlugins.reset()
  }


  @Test
  fun loadPosts_success() {

    feedPresenter.loadPostItems()

    Assert.assertEquals("Checking successful load", 1, view.setPostItemCounter)
    Assert.assertEquals("Checking for zero failure", 0, view.handleErrorCounter)
  }

  private val immediateScheduler = object : Scheduler() {
    override fun createWorker(): Worker {
      return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
    }
  }

  class TestFeedView : IFeedView {

    var setPostItemCounter = 0

    var handleErrorCounter = 0

    override fun handleError(error: Throwable) {
      handleErrorCounter++
    }

    override fun setPostItems(postItems: List<com.fireblade.core.post.PostItem>) {
      setPostItemCounter++
    }
  }
}