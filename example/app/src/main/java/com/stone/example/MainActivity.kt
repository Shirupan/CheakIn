package com.stone.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.stone.cheakin.widget.CheakInLinstener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIn.initCheakDays(arrayListOf(1,24))
//        checkIn.setCheckInLinstener(object : CheakInLinstener {
//            override fun onChecked() {
//                Log.d("test", "onChecked")
//            }
//
//            override fun onSuccess() {
//                Log.d("test", "onSuccess")
//            }
//        })
        checkIn.cheakInDays(31)
        checkIn.cheakInToday()
    }
}
