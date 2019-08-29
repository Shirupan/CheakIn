package com.stone.cheakin.until

import java.util.*

/**
 * @Description:
 * @Author:         Stone
 * @CreateDate:     2019/8/28 15:10
 * @UpdateDate:     2019/8/28 15:10
 */

class DateUtil {
    companion object{
        //把日期设置为当月第一天
        //日期回滚一天，也就是最后一天
        fun currentMonthLastDay(): Int{
            val a = Calendar.getInstance()
            a.set(Calendar.DATE, 1)
            a.roll(Calendar.DATE, -1)
            return a.get(Calendar.DATE)
        }

        fun currentYearAndMonth(): String{
            val a = Calendar.getInstance()
            val year = a.get(Calendar.YEAR)
            val month = a.get(Calendar.MONTH) + 1
            return year.toString() + "年" + month + "月"
        }

        fun firstDayOfMonth(): Int{
            val a = Calendar.getInstance()
            a.set(Calendar.DAY_OF_MONTH, 1)
            return a.get(Calendar.DAY_OF_WEEK)
        }

        fun currentDay(): Int{
            val a = Calendar.getInstance()
            return a.get(Calendar.DAY_OF_MONTH)
        }
    }
}