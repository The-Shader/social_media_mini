package com.fireblade.minisocialmedia.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fireblade.minisocialmedia.R
import com.fireblade.minisocialmedia.listview.ListViewFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, IHomeView {

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    supportFragmentManager.beginTransaction().apply {
      replace(R.id.post_layout, ListViewFragment())
    }.commitAllowingStateLoss()
  }
}
