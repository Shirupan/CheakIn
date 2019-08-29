package com.stone.cheakin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.stone.cheakin.R
import com.stone.cheakin.adapter.DateAdapter
import com.stone.cheakin.adapter.WeekAdapter
import com.stone.cheakin.until.DateUtil
import kotlinx.android.synthetic.main.view_check_in.view.*
import java.util.ArrayList


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
        cheakColor: Int? = R.color.cheakColor,
        unCheakColor: Int? = R.color.unCheakColor,
        titleTextColor: Int? = R.color.titleTextColor,
        titleBgColor: Int? = R.color.titleBgColor,
        week:Array<String>? = context.resources.getStringArray(R.array.weeks)
    ) : super(context) {
        init(cheakColor!!, unCheakColor!!, titleTextColor!!, titleBgColor!!, week!!)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckInView)
        init(
            typedArray.getColor(R.styleable.CheckInView_cheakColor, resources.getColor(R.color.cheakColor))
            , typedArray.getColor(R.styleable.CheckInView_unCheakColor, resources.getColor(R.color.unCheakColor))
            , typedArray.getColor(R.styleable.CheckInView_titleTextColor, resources.getColor(R.color.titleTextColor))
            , typedArray.getColor(R.styleable.CheckInView_titleBgColor, resources.getColor(R.color.titleBgColor))
        ,context.resources.getStringArray(R.array.weeks)
        )
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckInView)
        init(
            typedArray.getColor(R.styleable.CheckInView_cheakColor, resources.getColor(R.color.cheakColor))
            , typedArray.getColor(R.styleable.CheckInView_unCheakColor, resources.getColor(R.color.unCheakColor))
            , typedArray.getColor(R.styleable.CheckInView_titleTextColor, resources.getColor(R.color.titleTextColor))
            , typedArray.getColor(R.styleable.CheckInView_titleBgColor, resources.getColor(R.color.titleBgColor))
        ,context.resources.getStringArray(R.array.weeks)
        )
    }

    private fun init(cheakColor: Int, unCheakColor: Int, titleTextColor: Int, titleBgColor: Int, week:Array<String>) {
        val view = View.inflate(context, R.layout.view_check_in, this)

        view.tvYear.text = DateUtil.currentYearAndMonth()
        view.tvYear.setTextColor(titleTextColor)
        view.tvYear.setBackgroundColor(titleBgColor)

        view.gvWeek.adapter = WeekAdapter(context, week, unCheakColor)

        adapterDate = DateAdapter(context, cheakColor, unCheakColor)
        view.gvDate.adapter = adapterDate
    }

    /**
     * 签到成功的回调
     */
    fun setCheckInLinstener(linstener: CheakInLinstener) {
        adapterDate?.setCheckInLinstener(linstener)
    }

    /**
     * 初始化签到日期
     * @param cheakDays 传入对应当月已签到的号数
     */
    fun setCheakDays(cheakDays: ArrayList<Int>) {
        val al = ArrayList<Boolean>()//下标对应号数，1-31号，忽视下标0
        for (i in 0..DateUtil.currentMonthLastDay())al.add(false)
        for(item in cheakDays) al[item] = true
        adapterDate?.setCheakDays(al)
        adapterDate?.notifyDataSetChanged()
    }
}

interface CheakInLinstener {
    fun onSuccess()
    fun onChecked()
}