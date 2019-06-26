package com.fireblade.minisocialmedia.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fireblade.minisocialmedia.R
import com.fireblade.minisocialmedia.listview.PostItem
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

    val post: PostItem = intent.getParcelableExtra("POST")
    setPostItem(post)
  }

  private fun setPostItem(postItem: PostItem) {
    author_textview.text = postItem.author
    body_textview.text = postItem.body
    title_textview.text = postItem.title
    num_of_comments_textview.text = postItem.numOfComments.toString()
  }
}