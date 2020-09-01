package com.fireblade.minisocialmedia.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fireblade.feed.ui.FeedFragment
import com.fireblade.minisocialmedia.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasAndroidInjector {

  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): DispatchingAndroidInjector<Any> = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    supportFragmentManager.beginTransaction().apply {
      replace(R.id.post_layout, FeedFragment())
    }.commitAllowingStateLoss()
  }
}
