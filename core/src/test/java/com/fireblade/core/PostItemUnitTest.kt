package com.fireblade.core

import android.graphics.Color
import com.fireblade.core.post.PostItem
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PostItemUnitTest {

    private val testId = 1

    private val testTitle = "Title"

    private val testBody = "Test Body"

    private val testAuthor = "Test Author"

    private val numOfComments = 2

    private val testColor = Color.RED

    @Test
    fun postItem_CreateSuccess() {

        val postItem = PostItem(id = testId, title = testTitle, body = testBody, author = testAuthor, numOfComments = numOfComments)

        assertTrue(postItem.id == testId)
        assertTrue(postItem.title == testTitle)
        assertTrue(postItem.body == testBody)
        assertTrue(postItem.author == testAuthor)
        assertTrue(postItem.numOfComments == numOfComments)
        assertTrue(postItem.avatarColor == Color.WHITE)
    }

    @Test
    fun postItem_CreateCustomColorFail() {

        val postItem = PostItem(id = testId, title = testTitle, body = testBody, author = testAuthor, numOfComments = numOfComments)

        assertFalse(postItem.avatarColor == testColor)
    }

    @Test
    fun postItem_CreateCustomColorSuccess() {

        val postItem = PostItem(id = testId, title = testTitle, body = testBody, author = testAuthor, numOfComments = numOfComments, avatarColor = testColor)

        assertTrue(postItem.avatarColor == testColor)
    }
}