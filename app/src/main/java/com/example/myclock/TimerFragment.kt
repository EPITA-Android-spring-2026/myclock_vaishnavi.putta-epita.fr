package com.example.myclock

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class TimerFragment : Fragment(R.layout.fragment_timer) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val timerText = view.findViewById<TextView>(R.id.timerText)
        val startBtn = view.findViewById<Button>(R.id.startTimer)

        startBtn.setOnClickListener {

            object : CountDownTimer(60000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    timerText.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    timerText.text = "Finished!"
                }

            }.start()

        }
    }
}