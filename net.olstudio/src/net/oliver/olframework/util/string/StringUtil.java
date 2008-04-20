package net.oliver.olframework.util.string;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.oliver.olframework.util.date.DateUtil;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * ��ͨ��Stringͨ���� SDAICC
 * 
 * @author ���ƽ Nov 24, 2005 10:23:33 AM
 */
public final class StringUtil
{
    private static int codeCount = 0;

    private static DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");

    private static DecimalFormat floatFormat = new DecimalFormat("#0.00");
    
    // Oliver's addition starts
    
    /**
     * ʹ�ַ�����һ���ַ���д,prefixΪ׷�ӵ�ǰ�������,����:set,get
     */
    public static String startWithUpCase(String str,String prefix)
    {
        if(Defense.isBlank(str))
        {
            return null;
        }
        
        String head = str.substring(0,1).toUpperCase();
        
        if(prefix!=null)
        {
            return prefix+head+str.substring(1);
        }
        
        return head+str.substring(1);
    }
    
    /**
     * Splits a single string into an array of strings, using a specific delimiter character.
     */
    public static String[] split(String input, char delimiter)
    {
        if (Defense.isBlank(input))
            return new String[0];

        List strings = new ArrayList();

        char[] buffer = input.toCharArray();

        int start = 0;
        int length = 0;

        for (int i = 0; i < buffer.length; i++)
        {
            if (buffer[i] != delimiter)
            {
                length++;
                continue;
            }

            // Consecutive delimiters will result in a sequence
            // of empty strings.

            String token = new String(buffer, start, length);
            strings.add(token);

            start = i + 1;
            length = 0;
        }

        // If the string contains no delimiters, then
        // wrap it an an array and return it.

        if (start == 0 && length == buffer.length)
        {
            return new String[]
            { input };
        }

        // The final token.
        String token = new String(buffer, start, length);
        strings.add(token);

        return (String[]) strings.toArray(new String[strings.size()]);
    }
    
    // Oliver's addition over
    
    /**
     * ����Դ�ֵ�
     */
    private static final char[] sourceDictionary = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();

    /**
     * ����Ŀ���ֵ�
     */
    private static final char[] targetDictionary = "qwertyuiopasdfghjklzxcvbnm1QAZ2WSX3EDC4RFV5TGB6YHN7UJM8IK9OL0P"
            .toCharArray();

    /**
     * 
     * @param str
     * @param 0:
     *            Retrieve directly 1:("ISO-8859-1")to ("gb2312");
     *            2:("gb2312")to ("ISO-8859-1"); 3:("UTF-8")to ("gb2312");
     *            4:("gb2312")to ("UTF-8");
     * @return
     */
    public static final String changeCode(String str, int unicodeFlag)
    {
        try
        {
            switch (unicodeFlag)
            {
                case 0:
                    return str;
                case 1:
                    return new String(str.getBytes("ISO-8859-1"), "gb2312");
                case 2:
                    return new String(str.getBytes("gb2312"), "ISO-8859-1");
                case 3:
                    return new String(str.getBytes("UTF-8"), "gb2312");
                case 4:
                    return new String(str.getBytes("gb2312"), "UTF-8");
                default:
                    return str;
            }
        } catch (Exception ex)
        {
            return "";
        }
    }

    /**
     * ���� �����ֵ�һһ��Ӧ���н���
     * 
     * @param src
     *            Դ�ַ���
     * @return Ŀ���ַ���
     */
    public static final String decrypt(String src)
    {
        return encrypt(src, targetDictionary, sourceDictionary);
    }

    /**
     * ���� �������������ֵ�ƥ�����
     * 
     * @param src
     *            Դ�ַ���
     * @return Ŀ���ַ���
     */
    public static final String decrypt2(String src)
    {
        return decrypt(decrypt(src));
    }

    public static final String emptyToDefault(String value, String defaultValue)
    {
        return (value != null) && (value.length() > 0) ? value : defaultValue;
    }

    // translate empty string to null string: won't trim off the space
    // just simple to use
    public static final String emptyToNull(String s)
    {
        return s == null ? s : s.length() == 0 ? null : s;
    }

