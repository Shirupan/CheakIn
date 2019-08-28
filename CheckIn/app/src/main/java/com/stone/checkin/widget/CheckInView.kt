package com.stone.checkin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import com.stone.checkin.R
import com.stone.checkin.adapter.DateAdapter
import com.stone.checkin.adapter.WeekAdapter
import com.stone.checkin.until.DateUtil
import kotlinx.android.synthetic.main.layout_signdate.view.*

/**
 * @Description:    自定义签到控件
 * @Author:         Stone
 * @CreateDate:     2019/8/28 15:03
 * @UpdateDate:     2019/8/28 15:03
 */
class CheckInView : LinearLayout {

    private var adapterDate: DateAdapter? = null

    constructor(context: Context, checkColor: Int? = R.color.checkColor, unCheckColor: Int? = R.color.unCheckColor) : super(context) {
        init(checkColor!!, unCheckColor!!)
    }

//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init()
//    }
//
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        init()
//    }

    private fun init(checkColor: Int , unCheckColor: Int) {
        val view = View.inflate(context, R.layout.layout_signdate, this)
        view.tvYear?.text = DateUtil.currentYearAndMonth()
        view.gvWeek?.adapter = WeekAdapter(context)
        adapterDate = DateAdapter(context, checkColor, unCheckColor)
        view.gvDate?.adapter = adapterDate
    }

    /**
     * 签到成功的回调
     */
    fun setCheckInLinstener(linstener: CheckInLinstener) {
        adapterDate?.setCheckInLinstener(linstener)
    }
}

interface CheckInLinstener {
    fun onSuccess()
    fun onChecked()
}