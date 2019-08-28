package com.stone.checkin

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.stone.checkin.widget.CheckInLinstener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIn.checkColor = R.color.checkColor
        checkIn.unCheckColor = R.color.unCheckColor
        checkIn.setCheckInLinstener(object : CheckInLinstener{
            override fun onChecked() {

            }

            override fun onSuccess() {
                Log.e("wqf","Success")
            }
        })
    }
}