    /**
     * ���� �����ֵ�һһ��Ӧ���м���
     * 
     * @param src
     *            Դ�ַ���
     * @return Ŀ���ַ���
     */
    public static final String encrypt(String src)
    {
        return encrypt(src, sourceDictionary, targetDictionary);
    }

    /**
     * ���� ���ü򵥵��ֵ��㷨���д���
     * 
     * @param src
     *            Դ�ַ���
     * @param sd
     *            Դ�ֵ�
     * @param td
     *            Ŀ���ֵ�
     * @return Ŀ���ַ���
     */
    static final String encrypt(String src, char[] sd, char[] td)
    {
        if (src == null)
            return null;
        int length = src.length();
        if (length == 0)
            return src;
        StringBuffer targetBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++)
        {
            char temp = src.charAt(i);
            int index;
            for (index = 0; index < sd.length; index++)
            {
                if (sd[index] == temp)
                    break;
            }
            if (index < sd.length)
            {
                targetBuffer.append(td[index]);
            } else
            {
                targetBuffer.append(temp);
            }
        }
        return targetBuffer.toString();
    }

    /**
     * ���� �������������ֵ�ƥ�����
     * 
     * @param src
     *            Դ�ַ���
     * @return Ŀ���ַ���
     */
    public static final String encrypt2(String src)
    {
        return encrypt(encrypt(src));
    }

    public static final String formatFloat(float number)
    {
        return floatFormat.format(number);
    }

    public static final String getCode()
    {
        // return
        // dateFormat.format(DateUtil.nowDate())+'-'+uuidHexGenerate().substring(27,31);
        int tmp;
        synchronized (dateFormat)
        {
            tmp = (++codeCount) % 10000;
        }
        String s = String.valueOf(tmp);
        for (int i = s.length(); i < 4; i++)
        {
            s = '0' + s;
        }
        return dateFormat.format(DateUtil.nowDate()) + '-' + s;
    }

    /**
     * Turn special characters into HTML character references.
     * ע:��ʹ�õ�ʱ�����ֱ��ʹ��Spring
     * @param s
     * @return
     */
//    public static final String htmlEscape(String s)
//    {
//        return org.springframework.web.util.HtmlUtils.htmlEscape(s);
//    }

    /**
     * Turn HTML character references into their plain text UNICODE equivalent.
     * ע:��ʹ�õ�ʱ�����ֱ��ʹ��Spring
     * @param s
     * @return
     */
