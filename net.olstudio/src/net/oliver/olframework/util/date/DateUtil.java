package net.oliver.olframework.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * ���ڴ������
 * 
 * @author ���ƽ Nov 17, 2005 4:47:54 PM
 */
public final class DateUtil
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * ���ڸ�ʽ������
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * ����ʱ���ʽ������
     */
    private static DateFormat dateTimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    /**
     * ʱ���ʽ������
     */
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * ����ת��Ϊ�ַ��ܣ�yyyy-mm-dd hh:mm:ss
     * 
     * @param d
     * @return
     */
    public static String timeToString(Date d)
    {
        return sdf.format(d);
    }

    /**
     * �ַ���ת��Ϊ���ڣ�yyyy-mm-dd hh:mm:ss
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
     * ��ȡʱ���ʽ������ "yyyy-MM-dd"
     * 
     * @return
     */
    public static final DateFormat getDateFormat()
    {
        return dateFormat;
    }

    /**
     * ��ȡʱ�����ڸ�ʽ������ "yyyy-MM-dd HH:mm"
     * 
     * @return
     */
    public static final DateFormat getDateTimeFormat()
    {
        return dateTimeFormat;
    }

    /**
     * ��ȡ��ǰʱ���ʱ�����
     * 
     * @return
     */
    public static final Date nowDate()
    {
        return new Date();
    }

    /**
     * ϵͳ��Сʱ��
     * 
     * @return
     */
    public static final Date minDate()
    {
        return dateBegin(getDate(1900, 1, 1));
    }

    /**
     * ϵͳ���ʱ��
     * 
     * @return
     */
    public static final Date maxDate()
    {
        return dateEnd(getDate(2079, 1, 1));
    }

    /**
     * ��ȡָ��ʱ�����
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
     * ��ȡָ��ʱ�����
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
     * ��ȡָ��ʱ�����
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
     * ��ȡһ��ʱ�����
     * 
     * @param year
     *            ��ʽΪ��2004
     * @param month
     *            ��1��ʼ
     * @param date
     *            ��1��ʼ
     * @return
     */
    public static final Date getDate(int year, int month, int date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    /**
     * ��ȡһ��ʱ�����
     * 
     * @param year
     *            ��ʽΪ��2004
     * @param month
     *            ��1��ʼ
     * @param date
     *            ��1��ʼ
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
     * ��һ����֪ʱ��Ļ���������ָ����ʱ��
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
     * ��������ʱ����������
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
     * ��������ʱ�������ٷ���
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
     * һ��Ŀ�ʼʱ��
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
     * һ��Ľ���ʱ��
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
     * һ��Ľ���ʱ��
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
     * һ��Ŀ�ʼʱ��
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
     * һ�µĿ�ʼʱ��
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
     * һ�µļ���ʱ��
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
     * һ��Ŀ�ʼʱ��
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
     * һ��Ľ���ʱ��
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
     * ���ַ���ת��Ϊdate Ĭ�ϸ�ʽΪ "yyyy-MM-dd"
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
     * ���ַ���ת��Ϊdate Ĭ�ϸ�ʽΪ "yyyy-MM-dd HH:mm"
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
     * ��ʽ�����
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd"
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
     * ��ʽ�����
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd HH:mm"
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
