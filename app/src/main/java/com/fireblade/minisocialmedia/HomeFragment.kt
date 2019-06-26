package com.fireblade.minisocialmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

  private val postAdapter: PostAdapter by lazy { PostAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidSupportInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    list_of_posts.adapter = postAdapter
    postAdapter.items = createPosts()
  }

  fun createPosts() : List<PostItem> {

    val post1 = PostItem("Working here", "It is true awesome", "Alain", 3)
    val post2 = PostItem("Paris", "Paris is so hot right now", "Ian", 152)
    val post3 = PostItem("Today's World", "The AI is going to eat the world", "Rebeca", 41)
    val post4 = PostItem("Cryptocurrencies", "It doesn't matter whether it's good or bad: it's progression", "Ana", 24)
    val post5 = PostItem("Too late", "Go to the Gym!", "Jeff", 2)

    return listOf(post1, post2, post3, post4, post5)
  }
}