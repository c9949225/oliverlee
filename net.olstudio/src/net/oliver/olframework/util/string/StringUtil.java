/*
 * Created on 2004-9-4
 * Lasted Updated on 2004-11-4 
 */
package net.oliver.olframework.util.string;

import java.io.UnsupportedEncodingException;
import java.util.Stack;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

/**
 * 封装了一些字符串的常用方法
 * 
 * @author mlrain
 */
public class StringUtil
{
    /**
     * 左对齐
     */
    public final static int LEFT = 0;

    /**
     * 居中对齐
     */
    public final static int CENTER = 1;

    /**
     * 右对齐
     */
    public final static int RIGHT = 2;

    /**
     * 空字符串
     */
    public final static String BLANK = "";

    private StringUtil()
    {
    }

    /**
     * 由单个字符生成字符串
     * 
     * @param c
     *            字符串源字符
     * @return 由字符c生成的字符串
     * @deprecated JDK自身提供了这个方法
     * @see java.lang.String#valueOf(char)
     */
    public static String getString(char c)
    {
        char[] abTmp = new char[1];
        abTmp[0] = c;
        return new String(abTmp);
    }

    /**
     * 获得指定长度的由空格重复组成的字符串
     * 
     * @param iLen
     *            所需字符串长度
     * @return 填充空格后的字符串
     */
    public static String getBlankStr(int iLen)
    {
        return getBlankStr(" ", iLen);
    }

    /**
     * 获得指定长度的由指定字符重复组成的字符串
     * 
     * @param cIn
     *            填充字符
     * @param iLen
     *            所需字符串长度
     * @return 填充指定字符后的字符串
     */
    public static String getBlankStr(char cIn, int iLen)
    {
        return getBlankStr(getString(cIn), iLen);
    }

    /**
     * 获得指定长度的由指定字符串重复组成的字符串 <BR>
     * 若填充字符串为多字节的话，结果字符串长度可能比需要的要 <b>长 </B>
     * 
     * @param sIn
     *            填充字符串
     * @param iLen
     *            所需字符串长度
     * @return 填充指定字符串后的字符串
     */
    public static String getBlankStr(String sIn, int iLen)
    {
        String sBlank = "";

        while (sBlank.length() < iLen)
        {
            sBlank += sIn;
        }
        return sBlank;
    }

    /**
     * 
     * <DL>
     * <DT><B>填充字符串 </B></DT>
     * <p>
     * <DD>源字符串右对齐，填充字符为空格</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源串
     * @param len
     *            所需长度
     * @return 填充后的字符串
     */
    public static String fillStr(String source, int len)
    {
        return fillStr(source, " ", len, RIGHT);
    }

    /**
     * 填充字符串 <BR>
     * 源串长度大于或等于所需长度则返回原串 <BR>
     * 源串长度小于所需长度则按对齐方式填充
     * 
     * @param source
     *            源串
     * @param len
     *            所需长度
     * @param align
     *            对齐方式（0-左，1-中，2-右）
     * @return 填充后的字符串
     */
    public static String fillStr(String source, int len, int align)
    {
        return fillStr(source, " ", len, align);
    }

    /**
     * 
     * <DL>
     * <DT><B>填充字符串 </B></DT>
     * <p>
     * <DD>源串右对齐</DD>
     * </DL>
     * <p>
     * 创建时间: 2005-6-16 13:59:08
     * 
     * @param source
     *            源串
     * @param cIn
     *            填充字符
     * @param len
     *            所需长度
     * @return 填充后的字符串
     */
    public static String fillStr(String source, char cIn, int len)
    {
        return fillStr(source, cIn, len, RIGHT);
    }

    /**
     * <p>
     * 填充字符串 <BR>
     * 源串长度大于或等于所需长度则返回原串 <BR>
     * 源串长度小于所需长度则按对齐方式填充
     * </p>
     * 自定义填充字符 <BR>
     * 
     * @param source
     *            源串
     * @param cIn
     *            填充字符
     * @param len
     *            所需长度
     * @param align
     *            对齐方式（0-左，1-中，2-右）
     * @return 填充后的字符串
     */
    public static String fillStr(String source, char cIn, int len, int align)
    {
        return fillStr(source, getString(cIn), len, align);
    }

