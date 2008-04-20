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
 * 普通的String通用类 SDAICC
 * 
 * @author 沈军平 Nov 24, 2005 10:23:33 AM
 */
public final class StringUtil
{
    private static int codeCount = 0;

    private static DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");

    private static DecimalFormat floatFormat = new DecimalFormat("#0.00");
    
    // Oliver's addition starts
    
    /**
     * 使字符串第一个字符大写,prefix为追加到前面的内容,例如:set,get
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
     * 加密源字典
     */
    private static final char[] sourceDictionary = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();

    /**
     * 加密目标字典
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
     * 解密 采用字典一一对应进行解密
     * 
     * @param src
     *            源字符串
     * @return 目标字符串
     */
    public static final String decrypt(String src)
    {
        return encrypt(src, targetDictionary, sourceDictionary);
    }

    /**
     * 解密 采用连续两次字典匹配解密
     * 
     * @param src
     *            源字符串
     * @return 目标字符串
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
     * 加密 采用字典一一对应进行加密
     * 
     * @param src
     *            源字符串
     * @return 目标字符串
     */
    public static final String encrypt(String src)
    {
        return encrypt(src, sourceDictionary, targetDictionary);
    }

    /**
     * 加密 采用简单的字典算法进行处理
     * 
     * @param src
     *            源字符串
     * @param sd
     *            源字典
     * @param td
     *            目标字典
     * @return 目标字符串
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
     * 加密 采用连续两次字典匹配加密
     * 
     * @param src
     *            源字符串
     * @return 目标字符串
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
     * 注:在使用的时候可以直接使用Spring
     * @param s
     * @return
     */
//    public static final String htmlEscape(String s)
//    {
//        return org.springframework.web.util.HtmlUtils.htmlEscape(s);
//    }

    /**
     * Turn HTML character references into their plain text UNICODE equivalent.
     * 注:在使用的时候可以直接使用Spring
     * @param s
     * @return
     */
//    public static final String htmlUnescape(String s)
//    {
//        return org.springframework.web.util.HtmlUtils.htmlUnescape(s);
//    }

    /**
     * 把字符串左边的空格给去掉
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
     * 如果s为空，则返回defaultValue，否则返回s
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
     * 把输入的值转换为boolean，如果不能转换则返回默认值
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
     * 分析字符串 如果里面有被第一个"${"和最后一个"}"包围的部分，则返回该部分 否则返回null
     * 
     * @param strVal
     *            要分析的字符串
     * @return
     */
    public static final String parseString(String strVal)
    {
        return parseString(strVal, "${", "}");
    }

    /**
     * 分析字符串 如果里面有被第一个placeholderPrefix和最后一个placeholderSuffix包围的部分，则返回该部分
     * 否则返回null
     * 
     * @param strVal
     *            要分析的字符串
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
     * 替换字符串
     * 
     * @param str
     *            需要处理的字符串
     * @param oldStr
     *            要被替换掉的字段
     * @param newStr
     *            用来替换的字段
     * @return 新的字段,譬如: "124563"=replace("123333","333","456")
     *         如果str为null,则返回为"", 如果str中未含有oldstr,则返回str
     */
    public static String replace(String str, String oldStr, String newStr)
    {
        if (str != null)
        {
            int index = 0;
            int oldLen = oldStr.length();
            // oldStr字符串长度
            int newLen = newStr.length();
            // newStr字符串长度
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
     * 把字符串右边的空格给去掉
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
     * 将字符串在浏览器中显示,作文字处理
     * 
     * @param str
     * @return 以html格式返回
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
        // showstr="扩大反函数";
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
     * 获得32位的Hex uuid
     * 
     * @return
     */
//    public static final String uuidHexGenerate()
//    {
//        return new RandomGuid().toString();
//    }

    // 判断是否包含指定的元素
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

    // 将 String[] 转换为String
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

    // 在一个字符串中替换子串
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

    // 获取左边部分子串
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

    // 获取右边子串
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

    // 获取左边子串 从右开始
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

    // 获取右边子串 从右开始
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

    // 获取文件扩展名
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

    // 获取BASE64解码
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

    //  获取BASE64编码
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