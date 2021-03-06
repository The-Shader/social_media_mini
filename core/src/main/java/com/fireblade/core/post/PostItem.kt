package com.fireblade.core.post

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable

data class PostItem(
  val id: Int,
  val title: String,
  val body: String,
  val author: String,
  val numOfComments: Int = 0,
  val avatarColor: Int = Color.WHITE
) : Parcelable {

  constructor(parcel: Parcel) :
          this(
            id = parcel.readInt(),
            title = parcel.readString() ?: "",
            body = parcel.readString()?: "",
            author = parcel.readString()?: "",
            numOfComments = parcel.readInt(),
            avatarColor = parcel.readInt()
          )

  companion object CREATOR : Parcelable.Creator<PostItem> {
    override fun createFromParcel(source: Parcel): PostItem {
      return PostItem(source)
    }

    override fun newArray(size: Int): Array<PostItem?> {
      return arrayOfNulls(size)
    }

    @JvmStatic
    fun empty() : PostItem {
      return PostItem(-1, "", "", "")
    }

    const val TAG = "POST"
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(title)
    parcel.writeString(body)
    parcel.writeString(author)
    parcel.writeInt(numOfComments)
    parcel.writeInt(avatarColor)
  }

  override fun describeContents(): Int {
    return 0
  }
}