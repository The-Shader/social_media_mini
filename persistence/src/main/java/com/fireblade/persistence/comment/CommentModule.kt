package com.fireblade.persistence.comment

import com.fireblade.core.schedulers.ISchedulers
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class CommentModule @Inject constructor(private val commentDAO: CommentDAO, private val schedulers: ISchedulers) {

  fun insertComments(comments: List<DatabaseComment>) = commentDAO.insertComments(comments)

  fun getComments(): Flowable<List<DatabaseComment>> = commentDAO.getAllComments().subscribeOn(schedulers.io())

  fun getCommentsMaybe(): Maybe<List<DatabaseComment>> = commentDAO.getAllCommentsMaybe().subscribeOn(schedulers.io())

  fun getCommentsByPost(postId: Int): Flowable<List<DatabaseComment>> = commentDAO.getCommentsByPost(postId).subscribeOn(schedulers.io())
}