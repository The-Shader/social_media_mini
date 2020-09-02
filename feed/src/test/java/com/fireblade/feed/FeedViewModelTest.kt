package com.fireblade.feed

import com.appmattus.kotlinfixture.kotlinFixture
import com.babylon.orbit2.assert
import com.babylon.orbit2.test
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import com.fireblade.feed.business.FeedScreenState
import com.fireblade.feed.business.FeedViewModel
import com.fireblade.persistence.ISocialMediaRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.jupiter.api.Test

class FeedViewModelTest {

    private val socialMediaRepository = mock<ISocialMediaRepository>()
    private val fixture = kotlinFixture()

    @Test
    fun `load posts from repository`() {
        val postItems = fixture<List<PostItem>>()

        // Mock the repository
        whenever(socialMediaRepository.getPostItems()).thenReturn(Flowable.just(postItems))

        val feedViewModel = FeedViewModel(socialMediaRepository).test(
            initialState = FeedScreenState(),
            runOnCreate = true
        )

        feedViewModel.assert {
            states(
                { FeedScreenState(feedState = State.Ready, posts = postItems) }
            )
        }
    }

    @Test
    fun `do not load posts from repository if already loaded`() {
        val postItems = fixture<List<PostItem>>()

        // Mock the repository
        whenever(socialMediaRepository.getPostItems()).thenReturn(Flowable.just(postItems))

        val feedViewModel = FeedViewModel(socialMediaRepository).test(
            initialState = FeedScreenState(
                posts = postItems
            ),
            runOnCreate = true
        )

        feedViewModel.assert {}
    }
}