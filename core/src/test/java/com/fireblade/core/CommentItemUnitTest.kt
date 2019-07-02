package com.fireblade.core

import com.fireblade.core.comment.CommentItem
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CommentItemUnitTest {

    private val testContent = "Test Content"

    @Test
    fun commentItem_CreateSuccess() {

        val commentItem = CommentItem(content = testContent)

        assertFalse(commentItem.content == String())
        assertTrue(commentItem.content == testContent)
    }
}