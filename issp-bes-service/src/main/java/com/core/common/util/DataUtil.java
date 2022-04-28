package com.core.common.util;


import com.core.common.enums.StatisticalTypeEnum;
import org.ace.business.dto.TimeData;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xiepufeng
 * @date 2020/6/10 7:45
 */
public class DataUtil
{
    /**
     * 解析电表原始数据
     *
     * @param rawData  电表原始数据
     * @param rate     变比
     * @param decimals 小数位数
     * @param enctype  编码类型
     * @param isRate   是否变比
     * @return Double
     */
    public static Double parseRawData(String rawData, Integer rate, Integer decimals, Integer enctype, String isRate)
    {
        if (!StringUtils.hasText(rawData)
                || null == rate
                || null == decimals
                || null == enctype
                || null == isRate
                )
        {
            return null;
        }

        Integer dataInt = Integer.parseInt(rawData);

        /*注释原因：下位机数据已经转换，上位机不需要再次转换*/
        // 判断编码规则（bcd编码） 0 bcd 编码 1 bec 编码
        /*if (enctype.equals(0))
        {
            dataInt = Integer.parseInt(rawData, Character.FORMAT);
        }*/

        // 变比

        if ("1".equals(isRate))
        {
            dataInt *= rate;
        }

        // 根据小数个数转换数据
        return dataInt / (Math.pow(10, (decimals)));
    }

    /**
     * 格式化时间日期
     *
     * @param year   年
     * @param month  月
     * @param date   日
     * @param hour   小时
     * @param minute 分
     * @param second 秒
     * @return String
     */
    public static String formatDate(Integer year,
                                    Integer month,
                                    Integer date,
                                    Integer hour,
                                    Integer minute,
                                    Integer second)
    {
        if (null == year
                || null == month
                || null == date
                || null == hour
                || null == minute
                || null == second
                )
        {
            return null;
        }

        Calendar calendar = Calendar.getInstance();


        calendar.set(year + 2000, month - 1, date, hour, minute, second);

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return fmt.format(calendar.getTime());
    }


    /**
     * 格式化时间日期
     *
     * @param time 日期
     * @param type 统计类型
     * @return 日期
     * @throws ParseException
     */
    public static String formatDate(String time, StatisticalTypeEnum type)
    {
        if (null == time || null == type)
        {
            return null;
        }

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date;
        try
        {
            date = fmt.parse(time);

        } catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }

        switch (type)
        {
            case HOUR:

                SimpleDateFormat fmtHour = new SimpleDateFormat("yyyy-MM-dd HH:00:00");

                return fmtHour.format(date);

            case DAY:

                SimpleDateFormat fmtDay = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

                return fmtDay.format(date);

            case MONTH:

                SimpleDateFormat fmtMonth = new SimpleDateFormat("yyyy-MM-01 00:00:00");

                return fmtMonth.format(date);

            case YEAR:

                SimpleDateFormat fmtYear = new SimpleDateFormat("yyyy-01-01 00:00:00");

                return fmtYear.format(date);
        }

        return null;

    }

    public static TimeData getTimeDataObject()
    {
        TimeData timeData = new TimeData();

        // 设置时间，获取当前系统时间
        Calendar calendar = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("yy");
        Integer year =Integer.parseInt(df.format(Calendar.getInstance().getTime())) ;

        timeData.setYear(year);
        timeData.setMonth(calendar.get(Calendar.MONTH) + 1);
        timeData.setDay(calendar.get(Calendar.DATE));
        timeData.setWday(calendar.get(Calendar.DAY_OF_WEEK));
        timeData.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        timeData.setMinute(calendar.get(Calendar.MINUTE));
        timeData.setSecond(calendar.get(Calendar.SECOND));

        return timeData;
    }
}
