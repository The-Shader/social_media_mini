package com.fireblade.feed.business

import androidx.lifecycle.ViewModel
import com.babylon.orbit2.*
import com.babylon.orbit2.rxjava2.transformRx2Observable
import com.babylon.orbit2.viewmodel.container
import com.fireblade.core.model.State
import com.fireblade.core.post.PostItem
import com.fireblade.persistence.ISocialMediaRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val socialMediaRepository: ISocialMediaRepository
) : ContainerHost<FeedScreenState, FeedSideEffect>, ViewModel() {

    override val container = container<FeedScreenState, FeedSideEffect>(FeedScreenState()) {
        if (it.posts.isEmpty()) {
            loadPostItems()
        } else {
            onPostItemsLoaded()
        }
    }

    private fun loadPostItems() = orbit {
        transformRx2Observable {
            socialMediaRepository.getPostItems().toObservable()
        }
            .reduce {
                state.copy(
                    feedState = State.Ready,
                    posts = event
                )
            }
    }

    private fun onPostItemsLoaded() = orbit {
        reduce {
            state.copy(
                feedState = State.Ready
            )
        }
    }

    fun onPostItemClicked(postItem: PostItem) = orbit {
        sideEffect {
            post(FeedSideEffect.NavigateToDetail(postItem))
        }
    }
}