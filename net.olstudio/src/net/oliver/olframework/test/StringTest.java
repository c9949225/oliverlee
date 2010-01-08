package net.oliver.olframework.test;

import java.util.HashMap;
import java.util.Map;

public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String str = "xxxx $var1 xxxx $var2";
//		Map map = new HashMap();
//		map.put("var1", "HHHH");
//		map.put("var2", "KKKK");
//		// xxxxHHHHxxxxKKKK
//		System.out.println(stringSubstitution(str,map).toString().replaceAll(" ", ""));
	}
	
	/**
     * 将字符串中的$variable替换为Map中的值
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
}
