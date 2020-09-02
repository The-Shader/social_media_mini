package com.fireblade.detail

import androidx.lifecycle.SavedStateHandle
import com.appmattus.kotlinfixture.kotlinFixture
import com.babylon.orbit2.assert
import com.babylon.orbit2.test
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import com.fireblade.detail.business.DetailedPostScreenState
import com.fireblade.detail.business.DetailedPostViewModel
import com.fireblade.persistence.ISocialMediaRepository
import com.fireblade.persistence.comment.Comment
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.jupiter.api.Test


class DetailedPostViewModelTest {

    private val socialMediaRepository = mock<ISocialMediaRepository>()
    private val fixture = kotlinFixture()

    @Test
    fun `load post details from repository`() {
        val postItem = fixture<PostItem>()
        val commentItems = fixture<List<Comment>> {
            property(Comment::postId) { postItem.id }
        }
        val savedStateHandle = fixture<SavedStateHandle>()
            .apply { this.set(PostItem.TAG, postItem) }

        // Mock the repository
        whenever(socialMediaRepository.getCommentsByPost(postItem.id)).thenReturn(Flowable.just(commentItems))

        val detailedPostViewModel = DetailedPostViewModel(socialMediaRepository, savedStateHandle).test(
            initialState = DetailedPostScreenState(),
            runOnCreate = true
        )
        detailedPostViewModel.assert {
            states(
                { DetailedPostScreenState(detailState = State.Ready, postItem = postItem, comments = commentItems)}
            )
        }
    }

    @Test
    fun `do not load post details from repository if already loaded`() {
        val postItem = fixture<PostItem>()
        val commentItems = fixture<List<Comment>> {
            property(Comment::postId) { postItem.id }
        }
        val savedStateHandle = fixture<SavedStateHandle>()
            .apply { this.set(PostItem.TAG, postItem) }

        // Mock the repository
        whenever(socialMediaRepository.getCommentsByPost(postItem.id)).thenReturn(Flowable.just(commentItems))

        val detailedPostViewModel = DetailedPostViewModel(socialMediaRepository, savedStateHandle).test(
            initialState = DetailedPostScreenState(
                comments = commentItems
            ),
            runOnCreate = true
        )
        detailedPostViewModel.assert {}
    }

    @Test
    fun `error on loading empty post details`() {
        val postItem = PostItem.empty()
        val savedStateHandle = fixture<SavedStateHandle>()

        // Mock the repository
        whenever(socialMediaRepository.getCommentsByPost(postItem.id)).thenReturn(
            Flowable.just(
                listOf()
            )
        )

        val detailedPostViewModel =
            DetailedPostViewModel(socialMediaRepository, savedStateHandle).test(
                initialState = DetailedPostScreenState(),
                runOnCreate = true
            )
        detailedPostViewModel.assert {
            states(
                { DetailedPostScreenState(detailState = State.Error) }
            )
        }
    }
}