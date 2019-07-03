package com.fireblade.feed

import android.graphics.Color
import com.fireblade.core.schedulers.TestSchedulers
import com.fireblade.persistence.SocialMediaRepository
import com.fireblade.persistence.comment.Comment
import com.fireblade.persistence.post.Post
import com.fireblade.persistence.user.User
import org.junit.Before
import io.mockk.*
import io.reactivex.Flowable
import org.junit.After
import org.junit.Test


class FeedPresenterUnitTest {

    private lateinit var feedPresenter: IFeedPresenter

    private val mockFeedView: IFeedView = mockk()

    private val mockRepository: SocialMediaRepository = mockk()

    private val testSchedulers = TestSchedulers()

    @Before
    fun setup() {
        feedPresenter = FeedPresenter(view = mockFeedView, socialMediaRepository = mockRepository, schedulers = testSchedulers)
    }

    @After
    fun shutdown() {
        feedPresenter.destroy()
    }

    @Test
    fun testSimpleSuccess() {

        every { mockRepository.getUsers() } returns Flowable.just(listOf(User(1, "name", "username", "email", Color.RED)))
        every { mockRepository.getPosts() } returns Flowable.just(listOf(Post(0, 1, "title", "content")))
        every { mockRepository.getComments() } returns Flowable.just(listOf(Comment(2, 0, "name", "email", "content")))
        every { mockFeedView.handleError(any()) } just Runs
        every { mockFeedView.setPostItems(any()) } just Runs

        feedPresenter.loadPostItems()

        verify {
            mockFeedView.setPostItems(any())
        }
    }
}