    /**
     * <p>
     * 填充字符串 <BR>
     * 源串长度大于或等于所需长度则返回原串 <BR>
     * 源串长度小于所需长度则按对齐方式填充
     * </p>
     * 自定义填充字符串 <BR>
     * 
     * @param source
     *            源串
     * @param sIn
     *            填充字符串
     * @param len
     *            所需长度
     * @param align
     *            对齐方式（0-左，1-中，2-右）
     * @return 填充后的字符串
     */
    public static String fillStr(String source, String sIn, int len, int align)
    {
        String sTmp = "";

        if (source.length() < len)
        {
            if (align == LEFT)
            {
                sTmp = source + getBlankStr(sIn, len - source.length());
            } else if (align == CENTER)
            {
                int iTmp = (len - source.length()) / 2;
                sTmp = getBlankStr(sIn, iTmp) + source
                        + getBlankStr(sIn, len - iTmp);
            } else if (align == RIGHT)
            {
                sTmp = getBlankStr(sIn, len - source.length()) + source;
            } else
            {
                sTmp = source;
            }
        } else
        {
            sTmp = source;
        }

        return sTmp;
    }

    /**
     * 判断字符串是否在给定的字符串数组中 <BR>
     * 
     * @param parent
     *            字符串数组
     * @param child
     *            字符串
     * @return 若包含则返回true，否则返回false
     */
    public static boolean isIn(String[] parent, String child)
    {
        if (parent == null || child == null)
            return false;
        if (in(parent, child) >= 0)
            return true;
        return false;
    }

    /**
     * 判断字符串是否在给定的字符串数组中，并返回其位置 <BR>
     * 
     * @param parent
     *            字符串数组
     * @param child
     *            字符串
     * @return 字符串在数组中的位置（第一个位置为0）
     */
    public static int in(String[] parent, String child)
    {
        return in(parent, parent.length - 1, child);
    }

    public static int in(String[] parent, int end, String child)
    {
        if (parent == null || child == null)
            return -1;
        end = end < 0 ? 0 : end;
        end = end >= parent.length ? parent.length - 1 : end;
        for (int i = 0; i <= end; ++i)
        {
            if (parent[i].equals(child))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * <DL>
     * <DT><B>split </B></DT>
     * <p>
     * <DD>因为String.split(String)在jdk1.4之前的版本中没有，所以提供这个方法</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @param c
     *            分隔符（字符）
     * @return 分割后的字符串数组
     */
    public static String[] split(String source, char c)
    {
        return split(source, getString(c));
    }

    /**
     * 
     * <DL>
     * <DT><B>split </B></DT>
     * <p>
     * <DD>因为 <code>String.split(String)</code> 在jdk1.4之前的版本中没有，所以提供这个方法。使用
     * <code>StringTokenizer</code> 实现。所以，把空的段都给过滤掉了。如果需要空的段，可以使用
     * <code>splitEx(String, char)</code> 方法。</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @param s
     *            分隔符
     * @return 分割后的字符串数组
     */
    public static String[] split(String source, String s)
    {
        // N row(s) below edited by mlrain @2006-12-12 22:08:41
        // for: 使用commons-lang包中的方法，以提高程序效率
        return StringUtils.split(source, s);
        //        StringTokenizer st = new StringTokenizer(source, s);
        //        String[] ss = new String[st.countTokens()];
        //        int i = 0;
        //        while (st.hasMoreTokens())
        //        {
        //            ss[i++] = st.nextToken();
        //        }
        //        return ss;
    }

    /**
     * 
     * <DL>
     * <DT><B>split </B></DT>
     * <p>
     * <DD>因为 <code>String.split(String)</code> 在jdk1.4之前的版本中没有，所以提供这个方法。使用
     * <code>StringBuffer</code> 实现。返回所有段，包括空的段。 <BR>
     * <B>说明： </B>只支持字符分割！</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @param c
     *            分隔符（字符）
     * @return 分割后的字符串数组
     */
    public static String[] splitEx(String source, char c)
    {
        Vector v = new Vector();
        String[] ss = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < source.length(); ++i)
        {
            if (source.charAt(i) == c)
            {
                v.add(sb.toString());
                sb = new StringBuffer();
            } else
            {
                sb.append(source.charAt(i));
            }
        }
        v.add(sb.toString());
        ss = new String[v.size()];
        for (int i = 0; i < v.size(); ++i)
        {
            ss[i] = v.get(i).toString();
        }

        return ss;
    }

    public static String[] splitEx(String source, String s)
    {
        StringBuffer buffer = new StringBuffer();
        int deliPos = 0;

        Vector v = new Vector();
        String[] ss = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < source.length(); ++i)
        {
            // TODO
            if (source.charAt(i) == s.charAt(deliPos))
            {
                if (deliPos == s.length() - 1)
                {
                    v.add(sb.toString());
                    sb = new StringBuffer();
                    buffer = new StringBuffer();
                    deliPos = 0;
                } else
                {
                    deliPos++;
                    buffer.append(source.charAt(i));
                }
            } else
            {
                if (deliPos > 0 && deliPos < s.length() - 1)
                {
                    sb.append(buffer);
                    v.add(sb.toString());
                    sb = new StringBuffer();
                    buffer = new StringBuffer();
                    deliPos = 0;
                } else
                {
                    sb.append(source.charAt(i));
                }
            }
        }
        if (deliPos > 0 && deliPos < s.length())
        {
            v.add(sb.toString());
        }
        if (deliPos > 0 && deliPos == s.length())
        {
            v.add("");
        }
        ss = new String[v.size()];
        for (int i = 0; i < v.size(); ++i)
        {
            ss[i] = v.get(i).toString();
        }

        return ss;
    }

