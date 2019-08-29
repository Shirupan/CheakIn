package com.stone.checkin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.stone.checkin.R
import com.stone.checkin.until.DateUtil
import com.stone.checkin.widget.CheckInLinstener
import kotlinx.android.synthetic.main.item_date_gv.view.*
import java.util.ArrayList

/**
 * @Description:    日期适配器
 * @Author:         Stone
 * @CreateDate:     2019/8/28 14:39
 * @UpdateDate:     2019/8/28 14:39
 */
class DateAdapter(
    val context: Context,
    val checkColor: Int,
    val unCheckColor: Int
) : BaseAdapter() {
    private val days = ArrayList<Int>() //日历数据
    private val status = ArrayList<Boolean>() //签到状态，实际应用中初始化签到状态可通过该字段传递, true代表已签到状态
    private var linstener: CheckInLinstener? = null

    init {
        for (i in 0 until DateUtil.firstDayOfMonth() - 1) {
            //DateUtil.getFirstDayOfMonth()获取当月第一天是星期几，星期日是第一天，依次类推
            days.add(0)
            //0代表需要隐藏的item
            status.add(false)
        }
        for (i in 0 until DateUtil.currentMonthLastDay()) {//获取当月天数
            days.add(i + 1)
            //初始化日历数据
            status.add(false)
        }
    }

    override fun getCount(): Int {
        return days.size
    }

    override fun getItem(i: Int): Any {
        return days[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var root = view
        val viewHolder: ViewHolder
        if (root == null) {
            root = LayoutInflater.from(context).inflate(R.layout.item_date_gv, null)
            viewHolder = ViewHolder()
            root!!.tag = viewHolder
        } else {
            viewHolder = root.tag as ViewHolder
        }

        viewHolder.tv = root.tvWeek
        viewHolder.rlItem = root.rlItem
        viewHolder.ivStatus = root.ivStatus
        viewHolder.tv?.text = days[i].toString()
        if (days[i] == 0) {
            viewHolder.rlItem?.visibility = View.GONE
        }
        if (status[i]) {
            viewHolder.tv?.setTextColor(checkColor)
            viewHolder.ivStatus?.visibility = View.VISIBLE
        } else {
            viewHolder.tv?.setTextColor(unCheckColor)
            viewHolder.ivStatus?.visibility = View.GONE
        }
        root.setOnClickListener {
            if (status[i]) {
                linstener?.onChecked()
            } else {
                status[i] = true
                notifyDataSetChanged()
                linstener?.onSuccess()
            }
        }
        return root
    }

    internal inner class ViewHolder {
        var rlItem: RelativeLayout? = null
        var tv: TextView? = null
        var ivStatus: ImageView? = null
    }

    fun setCheckInLinstener(linstener: CheckInLinstener) {
        this.linstener = linstener
    }
}