//    public static final String htmlUnescape(String s)
//    {
//        return org.springframework.web.util.HtmlUtils.htmlUnescape(s);
//    }

    /**
     * ���ַ�����ߵĿո��ȥ��
     * 
     * @param s
     * @return
     */
    public static final String ltrim(String s)
    {
        int len = s.length();
        int st = 0;
        int off = 0; /* avoid getfield opcode */
        char[] val = s.toCharArray(); /* avoid getfield opcode */

        while ((st < len) && (val[off + st] <= ' '))
        {
            st++;
        }
        return ((st > 0) || (len < s.length())) ? s.substring(st, len) : s;
    }

    public static final boolean nullToDefault(Boolean value,
            boolean defaultValue)
    {
        return value == null ? defaultValue : value.booleanValue();
    }

    public static final int nullToDefault(Integer value, int defaultValue)
    {
        return value == null ? defaultValue : value.intValue();
    }

    /**
     * ���sΪ�գ��򷵻�defaultValue�����򷵻�s
     * 
     * @param s
     * @param defaultValue
     * @return
     */
    public static final String nullToDefault(String value, String defaultValue)
    {
        return value == null ? defaultValue : value;
    }

    public static final String nullToEmpty(Object o)
    {
        return o == null ? "" : o.toString();
    }

    /**
     * �������ֵת��Ϊboolean���������ת���򷵻�Ĭ��ֵ
     * 
     * @param s
     * @param defaultValue
     * @return
     */
    public static final boolean parseBoolean(String s, boolean defaultValue)
    {
        if (null == s)
            return defaultValue;
        if ("true".equalsIgnoreCase(s))
            return true;
        if ("false".equalsIgnoreCase(s))
            return false;
        return defaultValue;
    }

    // call Integer.parseInt to do
    // just simple to use
    public static final int parseInt(String value, int defaultValue)
    {
        try
        {
            return (value != null) && (value.length() > 0) ? Integer
                    .parseInt(value) : defaultValue;
        } catch (Exception ex)
        {
            return defaultValue;
        }
    }

    /**
     * �����ַ��� ��������б���һ��"${"�����һ��"}"��Χ�Ĳ��֣��򷵻ظò��� ���򷵻�null
     * 
     * @param strVal
     *            Ҫ�������ַ���
     * @return
     */
    public static final String parseString(String strVal)
    {
        return parseString(strVal, "${", "}");
    }

    /**
     * �����ַ��� ��������б���һ��placeholderPrefix�����һ��placeholderSuffix��Χ�Ĳ��֣��򷵻ظò���
     * ���򷵻�null
     * 
     * @param strVal
     *            Ҫ�������ַ���
     * @param placeholderPrefix
     * @param placeholderSuffix
     * @return
     */
    public static final String parseString(String strVal,
            String placeholderPrefix, String placeholderSuffix)
    {
        if (strVal == null || placeholderPrefix == null
                || placeholderSuffix == null)
            return null;
        int startIndex = strVal.indexOf(placeholderPrefix);
        if (startIndex != -1)
        {
            int endIndex = strVal.lastIndexOf(placeholderSuffix);
            if (endIndex != -1)
            {
                String placeholder = strVal.substring(startIndex
                        + placeholderPrefix.length(), endIndex);
                return placeholder;
            }
        }
        return null;
    }

    public static final String randomString()
    {
        return RandomStringUtils.randomNumeric(8);
    }

    /**
     * �滻�ַ���
     * 
     * @param str
     *            ��Ҫ������ַ���
     * @param oldStr
     *            Ҫ���滻�����ֶ�
     * @param newStr
     *            �����滻���ֶ�
     * @return �µ��ֶ�,Ʃ��: "124563"=replace("123333","333","456")
     *         ���strΪnull,�򷵻�Ϊ"", ���str��δ����oldstr,�򷵻�str
     */
    public static String replace(String str, String oldStr, String newStr)
    {
        if (str != null)
        {
            int index = 0;
            int oldLen = oldStr.length();
            // oldStr�ַ�������
            int newLen = newStr.length();
            // newStr�ַ�������
            while (true)
            {
                index = str.indexOf(oldStr, index);
                if (index == -1)
                {
                    return str;
                } else
                {
                    str = str.substring(0, index) + newStr
                            + str.substring(index + oldLen);
                    index += newLen;
                }
            }
        } else
        {
            return "";
        }
    }

    /**
     * ���ַ����ұߵĿո��ȥ��
     * 
     * @param s
     * @return
     */
    public static final String rtrim(String s)
    {
        int len = s.length();
        int st = 0;
        int off = 0; /* avoid getfield opcode */
        char[] val = s.toCharArray(); /* avoid getfield opcode */

        while ((st < len) && (val[off + len - 1] <= ' '))
        {
            len--;
        }
        return ((st > 0) || (len < s.length())) ? s.substring(st, len) : s;
    }

    // simulate String.spit in jdk1.4
    public static final String[] split(final String sourceStr,
            final String delimiter)
    {
        return split(sourceStr, delimiter, 0);
    }

    public static final String[] split(final String sourceStr,
            final String delimiter, final int maxSubCount)
    {
        return StringUtils.split(sourceStr, delimiter, maxSubCount);
    }

    /**
     * ���ַ��������������ʾ,�����ִ���
     * 
     * @param str
     * @return ��html��ʽ����
     */
    public static String strToHtml(String str)
    {
        return strToHtml(str, true);
    }

    private static String strToHtml(String str, boolean supportHtml)
    {
        if (str == null)
        {
            return "";
        }
        str = replace(str, " ", "&nbsp;");
        str = replace(str, "\r\n", "<br>");
        if (supportHtml == false)
        {
            str = replace(str, "&", "&amp;");
            str = replace(str, "<", "&lt;");
        }
        return str;
    }

    public static String strToShow(String str, String showstr)
    {
        if (str == null)
        {
            return "";
        }
        // showstr="���󷴺���";
        str = replace(str, " ", "&nbsp;");
        str = replace(str, "\n", "<br>");
        if (showstr.equals(""))
        {
        } else
        {
            String repstr = "<font color=green><b>" + showstr + "</b></font>";
            str = replace(str, showstr, repstr);
        }
        return str;
    }

    /**
     * ���32λ��Hex uuid
     * 
     * @return
     */
