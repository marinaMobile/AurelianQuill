package com.gameloft.android.ANMP.G.white

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.gameloft.android.ANMP.G.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_game.*

class Game : AppCompatActivity() {
    var score:Int = 0

    var array = listOf(R.drawable.owl, R.drawable.drago, R.drawable.bird, R.drawable.dragon)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val shakeAnimation: Animation = AnimationUtils.loadAnimation(this,R.anim.shake)

        start.setOnClickListener{
            startActivity(Intent(this, Game::class.java))
        }

        egg.setOnClickListener{
            egg.startAnimation(shakeAnimation)
            score++

            if(score>=35) {
                egg.setImageDrawable(resources.getDrawable(array[(0 until 4).random()]))
                egg.clearAnimation()
                egg.isClickable = false
                start.visibility = View.VISIBLE
                desc.visibility = View.INVISIBLE
            }
        }

        val running : TextView = findViewById(R.id.running)
        val timeRan : TextView = findViewById(R.id.timeRan)


        val s : Long = "10".toLong() * 1000

        object : CountDownTimer( s , 1000) {

            override fun onTick(millisUntilFinished: Long) {
                running.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                timeRan.text = "Time's Up!"
                egg.isClickable = false
                start.visibility = View.VISIBLE
                desc.visibility = View.INVISIBLE
            }
        }.start()

    }


}