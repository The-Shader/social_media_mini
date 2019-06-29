package com.fireblade.minisocialmedia.persistence.comment

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentModule @Inject constructor(private val commentDAO: CommentDAO) {

  fun insertComments(comments: List<DatabaseComment>) = commentDAO.insertComments(comments)

  fun getComments() = commentDAO.getAllComments().subscribeOn(Schedulers.io())

  fun getCommentsByPost(postId: Int) = commentDAO.getCommentsByPost(postId).subscribeOn(Schedulers.io())
}