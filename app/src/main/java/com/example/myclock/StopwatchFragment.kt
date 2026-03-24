package com.example.myclock

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class StopwatchFragment : Fragment(R.layout.fragment_stopwatch) {

    private var seconds = 0
    private var running = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = view.findViewById<TextView>(R.id.stopwatchText)
        val startBtn = view.findViewById<Button>(R.id.startStopwatch)
        val resetBtn = view.findViewById<Button>(R.id.resetStopwatch) // I should check if this exists in layout

        val runnable = object : Runnable {
            override fun run() {
                seconds++
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                text.text = String.format("%02d:%02d:%02d", hours, minutes, secs)
                handler.postDelayed(this, 1000)
            }
        }

        startBtn.setOnClickListener {
            if (!running) {
                handler.post(runnable)
                running = true
                startBtn.text = "Stop"
            } else {
                handler.removeCallbacks(runnable)
                running = false
                startBtn.text = "Start"
            }
        }

        resetBtn?.setOnClickListener {
            handler.removeCallbacks(runnable)
            running = false
            seconds = 0
            text.text = "00:00:00"
            startBtn.text = "Start"
        }
    }

    override fun onPause() {
        super.onPause()
        // Optionally stop if you don't want it running in background, 
        // but for a stopwatch it usually keeps going. 
        // However, this simple implementation uses a Handler which won't be very accurate in background.
    }
}