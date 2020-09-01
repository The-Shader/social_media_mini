package com.fireblade.detail.business

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.babylon.orbit2.ContainerHost
import com.babylon.orbit2.reduce
import com.babylon.orbit2.rxjava2.transformRx2Observable
import com.babylon.orbit2.viewmodel.container
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import com.fireblade.persistence.ISocialMediaRepository
import javax.inject.Inject

class DetailedPostViewModel @Inject constructor(
    private val socialMediaRepository: ISocialMediaRepository,
    savedStateHandle: SavedStateHandle,
) : ContainerHost<DetailedPostScreenState, Nothing>, ViewModel() {

    private val postItem: PostItem = savedStateHandle.get<PostItem>(PostItem.TAG) ?: PostItem.empty()

    override val container =
        container<DetailedPostScreenState, Nothing>(DetailedPostScreenState(postItem = postItem)) {
            if (it.comments.isEmpty()) {
                loadComments()
            }
        }

    private fun loadComments() = orbit {
        transformRx2Observable {
            socialMediaRepository.getCommentsByPost(postItem.id).toObservable()
        }
            .reduce {
                if (postItem == PostItem.empty()) {
                    state.copy(
                        detailState = State.Error
                    )
                } else {
                    state.copy(
                        detailState = State.Ready,
                        postItem = postItem,
                        comments = event
                    )
                }
            }
    }

    class Factory(
        owner: SavedStateRegistryOwner,
        private val repository: ISocialMediaRepository,
        defaultArgs: Bundle? = null
    ) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel> create(
            key: String, modelClass: Class<T>, handle: SavedStateHandle
        ): T {
            return DetailedPostViewModel(
                repository, handle
            ) as T
        }
    }
}
