package com.fireblade.minisocialmedia

import com.fireblade.minisocialmedia.listview.IListView
import com.fireblade.minisocialmedia.listview.ListViewPresenter
import com.fireblade.minisocialmedia.listview.PostItem
import com.fireblade.minisocialmedia.network.IPlaceholderApiService
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

class ListViewPresenterTest {

  private val view = TestListView()

  private lateinit var placeholderApiService: IPlaceholderApiService

  private lateinit var listViewPresenter: ListViewPresenter

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

    listViewPresenter = ListViewPresenter(view, placeholderApiService)
  }

  @After
  fun shutDown() {
    RxJavaPlugins.reset()
    RxAndroidPlugins.reset()
  }


  @Test
  fun loadPosts_success() {

    listViewPresenter.loadPostItems()

    Assert.assertEquals("Checking successful load", 1, view.setPostItemCounter)
    Assert.assertEquals("Checking for zero failure", 0, view.handleErrorCounter)
  }

  private val immediateScheduler = object : Scheduler() {
    override fun createWorker(): Worker {
      return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
    }
  }

  class TestListView : IListView {

    var setPostItemCounter = 0

    var handleErrorCounter = 0

    override fun handleError(error: Throwable) {
      handleErrorCounter++
    }

    override fun setPostItems(postItems: List<PostItem>) {
      setPostItemCounter++
    }
  }
}