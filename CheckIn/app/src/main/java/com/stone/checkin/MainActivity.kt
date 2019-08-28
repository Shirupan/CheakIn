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

        checkIn.setCheckInLinstener(object : CheckInLinstener{
            override fun onChecked() {

            }

            override fun onSuccess() {
                Log.e("wqf","Success")
            }
        })
    }
}
