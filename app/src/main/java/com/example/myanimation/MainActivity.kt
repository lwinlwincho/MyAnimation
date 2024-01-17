package com.example.myanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var rotateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rotateButton = findViewById<Button>(R.id.button)
        textView = findViewById<TextView>(R.id.textView)

        rotateButton.setOnClickListener {
           // rotater()
            fadeIn()
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(applicationContext,"Pause 1000ms",Toast.LENGTH_SHORT).show()
            } , 100)
            fadeOut()
        }

    }

    fun fadeIn() {
        val fadeIn : Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_in)
          textView.startAnimation(fadeIn)
        Toast.makeText(applicationContext,"Fade in",Toast.LENGTH_SHORT).show()
    }

    fun fadeOut() {
        val fadeOut : Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_out)
        textView.startAnimation(fadeOut)
        Toast.makeText(applicationContext,"Fade out",Toast.LENGTH_SHORT).show()

    }

    private fun rotater() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val path = Path().apply {
               // arcTo(0f, 0f, 1000f, 1000f, 90f, 90f, true)
                moveTo(500f,0f)
            }
            val animator = ObjectAnimator.ofFloat(textView, View.X, View.Y, path)
            animator.duration = 1000
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    rotateButton.isEnabled = false
                }

                override fun onAnimationEnd(animation: Animator) {
                    rotateButton.isEnabled = true
                }
            })
            animator.start()
        }
    }
}