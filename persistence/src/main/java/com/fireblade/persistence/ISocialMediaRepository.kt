package com.fireblade.persistence

import com.fireblade.core.post.PostItem
import com.fireblade.persistence.comment.Comment
import io.reactivex.Flowable

interface ISocialMediaRepository {

    fun getCommentsByPost(postId: Int): Flowable<List<Comment>>

    fun getPostItems(): Flowable<List<PostItem>>
}