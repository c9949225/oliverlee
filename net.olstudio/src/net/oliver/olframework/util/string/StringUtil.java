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
 * ��װ��һЩ�ַ����ĳ��÷���
 * 
 * @author mlrain
 */
public class StringUtil
{
    /**
     * �����
     */
    public final static int LEFT = 0;

    /**
     * ���ж���
     */
    public final static int CENTER = 1;

    /**
     * �Ҷ���
     */
    public final static int RIGHT = 2;

    /**
     * ���ַ���
     */
    public final static String BLANK = "";

    private StringUtil()
    {
    }

    /**
     * �ɵ����ַ������ַ���
     * 
     * @param c
     *            �ַ���Դ�ַ�
     * @return ���ַ�c���ɵ��ַ���
     * @deprecated JDK�����ṩ���������
     * @see java.lang.String#valueOf(char)
     */
    public static String getString(char c)
    {
        char[] abTmp = new char[1];
        abTmp[0] = c;
        return new String(abTmp);
    }

    /**
     * ���ָ�����ȵ��ɿո��ظ���ɵ��ַ���
     * 
     * @param iLen
     *            �����ַ�������
     * @return ���ո����ַ���
     */
    public static String getBlankStr(int iLen)
    {
        return getBlankStr(" ", iLen);
    }

    /**
     * ���ָ�����ȵ���ָ���ַ��ظ���ɵ��ַ���
     * 
     * @param cIn
     *            ����ַ�
     * @param iLen
     *            �����ַ�������
     * @return ���ָ���ַ�����ַ���
     */
    public static String getBlankStr(char cIn, int iLen)
    {
        return getBlankStr(getString(cIn), iLen);
    }

    /**
     * ���ָ�����ȵ���ָ���ַ����ظ���ɵ��ַ��� <BR>
     * ������ַ���Ϊ���ֽڵĻ�������ַ������ȿ��ܱ���Ҫ��Ҫ <b>�� </B>
     * 
     * @param sIn
     *            ����ַ���
     * @param iLen
     *            �����ַ�������
     * @return ���ָ���ַ�������ַ���
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
     * <DT><B>����ַ��� </B></DT>
     * <p>
     * <DD>Դ�ַ����Ҷ��룬����ַ�Ϊ�ո�</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ��
     * @param len
     *            ���賤��
     * @return ������ַ���
     */
    public static String fillStr(String source, int len)
    {
        return fillStr(source, " ", len, RIGHT);
    }

    /**
     * ����ַ��� <BR>
     * Դ�����ȴ��ڻ�������賤���򷵻�ԭ�� <BR>
     * Դ������С�����賤���򰴶��뷽ʽ���
     * 
     * @param source
     *            Դ��
     * @param len
     *            ���賤��
     * @param align
     *            ���뷽ʽ��0-��1-�У�2-�ң�
     * @return ������ַ���
     */
    public static String fillStr(String source, int len, int align)
    {
        return fillStr(source, " ", len, align);
    }

    /**
     * 
     * <DL>
     * <DT><B>����ַ��� </B></DT>
     * <p>
     * <DD>Դ���Ҷ���</DD>
     * </DL>
     * <p>
     * ����ʱ��: 2005-6-16 13:59:08
     * 
     * @param source
     *            Դ��
     * @param cIn
     *            ����ַ�
     * @param len
     *            ���賤��
     * @return ������ַ���
     */
    public static String fillStr(String source, char cIn, int len)
    {
        return fillStr(source, cIn, len, RIGHT);
    }

    /**
     * <p>
     * ����ַ��� <BR>
     * Դ�����ȴ��ڻ�������賤���򷵻�ԭ�� <BR>
     * Դ������С�����賤���򰴶��뷽ʽ���
     * </p>
     * �Զ�������ַ� <BR>
     * 
     * @param source
     *            Դ��
     * @param cIn
     *            ����ַ�
     * @param len
     *            ���賤��
     * @param align
     *            ���뷽ʽ��0-��1-�У�2-�ң�
     * @return ������ַ���
     */
    public static String fillStr(String source, char cIn, int len, int align)
    {
        return fillStr(source, getString(cIn), len, align);
    }

