package com.example.myclock

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class StopwatchFragment : Fragment(R.layout.fragment_stopwatch) {

    private var seconds = 0
    private val handler = Handler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val text = view.findViewById<TextView>(R.id.stopwatchText)
        val startBtn = view.findViewById<Button>(R.id.startStopwatch)

        startBtn.setOnClickListener {

            handler.post(object : Runnable {

                override fun run() {

                    seconds++
                    text.text = seconds.toString()

                    handler.postDelayed(this, 1000)
                }
            })
        }
    }
}