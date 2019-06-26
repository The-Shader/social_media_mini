package com.fireblade.minisocialmedia.listview

interface IListView {

  fun setPostItems(postItems: List<PostItem>)

  fun handleError(error: Throwable)
}

interface IListPresenter {

  fun loadPostItems()
}