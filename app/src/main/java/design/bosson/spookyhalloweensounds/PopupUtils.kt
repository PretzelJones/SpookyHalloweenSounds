package design.bosson.spookyhalloweensounds

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.view.WindowManager

object PopupUtils {

    fun showPopup(context: Context) {
        // Create the AlertDialog with your custom style
        val builder = AlertDialog.Builder(context, R.style.RoundedAlertDialog)
        builder.setMessage(context.getString(R.string.popup_text_pause))
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()

        // Set up custom animations
        val scaleX = ObjectAnimator.ofFloat(dialog.window!!.decorView, "scaleX", 0.5f, 1f)
        val scaleY = ObjectAnimator.ofFloat(dialog.window!!.decorView, "scaleY", 0.5f, 1f)
        val fadeIn = ObjectAnimator.ofFloat(dialog.window!!.decorView, "alpha", 0f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.duration = 300 // Set the duration as needed
        animatorSet.playTogether(scaleX, scaleY, fadeIn)

        // Configure animations
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                dialog.window?.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            }

            override fun onAnimationEnd(animation: Animator) {
                dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        animatorSet.start()

        // Show the dialog after setting up the animations
        dialog.show()
    }
}
