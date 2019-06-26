package com.fireblade.minisocialmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    supportFragmentManager.beginTransaction().apply {
      replace(R.id.post_layout, HomeFragment())
    }.commitAllowingStateLoss()
  }
}
