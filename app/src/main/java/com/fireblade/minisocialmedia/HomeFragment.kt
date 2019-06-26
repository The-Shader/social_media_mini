package com.fireblade.minisocialmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.fireblade.minisocialmedia.model.Post
import com.fireblade.minisocialmedia.model.User
import com.fireblade.minisocialmedia.network.RequestClient
import com.fireblade.minisocialmedia.network.RequestInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

  private val postAdapter: PostAdapter by lazy { PostAdapter() }

  private lateinit var requestInterface: RequestInterface

  private val subscribers = CompositeDisposable()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    list_of_posts.adapter = postAdapter

    list_of_posts.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply { setDrawable(resources.getDrawable(R.drawable.divider_item_decoration, null)) })


    requestInterface = RequestClient.getClient().create(RequestInterface::class.java)

    val observablePosts = requestInterface.getPosts().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    val observableUsers = requestInterface.getUsers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    Observable.zip(observableUsers, observablePosts, BiFunction<List<User>, List<Post>, List<PostItem>> { userList, postList -> return@BiFunction createPostItem(userList, postList)})
      .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::handleResponse, this::handleError).apply {
        subscribers.add(this)
      }

    //postAdapter.items = createPosts()
  }

  private fun createPosts() : List<PostItem> {

    val post1 = PostItem("Working here", "It is true awesome", "Alain", 3)
    val post2 = PostItem("Paris", "Paris is so hot right now", "Ian", 152)
    val post3 = PostItem("Today's World", "The AI is going to eat the world", "Rebeca", 41)
    val post4 = PostItem("Cryptocurrencies", "It doesn't matter whether it's good or bad: it's progression", "Ana", 24)
    val post5 = PostItem("Too late", "Go to the Gym!", "Jeff", 2)

    return listOf(post1, post2, post3, post4, post5)
  }

  private fun handleResponse(postItems: List<PostItem>) {
    postAdapter.items = postItems
  }

  private fun handleError(error: Throwable) {
    Toast.makeText(context, "An error occurred while loading data from the network. Check your internet connection.", Toast.LENGTH_SHORT).show()
  }

  private fun createPostItem(userList: List<User>, postList: List<Post>) : List<PostItem> {

    val postItems = mutableListOf<PostItem>()

    postList.map { post ->
      postItems.add(PostItem(post.title, post.body, userList.find { user -> user.id == post.userId }?.name ?: "Unknown User"))
    }

    return postItems
  }
}