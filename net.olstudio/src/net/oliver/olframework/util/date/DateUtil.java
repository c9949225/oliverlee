package net.oliver.olframework.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 日期处理的类
 * 
 * @author 沈军平 Nov 17, 2005 4:47:54 PM
 */
public final class DateUtil
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * 日期格式化对象
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 日期时间格式化对象
     */
    private static DateFormat dateTimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    /**
     * 时间格式化对象
     */
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * 日期转换为字符窜：yyyy-mm-dd hh:mm:ss
     * 
     * @param d
     * @return
     */
    public static String timeToString(Date d)
    {
        return sdf.format(d);
    }

    /**
     * 字符窜转换为日期：yyyy-mm-dd hh:mm:ss
     * 
     * @param s
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String s)
    {
        if (s == null)
        {
            return null;
        }
        try
        {
            return sdf.parse(s);
        } catch (ParseException e)
        {
            // do nothing, just return null;
            return null;
        }
    }

    /**
     * 获取时间格式化对象 "yyyy-MM-dd"
     * 
     * @return
     */
    public static final DateFormat getDateFormat()
    {
        return dateFormat;
    }

    /**
     * 获取时间日期格式化对象 "yyyy-MM-dd HH:mm"
     * 
     * @return
     */
    public static final DateFormat getDateTimeFormat()
    {
        return dateTimeFormat;
    }

    /**
     * 获取当前时间的时间对象
     * 
     * @return
     */
    public static final Date nowDate()
    {
        return new Date();
    }

    /**
     * 系统最小时间
     * 
     * @return
     */
    public static final Date minDate()
    {
        return dateBegin(getDate(1900, 1, 1));
    }

    /**
     * 系统最大时间
     * 
     * @return
     */
    public static final Date maxDate()
    {
        return dateEnd(getDate(2079, 1, 1));
    }

    /**
     * 获取指定时间的年
     * 
     * @param date
     * @return
     */
    public static final int year(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间的月
     * 
     * @param date
     * @return
     */
    public static final int month(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时间的日
     * 
     * @param date
     * @return
     */
    public static final int day(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取一个时间对象
     * 
     * @param year
     *            格式为：2004
     * @param month
     *            从1开始
     * @param date
     *            从1开始
     * @return
     */
    public static final Date getDate(int year, int month, int date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    /**
     * 获取一个时间对象
     * 
     * @param year
     *            格式为：2004
     * @param month
     *            从1开始
     * @param date
     *            从1开始
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static final Date getDateTime(int year, int month, int date,
            int hour, int minute, int second)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, hour, minute, second);
        return calendar.getTime();
    }

    /**
     * 在一个已知时间的基础上增加指定的时间
     * 
     * @param oleDate
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static final Date addDate(Date oldDate, int year, int month, int date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        return calendar.getTime();
    }

    /**
     * 返回两个时间相差的天数
     * 
     * @param a
     * @param b
     * @return
     */
    public static final int dateSub(Date a, Date b)
    {
        int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime()
                / (24 * 60 * 60 * 1000));
        return date <= 0 ? 1 : date;
    }

    public static final int dateSubAddOne(Date a, Date b)
    {
        int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime()
                / (24 * 60 * 60 * 1000));
        return date <= 0 ? 1 : date + 1;
    }

    /**
     * 返回两个时间相差多少分钟
     * 
     * @param a
     * @param b
     * @return
     */
    public static final int subSecond(Date a, Date b)
    {
        return (int) (a.getTime() / (1000) - b.getTime() / (1000));
    }

    public static final int subSecond(String str, Date b)
    {
        Date a = null;
        try
        {
            a = timeFormat.parse(str);
        } catch (ParseException e)
        {

            return 0;
        }
        return (int) ((a.getTime() % (24 * 60 * 60 * 1000)) / 1000 - (b
                .getTime() % (24 * 60 * 60 * 1000)) / 1000);
    }

    /**
     * 一天的开始时间
     * 
     * @param date
     * @return
     */
    public static final Date dateBegin(Date date)
    {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     * 
     * @param date
     * @return
     */
    public static final Date dateEnd(Date date)
    {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     * 
     * @param calendar
     * @return
     */
    public static final Calendar dateEnd(Calendar calendar)
    {
        if (calendar == null)
            return null;
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 一天的开始时间
     * 
     * @param calendar
     * @return
     */
    public static final Calendar dateBegin(Calendar calendar)
    {
        if (calendar == null)
            return null;
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 一月的开始时间
     * 
     * @param date
     * @return
     */
    public static final Date monthBegin(Date date)
    {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一月的技术时间
     * 
     * @param date
     * @return
     */
    public static final Date monthEnd(Date date)
    {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 一年的开始时间
     * 
     * @param date
     * @return
     */
    public static final Date yearBegin(Date date)
    {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, month);
        dateBegin(calendar);
        return calendar.getTime();
        // return parseDate(formatDate(date).substring(0,4)+"-01-01");
    }

    /**
     * 一年的结束时间
     * 
     * @param date
     * @return
     */
    public static final Date yearEnd(Date date)
    {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
        // return parseDate(formatDate(date).substring(0,4)+"-12-31");
    }

    /**
     * 从字符串转换为date 默认格式为 "yyyy-MM-dd"
     * 
     * @param source
     * @return
     */
    public static final Date parseDate(String source)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("parseDate(String) - start");
        }

        if (source == null || source.length() == 0)
            return null;
        try
        {
            Date returnDate = dateFormat.parse(source);
            if (logger.isDebugEnabled())
            {
                logger.debug("parseDate(String) - end");
            }
            return returnDate;
        } catch (ParseException e)
        {
            logger.error("DateUtil parseDate error", e);

            if (logger.isDebugEnabled())
            {
                logger.debug("parseDate(String) - end");
            }
            return null;
        }
    }

    /**
     * 从字符串转换为date 默认格式为 "yyyy-MM-dd HH:mm"
     * 
     * @param source
     * @return
     */
    public static final Date parseDateTime(String source)
    {
        if (source == null || source.length() == 0)
            return null;
        try
        {
            return dateTimeFormat.parse(source);
        } catch (ParseException e)
        {
            logger.error("DateUtil parseDate error", e);
            return null;
        }
    }

    /**
     * 格式化输出
     * 默认格式为 "yyyy-MM-dd"
     * @param date
     * @return
     */
    public static String formatDate(Date date)
    {
        if (date == null)
            return "";
        return dateFormat.format(date);
    }

    /**
     * 格式化输出
     * 默认格式为 "yyyy-MM-dd HH:mm"
     * @param date
     * @return
     */
    public static String formatDateTime(Date date)
    {
        if (date == null)
            return "";
        return dateTimeFormat.format(date);
    }
}