    /**
     * <p>
     * ����ַ��� <BR>
     * Դ�����ȴ��ڻ�������賤���򷵻�ԭ�� <BR>
     * Դ������С�����賤���򰴶��뷽ʽ���
     * </p>
     * �Զ�������ַ��� <BR>
     * 
     * @param source
     *            Դ��
     * @param sIn
     *            ����ַ���
     * @param len
     *            ���賤��
     * @param align
     *            ���뷽ʽ��0-��1-�У�2-�ң�
     * @return ������ַ���
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
     * �ж��ַ����Ƿ��ڸ������ַ��������� <BR>
     * 
     * @param parent
     *            �ַ�������
     * @param child
     *            �ַ���
     * @return �������򷵻�true�����򷵻�false
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
     * �ж��ַ����Ƿ��ڸ������ַ��������У���������λ�� <BR>
     * 
     * @param parent
     *            �ַ�������
     * @param child
     *            �ַ���
     * @return �ַ����������е�λ�ã���һ��λ��Ϊ0��
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
     * <DD>��ΪString.split(String)��jdk1.4֮ǰ�İ汾��û�У������ṩ�������</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @param c
     *            �ָ������ַ���
     * @return �ָ����ַ�������
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
     * <DD>��Ϊ <code>String.split(String)</code> ��jdk1.4֮ǰ�İ汾��û�У������ṩ���������ʹ��
     * <code>StringTokenizer</code> ʵ�֡����ԣ��ѿյĶζ������˵��ˡ������Ҫ�յĶΣ�����ʹ��
     * <code>splitEx(String, char)</code> ������</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @param s
     *            �ָ���
     * @return �ָ����ַ�������
     */
    public static String[] split(String source, String s)
    {
        // N row(s) below edited by mlrain @2006-12-12 22:08:41
        // for: ʹ��commons-lang���еķ���������߳���Ч��
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
     * <DD>��Ϊ <code>String.split(String)</code> ��jdk1.4֮ǰ�İ汾��û�У������ṩ���������ʹ��
     * <code>StringBuffer</code> ʵ�֡��������жΣ������յĶΡ� <BR>
     * <B>˵���� </B>ֻ֧���ַ��ָ</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @param c
     *            �ָ������ַ���
     * @return �ָ����ַ�������
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
     * <DD>�ַ����Ķ��ַ����ĵ����滻������jdk1.5֮ǰ���ṩ�����ṩ֮</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @param from
     *            ��Ҫ�滻���ַ���
     * @param to
     *            �����滻���ַ���
     * @return �滻����ַ���
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
     * <DT><B>�ж��ַ����Ƿ�Ϊ�� </B></DT>
     * <p>
     * <DD>���ַ���Ϊnull����Ϊ�գ�����ֻ�����ո��򷵻�true</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            ��Ҫ�жϵ��ַ���
     * @return �Ƿ�Ϊ��
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
     * <DT><B>��ֵȷ�� </B></DT>
     * <p>
     * <DD>��Դ�ַ���Ϊ��ֵ��null��""���򷵻ؿ��ַ��������򷵻�Դ�ַ���</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @return �������ַ���
     */
    public static String checkBlank(String source)
    {
        return checkBlank(source, BLANK);
    }

    /**
     * 
     * <DL>
     * <DT><B>��ֵȷ�� </B></DT>
     * <p>
     * <DD>��Դ�ַ���Ϊ��ֵ��null��""���򷵻ر����ַ��������򷵻�Դ�ַ���</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @param back
     *            �����ַ���
     * @return �������ַ���
     */
    public static String checkBlank(String source, String back)
    {
        return empty(source) ? back : source;
    }

    /**
     * 
     * <DL>
     * <DT><B>��ȡGB2312������ַ��� </B></DT>
     * <p>
     * <DD>Դ�ַ����ı��뷽ʽ����ϵͳĬ�ϱ���</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @return GB2312������ַ���
     */
    public static String getGB2312(String source)
    {
        return getCodedStr(source, "GB2312");
    }

    /**
     * 
     * <DL>
     * <DT><B>���ַ�����GB2312ת��Ϊ���ر��뷽ʽ </B></DT>
     * <p>
     * <DD>��ϸ����</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @return ���ر��뷽ʽ���ַ���
     */
    public static String getFromGB2312(String source)
    {
        return getCodedStr(source, "GB2312", "");
    }

    /**
     * 
     * <DL>
     * <DT><B>��ȡ�ض�������ַ��� </B></DT>
     * <p>
     * <DD>Դ�ַ����ı��뷽ʽ����ϵͳĬ�ϱ���</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @param codeName
     *            �ַ����뷽ʽ���ƣ��磺GB2312��UTF-8��ISO8859-1
     * @return �������ַ���
     */
    public static String getCodedStr(String source, String codeName)
    {
        return getCodedStr(source, "", codeName);
    }

    /**
     * 
     * <DL>
     * <DT><B>��ȡ�ض�������ַ��� </B></DT>
     * <p>
     * <DD>��ϸ����</DD>
     * </DL>
     * <p>
     * 
     * @param source
     *            Դ�ַ���
     * @param oriCodeName
     *            �ַ���ԭ���ı��뷽ʽ���ƣ��磺GB2312��UTF-8��ISO8859-1
     * @param newCodeName
     *            �ַ����µı��뷽ʽ���ƣ��磺GB2312��UTF-8��ISO8859-1
     * @return �������ַ���
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
     * <DT><B>�ַ������ض��ַ����ִ��� </B></DT>
     * <p>
     * <DD>�ַ������ض��ַ����ִ�����</DD>
     * </DL>
     * <p>
     * 
     * @param str
     *            �ַ���
     * @param c
     *            ָ�����ַ�
     * @return �ַ����ִ���
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
     * <DT><B>ȥ���ַ����е��Ű��ַ� </B></DT>
     * <p>
     * <DD>ȥ���ַ����е��Ʊ�������з����س�����ȥ����ͷ�Ŀո��ַ�����ѡ����</DD>
     * </DL>
     * 
     * @param source
     *            Դ�ַ�������ֵΪnull�򷵻ؿ��ַ���
     * @param trim
     *            �Ƿ�ȥ����ͷ�Ŀո�
     * @return ȥ�Ű��ַ�����ַ���
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
     * <DT><B>�ַ��������ֵļ�/���� </B></DT>
     * <p>
     * <DD>ֻ֧�ֳ������ַ����ļ�/����</DD>
     * </DL>
     * <p>
     * 
     * @param op1
     *            ������1
     * @param op2
     *            ������2
     * @return ���
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