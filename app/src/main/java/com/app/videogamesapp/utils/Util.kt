package com.app.videogamesapp.utils

import android.os.SystemClock
import android.view.View
import android.view.animation.LinearInterpolator

class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit,
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        v.clickAnimation()
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun View.clickListener(onSafeClick: (View) -> Unit) {

    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.clickAnimation() {
    alpha = 0.1f
    animate().apply {
        interpolator = LinearInterpolator()
        duration = 100
        alpha(1f)
        startDelay = 200
        start()
    }
}


fun View.visibleAlpha() {
    alpha=0f
    visibility=View.VISIBLE
    animate().alpha(1f).setDuration(300).start()
}

fun View.goneAlpha() {
    animate().alpha(0f).setDuration(300).start()
    visibility= View.GONE
}