package com.stone.cheakin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.stone.cheakin.R
import com.stone.cheakin.until.DateUtil
import com.stone.cheakin.widget.CheakInLinstener
import kotlinx.android.synthetic.main.item_date_gv.view.*
import java.util.*

/**
 * @Description:    日期适配器
 * @Author:         Stone
 * @CreateDate:     2019/8/28 14:39
 * @UpdateDate:     2019/8/28 14:39
 * @param cheakColor 已签到颜色
 * @param cheakDays 签到号数，从0开始，1号为0
 */
class DateAdapter(
    val context: Context,
    val cheakColor: Int,
    val unCheakColor: Int
) : BaseAdapter() {
//    private val days = ArrayList<Int>() //日历数据
//    private val status = ArrayList<Boolean>() //签到状态，实际应用中初始化签到状态可通过该字段传递, true代表已签到状态
    val days = ArrayList<Data>()
    private var linstener: CheakInLinstener? = null

    init {
        var data = Data()
        for (i in 0 until DateUtil.firstDayOfMonth() - 1) {//DateUtil.getFirstDayOfMonth()获取当月第一天是星期几，星期日是第一天，一号之前的日子全部隐藏
            days.add(data)//无数据隐藏
        }
        for (i in 0 until DateUtil.currentMonthLastDay()) {//获取当月天数
            data = Data(i + 1)
            days.add(data)
        }
    }

    fun setCheakDays(cheakDays: ArrayList<Boolean>){
        for(item in days){
            if(item.day!!>0){
                item.isCheak = cheakDays[item.day!!]//设置对应号数状态已签到，下标对应号数
            }
        }
    }

    fun setCheak(day: Int){
        //有效日期为(DateUtil.firstDayOfMonth()-1）开始，对应当月1日
        val item = days[DateUtil.firstDayOfMonth() - 2 + day]
            if(item.day == day){
                if(item.isCheak!!){
                    linstener?.onChecked(day)
                }else{
                    item.isCheak = true
                    linstener?.onSuccess(day)
                }

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
        viewHolder.tv?.text = days[i].day.toString()
        if (days[i].day == 0) {
            viewHolder.rlItem?.visibility = View.GONE
        }
        if (days[i].isCheak!!) {
            viewHolder.tv?.setTextColor(cheakColor)
            viewHolder.ivStatus?.visibility = View.VISIBLE
        } else {
            viewHolder.tv?.setTextColor(unCheakColor)
            viewHolder.ivStatus?.visibility = View.GONE
        }

        return root
    }

    internal inner class ViewHolder {
        var rlItem: RelativeLayout? = null
        var tv: TextView? = null
        var ivStatus: ImageView? = null
    }

    fun setCheckInLinstener(linstener: CheakInLinstener) {
        this.linstener = linstener
    }

    data class Data(var day: Int? = 0,
               var isCheak: Boolean? = false)
}

