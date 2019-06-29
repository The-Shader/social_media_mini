package com.fireblade.persistence.user

import android.graphics.Color
import kotlin.random.Random

class AvatarColor {

  companion object {

    fun generateColor() : Int = Color.argb(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))

    fun generateBackgroundColor(color: Int) : Int {

      return if (getLuminance(color) > 128) {
        Color.DKGRAY
      } else {
        Color.LTGRAY
      }
    }

    private fun getLuminance(color: Int) : Int  = (0.2126f * Color.red(color) + 0.7152f * Color.red(color) + 0.0722f * Color.red(color)).toInt()
  }
}