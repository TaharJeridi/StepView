package it.tjeridi.stepview.extension

import android.view.View
import android.view.ViewGroup

fun View.updateMargins(
    leftMarginPx: Int? = null,
    topMarginPx: Int? = null,
    rightMarginPx: Int? = null,
    bottomMarginPx: Int? = null
) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        leftMarginPx?.run { params.leftMargin = this }
        topMarginPx?.run { params.topMargin = this }
        rightMarginPx?.run { params.rightMargin = this }
        bottomMarginPx?.run { params.bottomMargin = this }
        requestLayout()
    }
}