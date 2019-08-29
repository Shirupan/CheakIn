package com.stone.checkin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.stone.checkin.R
import com.stone.checkin.adapter.DateAdapter
import com.stone.checkin.adapter.WeekAdapter
import com.stone.checkin.until.DateUtil
import kotlinx.android.synthetic.main.view_check_in.view.*


/**
 * @Description:    自定义签到控件
 * @Author:         Stone
 * @CreateDate:     2019/8/28 15:03
 * @UpdateDate:     2019/8/28 15:03
 */
class CheckInView : LinearLayout {

    private var adapterDate: DateAdapter? = null

    constructor(
        context: Context,
        checkColor: Int? = R.color.checkColor,
        unCheckColor: Int? = R.color.unCheckColor,
        titleTextColor: Int? = R.color.titleTextColor,
        titleBgColor: Int? = R.color.titleBgColor,
        week:Array<String>? = context.resources.getStringArray(R.array.weeks)
    ) : super(context) {
        init(checkColor!!, unCheckColor!!, titleTextColor!!, titleBgColor!!, week!!)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckInView)
        init(
            typedArray.getColor(R.styleable.CheckInView_checkColor, resources.getColor(R.color.checkColor))
            , typedArray.getColor(R.styleable.CheckInView_unCheckColor, resources.getColor(R.color.unCheckColor))
            , typedArray.getColor(R.styleable.CheckInView_titleTextColor, resources.getColor(R.color.titleTextColor))
            , typedArray.getColor(R.styleable.CheckInView_titleBgColor, resources.getColor(R.color.titleBgColor))
        ,context.resources.getStringArray(R.array.weeks)
        )
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckInView)
        init(
            typedArray.getColor(R.styleable.CheckInView_checkColor, resources.getColor(R.color.checkColor))
            , typedArray.getColor(R.styleable.CheckInView_unCheckColor, resources.getColor(R.color.unCheckColor))
            , typedArray.getColor(R.styleable.CheckInView_titleTextColor, resources.getColor(R.color.titleTextColor))
            , typedArray.getColor(R.styleable.CheckInView_titleBgColor, resources.getColor(R.color.titleBgColor))
        ,context.resources.getStringArray(R.array.weeks)
        )
    }

    private fun init(checkColor: Int, unCheckColor: Int, titleTextColor: Int, titleBgColor: Int, week:Array<String>) {
        val view = View.inflate(context, R.layout.view_check_in, this)

        view.tvYear.text = DateUtil.currentYearAndMonth()
        view.tvYear.setTextColor(titleTextColor)
        view.tvYear.setBackgroundColor(titleBgColor)

        view.gvWeek.adapter = WeekAdapter(context, week)

        adapterDate = DateAdapter(context, checkColor, unCheckColor)
        view.gvDate.adapter = adapterDate
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