package com.example.myclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

class AlarmFragment : Fragment() {

    private lateinit var timePicker: TimePicker
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alarm, container, false)

        timePicker = view.findViewById(R.id.timePicker)
        button = view.findViewById(R.id.setAlarm)

        button.setOnClickListener {

            val hour = timePicker.hour
            val minute = timePicker.minute

            val calendar = Calendar.getInstance()
            val now = Calendar.getInstance()

            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)

            if (calendar.before(now)) {
                calendar.add(Calendar.DATE, 1)
            }

            val intent = Intent(requireContext(), AlarmReceiver::class.java)

            val pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val alarmManager =
                requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )

            Toast.makeText(requireContext(), "Alarm Set!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}