    public static void main(String[] args)
    {
        String s = "mlrainmlxyzmlmlms";
        //        s = "insert into agent_info (select
        // agentid.nextval,'1','1001',trim(t.xsdm),trim(t.kkdx),null,null,null,null,trim(t.qxmc),trim(t.cxsh),trim(t.gsdm),trim(t.dlhh),trim(t.dlywbh),trim(t.dlywzl),trim(t.hzrzzh),null,null,'0',null,null,null,null,null,null,null
        // from yw_cfg96 t );";
        String[] ss = splitEx(s, "ml");
        //        String[] ss = split(s, ".");
        for (int i = 0; i < ss.length; ++i)
        {
            System.out.println(i + "." + ss[i]);
        }
    }

    /**
     * 
     * <DL>
     * <DT><B>replace </B></DT>
     * <p>
     * <DD>字符串的对字符串的单纯替换操作在jdk1.5之前不提供，故提供之</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符器
     * @param from
     *            需要替换的字符串
     * @param to
     *            用于替换的字符串
     * @return 替换后的字符串
     */
    public static String replace(String source, String from, String to)
    {
        StringBuffer sb = new StringBuffer(source);
        Stack stack = new Stack();
        int index = source.indexOf(from);
        while (index >= 0)
        {
            stack.push(String.valueOf(index));
            index = source.indexOf(from, index + from.length());
        }
        while (!stack.empty())
        {
            index = Integer.parseInt(stack.pop().toString());
            sb.replace(index, index + from.length(), to);
        }
        return sb.toString();
    }

