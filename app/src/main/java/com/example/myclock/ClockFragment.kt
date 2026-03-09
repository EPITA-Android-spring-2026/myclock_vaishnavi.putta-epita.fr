package com.example.myclock

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class ClockFragment : Fragment(R.layout.fragment_clock) {
    private lateinit var clockText: TextView
    private val handler = Handler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clockText = view.findViewById(R.id.clockText)

        updateClock()
    }

    private fun updateClock() {

        handler.post(object : Runnable {

            override fun run() {

                val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                val time = sdf.format(Date())

                clockText.text = time

                handler.postDelayed(this, 1000)

            }
        })
    }
}