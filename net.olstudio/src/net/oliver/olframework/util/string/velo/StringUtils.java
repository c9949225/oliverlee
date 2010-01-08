package net.oliver.olframework.util.string.velo;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * �����ṩ��һЩ"��̬���ö���ķ���"�ķ���
 * ��Щ�ַ��������������ᱻ�Ƶ�turbine���ַ�����������
 * ����ѡ��velocity��Դ�ļ�
 * 
 */
public class StringUtils
{
    /**
     * ��ȡ��ϵͳ����line.separatorָ�����ص����ϵͳ��·���ָ���
     */
    private static final String EOL = System.getProperty("line.separator");
    
    public static void main(String[] xx)
    {
    	System.out.println(insertAt(1,"abcde","xxx"));
    }
    
    /**
     * ���ַ���ָ��λ�ò���һ�������ַ���,insertAt(1,"abcde","xxx")�����axxxbcde
     * 
     * @param position
     * @param input
     * @param part
     * @return
     */
    public static String insertAt(int position,String input,String part)
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append(input.substring(0,position));
    	sb.append(part);
    	sb.append(input.substring(position));
    	return sb.toString();
    }

    /**
     * ��һ������б�Ϊһ���ַ�������
     */
    public String concat(List list)
    {
        StringBuffer sb = new StringBuffer();
        int size = list.size();

        for (int i = 0; i < size; i++)
        {
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }

    /**
     * ������·��ת��Ϊ�ļ�·��
     */
    static public String getPackageAsPath(String pckge)
    {
        return pckge.replace( '.', File.separator.charAt(0) ) + File.separator;
    }

    /**
     * ȥ���»��߲�����һ���ַ���д
     * foo_bar ----> FooBar
     * foo_barBar ----> FooBarbar
     */
    static public String removeUnderScores (String data)
    {
        String temp = null;
        StringBuffer out = new StringBuffer();
        temp = data;

        StringTokenizer st = new StringTokenizer(temp, "_");

        while (st.hasMoreTokens())
        {
            String element = (String) st.nextElement();
            out.append ( firstLetterCaps(element));
        }

        return out.toString();
    }

    /**
     * �շ�ʽ���滻�»���
     * foo_barBar ----> FooBarBar
     */
    static public String removeAndHump (String data)
    {
        return removeAndHump(data,"_");
    }

    /**
     * �շ�ʽ�滻�����ַ�
     *  foo*barBar ----> FooBarBar
     */
    static public String removeAndHump (String data,String replaceThis)
    {
        String temp = null;
        StringBuffer out = new StringBuffer();
        temp = data;

        StringTokenizer st = new StringTokenizer(temp, replaceThis);

        while (st.hasMoreTokens())
        {
            String element = (String) st.nextElement();
            out.append ( capitalizeFirstLetter(element));
        }//while

        return out.toString();
    }

    /**
     * ʹ������ַ�����һ����ĸ��д������һ��Сд
     */
    static public String firstLetterCaps ( String data )
    {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }

    /**
     * ����һ����ĸ��д�����ı���ԭ��
     */
    static public String capitalizeFirstLetter ( String data )
    {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1);
        return firstLetter + restLetters;
    }

    /**
     * ͨ���ַ����еķָ�����������
     */
    public static String [] split(String line, String delim)
    {
        List list = new ArrayList();
        StringTokenizer t = new StringTokenizer(line, delim);
        while (t.hasMoreTokens())
        {
            list.add(t.nextToken());
        }
        return (String []) list.toArray(new String[list.size()]);
    }

    /**
     * Ĭ����ϵͳ�ָ���Ϊ��β,ȥ��β��i���ַ�
     */
    public static String chop(String s, int i)
    {
        return chop(s, i, EOL);
    }

    /**
     * ���ַ�����β��ȥ��i���ַ�,2���ַ��Ľ�β�ַ���������������
     */
    public static String chop(String s, int i, String eol)
    {
        if ( i == 0 || s == null || eol == null )
        {
           return s;
        }

        int length = s.length();

        /*
         * if it is a 2 char EOL and the string ends with
         * it, nip it off.  The EOL in this case is treated like 1 character
         */
        if ( eol.length() == 2 && s.endsWith(eol ))
        {
            length -= 2;
            i -= 1;
        }

        if ( i > 0)
        {
            length -= i;
        }

        if ( length < 0)
        {
            length = 0;
        }

        return s.substring( 0, length);
    }

    /**
     * @param argStr
     * @param vars
     * @return Substituted String.
     */
    public static StringBuffer stringSubstitution( String argStr,
                                                   Hashtable vars )
    {
        return stringSubstitution( argStr, (Map) vars );
    }

    /**
     * ���ַ����е�$variable�滻ΪMap�е�ֵ
     * String str = "xxxx $var1 xxxx $var2";
	 * Map map = new HashMap();
	 * map.put("var1", "HHHH");
	 * map.put("var2", "KKKK");
	 * // xxxxHHHHxxxxKKKK
	 * System.out.println(stringSubstitution(str,map).toString().replaceAll(" ", ""));
     */
    public static StringBuffer stringSubstitution(String argStr,
            Map vars)
    {
        StringBuffer argBuf = new StringBuffer();

        for (int cIdx = 0 ; cIdx < argStr.length();)
        {
            char ch = argStr.charAt(cIdx);

            switch (ch)
            {
                case '$':
                    StringBuffer nameBuf = new StringBuffer();
                    for (++cIdx ; cIdx < argStr.length(); ++cIdx)
                    {
                        ch = argStr.charAt(cIdx);
                        if (ch == '_' || Character.isLetterOrDigit(ch))
                            nameBuf.append(ch);
                        else
                            break;
                    }

                    if (nameBuf.length() > 0)
                    {
                        String value =
                                (String) vars.get(nameBuf.toString());

                        if (value != null)
                        {
                            argBuf.append(value);
                        }
                    }
                    break;

                default:
                    argBuf.append(ch);
                    ++cIdx;
                    break;
            }
        }

        return argBuf;
    }

    /**
     * ��ȡһ���ļ�����Ϊ�ַ���
     */
    public static String fileContentsToString(String file)
    {
        String contents = "";

        File f = null;
        try
        {
            f = new File(file);

            if (f.exists())
            {
                FileReader fr = null;
                try
                {
                    fr = new FileReader(f);
                    char[] template = new char[(int) f.length()];
                    fr.read(template);
                    contents = new String(template);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (fr != null)
                    {
                        fr.close();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return contents;
    }

    /**
     * ȥ������
     */
    public static String collapseNewlines(String argStr)
    {
        char last = argStr.charAt(0);
        StringBuffer argBuf = new StringBuffer();

        for (int cIdx = 0 ; cIdx < argStr.length(); cIdx++)
        {
            char ch = argStr.charAt(cIdx);
            if (ch != '\n' || last != '\n')
            {
                argBuf.append(ch);
                last = ch;
            }
        }

        return argBuf.toString();
    }

    /**
     * ȥ���ո�
     */
    public static String collapseSpaces(String argStr)
    {
        char last = argStr.charAt(0);
        StringBuffer argBuf = new StringBuffer();

        for (int cIdx = 0 ; cIdx < argStr.length(); cIdx++)
        {
            char ch = argStr.charAt(cIdx);
            if (ch != ' ' || last != ' ')
            {
                argBuf.append(ch);
                last = ch;
            }
        }

        return argBuf.toString();
    }

     /**
      * ���ַ�����ʵ���¾��Ӵ��滻
      */
    public static final String sub(String line, String oldString,
            String newString)
    {
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0)
        {
            char [] line2 = line.toCharArray();
            char [] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0)
            {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * ��printStackTrace��������Ϊ�ַ�������
     */
    public static final String stackTrace(Throwable e)
    {
        String foo = null;
        try
        {
            // And show the Error Screen.
            ByteArrayOutputStream ostr = new ByteArrayOutputStream();
            e.printStackTrace( new PrintWriter(ostr,true) );
            foo = ostr.toString();
        }
        catch (Exception f)
        {
            // Do nothing.
        }
        return foo;
    }

    /**
     * ����һ����"/"��ʼ�Ļ������·���ַ���,��·��������Ӧ�û�����
     * ����: �кܶ��".."�Ļ����ᳬ��������ô����null
     */
    public static final String normalizePath(String path)
    {
        String normalized = path;
        
        // ��"\\"�滻Ϊ"/"
        if (normalized.indexOf('\\') >= 0)
        {
            normalized = normalized.replace('\\', '/');
        }
        // ǿ����"/"��ͷ
        if (!normalized.startsWith("/"))
        {
            normalized = "/" + normalized;
        }
        // ȥ�����е�"//"
        while (true)
        {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
            normalized.substring(index + 1);
        }

        // ȥ�����е�"%20"
        while (true)
        {
            int index = normalized.indexOf("%20");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) + " " +
            normalized.substring(index + 3);
        }

        // ȥ�����е�"/./"
        while (true)
        {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
            normalized.substring(index + 2);
        }

        // ȥ�����е�"/../" 
        while (true)
        {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
            normalized.substring(index + 3);
        }

        // R���ؽ��
        return (normalized);
    }

    /**
     * ���state��true����trueString false����falseString
     */
    public String select(boolean state, String trueString, String falseString)
    {
        if (state)
        {
            return trueString;
        }
        else
        {
            return falseString;
        }
    }

    /**
     * ��鴫����б����Ƿ���null����
     */
    public boolean allEmpty(List list)
    {
        int size = list.size();

        for (int i = 0; i < size; i++)
        {
            if (list.get(i) != null && list.get(i).toString().length() > 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * ��List�е������ַ���trim��
     */
    public static List trimStrings(List list)
    {
        if (list == null)
            return null;

        int sz = list.size();
        for (int i = 0; i < sz; i++)
            list.set(i,nullTrim((String) list.get(i)));
        return list;
    }

    /**
     * �����Ϊnull�ͽ��ַ���trim
     */
    public static String nullTrim(String s)
    {
        if (s == null)
        {
            return null;
        }
        else
        {
            return s.trim();
        }
    }
}