    /**
     * 
     * <DL>
     * <DT><B>判断字符串是否为空 </B></DT>
     * <p>
     * <DD>若字符串为null或者为空，或者只包括空格，则返回true</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            需要判断的字符串
     * @return 是否为空
     */
    public static boolean empty(String source)
    {
        if (source == null || source.trim().length() == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * 
     * <DL>
     * <DT><B>空值确认 </B></DT>
     * <p>
     * <DD>若源字符串为空值（null或""）则返回空字符串，否则返回源字符串</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @return 处理后的字符串
     */
    public static String checkBlank(String source)
    {
        return checkBlank(source, BLANK);
    }

    /**
     * 
     * <DL>
     * <DT><B>空值确认 </B></DT>
     * <p>
     * <DD>若源字符串为空值（null或""）则返回备用字符串，否则返回源字符串</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @param back
     *            备用字符串
     * @return 处理后的字符串
     */
    public static String checkBlank(String source, String back)
    {
        return empty(source) ? back : source;
    }

    /**
     * 
     * <DL>
     * <DT><B>获取GB2312编码的字符串 </B></DT>
     * <p>
     * <DD>源字符串的编码方式采用系统默认编码</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @return GB2312编码的字符串
     */
    public static String getGB2312(String source)
    {
        return getCodedStr(source, "GB2312");
    }

    /**
     * 
     * <DL>
     * <DT><B>将字符串从GB2312转换为本地编码方式 </B></DT>
     * <p>
     * <DD>详细介绍</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @return 本地编码方式的字符串
     */
    public static String getFromGB2312(String source)
    {
        return getCodedStr(source, "GB2312", "");
    }

    /**
     * 
     * <DL>
     * <DT><B>获取特定编码的字符串 </B></DT>
     * <p>
     * <DD>源字符串的编码方式采用系统默认编码</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @param codeName
     *            字符编码方式名称，如：GB2312、UTF-8、ISO8859-1
     * @return 编码后的字符串
     */
    public static String getCodedStr(String source, String codeName)
    {
        return getCodedStr(source, "", codeName);
    }

    /**
     * 
     * <DL>
     * <DT><B>获取特定编码的字符串 </B></DT>
     * <p>
     * <DD>详细介绍</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            源字符串
     * @param oriCodeName
     *            字符串原来的编码方式名称，如：GB2312、UTF-8、ISO8859-1
     * @param newCodeName
     *            字符串新的编码方式名称，如：GB2312、UTF-8、ISO8859-1
     * @return 编码后的字符串
     */
    public static String getCodedStr(String source, String oriCodeName,
            String newCodeName)
    {
        try
        {
            if (oriCodeName == null || oriCodeName.trim().length() == 0)
            {
                return new String(source.getBytes(), newCodeName);
            }
            if (newCodeName == null || newCodeName.trim().length() == 0)
            {
                return new String(source.getBytes(oriCodeName));
            }
            return new String(source.getBytes(oriCodeName), newCodeName);
        } catch (UnsupportedEncodingException e)
        {
            return source;
        }
    }

    /**
     * 
     * <DL>
     * <DT><B>字符串中特定字符出现次数 </B></DT>
     * <p>
     * <DD>字符串中特定字符出现次数。</DD>
     * </DL>
     * <p>
     * 
     * @param str
     *            字符串
     * @param c
     *            指定的字符
     * @return 字符出现次数
     */
    public static int countCharAppearTimes(String str, char c)
    {
        int times = 0;
        for (int i = 0; i < str.length(); ++i)
        {
            if (str.charAt(i) == c)
                times++;
        }
        return times;
    }

    /**
     * 
     * <DL>
     * <DT><B>去掉字符串中的排版字符 </B></DT>
     * <p>
     * <DD>去掉字符串中的制表符、换行符、回车符，去掉两头的空格字符（可选）。</DD>
     * </DL>
     * 
     * @param source
     *            源字符串，若值为null则返回空字符串
     * @param trim
     *            是否去掉两头的空格
     * @return 去排版字符后的字符串
     */
    public static String trimBlank(String source, boolean trim)
    {
        source = checkBlank(source);

        String[] chars = new String[]
        { "\n", "\r", "\t" };
        for (int i = 0; i < chars.length; ++i)
        {
            source = StringUtil.replace(source, chars[i], "");
        }
        if (trim)
            source = source.trim();
        return source;
    }

    /**
     * 
     * <DL>
     * <DT><B>字符串型数字的加/减法 </B></DT>
     * <p>
     * <DD>只支持长整型字符串的加/减。</DD>
     * </DL>
     * <p>
     * 
     * @param op1
     *            操作数1
     * @param op2
     *            操作数2
     * @return 结果
     */
    public static String strAdd(String op1, String op2)
    {
        if (empty(op1) || empty(op2))
            return "0";
        try
        {
            return String.valueOf(Long.parseLong(op1) + Long.parseLong(op2));
        } catch (NumberFormatException e)
        {
            return "0";
        }
    }
}