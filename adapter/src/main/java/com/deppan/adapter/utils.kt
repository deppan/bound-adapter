package com.deppan.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

var DURATION = 150L

fun fadeIn(itemView: View) {
    itemView.alpha = 0f
    val animatorSet = AnimatorSet()
    val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.0f, 0.5f, 1.0f)
    animator.startDelay = DURATION / 2
    animator.duration = 500
    animatorSet.play(animator)
    animator.start()
}

fun left2Right(itemView: View) {
    itemView.translationX = -400f
    itemView.alpha = 0f
    val animatorSet = AnimatorSet()
    val animatorTranslateY = ObjectAnimator.ofFloat(itemView, "translationX", -400f, 0f)
    val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
    animatorTranslateY.startDelay = DURATION
    animatorTranslateY.duration = 2 * DURATION
    animatorSet.playTogether(animatorTranslateY, animatorAlpha)
    animatorSet.start()
}

fun right2Left(itemView: View) {
    itemView.translationX = itemView.x + 400
    itemView.alpha = 0f
    val animatorSet = AnimatorSet()
    val animatorTranslateY: ObjectAnimator =
        ObjectAnimator.ofFloat(itemView, "translationX", itemView.x + 400, 0f)
    val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
    animatorTranslateY.startDelay = DURATION
    animatorTranslateY.duration = 2 * DURATION
    animatorSet.playTogether(animatorTranslateY, animatorAlpha)
    animatorSet.start()
}