//    public static final String uuidHexGenerate()
//    {
//        return new RandomGuid().toString();
//    }

    // �ж��Ƿ����ָ����Ԫ��
    public static int member(String s, String[] a)
    {
        if (a == null)
            return -1;
        for (int i = 0; i < a.length; i++)
        {
            if (s.equals(a[i]))
            {
                return i;
            }
        }
        return -1;
    }

    // �� String[] ת��ΪString
    public static String arrayToString(String[] a1, String s)
    {
        StringBuffer s1 = new StringBuffer();
        if (a1 == null)
            return "";
        s1.append(a1[0]);
        s1.append(s);
        for (int i = 1; i < a1.length; i++)
        {
            s1.append(a1[i]);
            if (i < a1.length - 1)
                s1.append(s);
        }
        return s1.toString();
    }

    // ��һ���ַ������滻�Ӵ�
    public static String replaceSubString(String source, String from, String to)
    {
        StringBuffer s = new StringBuffer(source);
        int n;
        n = s.indexOf(from);
        while (n > -1)
        {
            s.replace(n, n + from.length(), to);
            n = s.indexOf(from);
        }
        return s.toString();
    }

    // ��ȡ��߲����Ӵ�
    public static String strLeft(String source, String s)
    {
        int n;
        n = source.indexOf(s);
        if (n <= 0)
        {
            return "";
        } else
        {
            return source.substring(0, n);
        }
    }

    // ��ȡ�ұ��Ӵ�
    public static String strRight(String source, String s)
    {
        int n;
        n = source.indexOf(s);
        if (n == -1)
        {
            return "";
        } else
        {
            return source.substring(n + s.length());
        }
    }

    // ��ȡ����Ӵ� ���ҿ�ʼ
    public static String strLeftBack(String source, String s)
    {
        int n;
        n = source.lastIndexOf(s);
        if (n <= 0)
        {
            return "";
        } else
        {
            return source.substring(0, n);
        }
    }

    // ��ȡ�ұ��Ӵ� ���ҿ�ʼ
    public static String strRightBack(String source, String s)
    {
        int n;
        n = source.lastIndexOf(s);
        if (n == -1)
        {
            return "";
        } else
        {
            return source.substring(n + s.length());
        }
    }

    // ��ȡ�ļ���չ��
    public static String getFileExt(String filename)
    {
        int aa = filename.lastIndexOf(".");
        if (aa == 0)
        {
            return "";
        } else
        {
            return filename.substring(aa + 1);
        }
    }

    // ��ȡBASE64����
    public static String getDecodeString(String s) throws IOException
    {
        if (s.length() > 11)
        {
            if (s.substring(0, 11).equals("=?GB2312?B?"))
            {
                BASE64Decoder decoder = new BASE64Decoder();
                String s1 = new String(decoder.decodeBuffer(s.substring(11, s
                        .length() - 2)));
                return s1;
            }
        }
        return s;
    }

    //  ��ȡBASE64����
    public static String getEncodeString(String s)
    {
        BASE64Encoder enc = new BASE64Encoder();
        if (s.length() > 11)
        {
            if (s.substring(0, 11).equals("=?GB2312?B?"))
            {
                return s;
            } else
            {
                String s1 = "=?GB2312?B?" + enc.encode(s.getBytes()) + "?=";
                return s1;
            }
        }
        String s1 = "=?GB2312?B?" + enc.encode(s.getBytes()) + "?=";
        return s1;
    }

}