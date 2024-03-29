package net.oliver.olframework.util.filesystem.html;

/**
 * 有时候我们在处理页面提交过来的中文产生乱码不容易解决时，
 * 比如页面选择了别的编码，而 AJAX 是用的 UTF-8 字符集，
 * 
 * 我们可以对要发送到服务器的中文用 Javascript 的 escape 函数进行编码，
 * 
 * 然而 Java 中又没有相应的 unescape 函数。
 * 
 * 而且 Java 中的 java.net.URLDecoder/java.net.URLEncoder 
 * 也对应不上 javascript 的 encodeURI/decodeURI 和 encodeURIComponent/decodeURIComponent 函数。
 * 
 * 所以我去网上找来了一段能够与 Javascript 的 escape/unescape 函数的代码。
 * 
 * 对于传送后偶尔会出现乱码的中文字符串用 javascript 的 escape 编码后，传到服务器，就
 * 
 * 能用下面的方法 unescape 解码了，escape 与 encodeURI 可是不一样的。
 */
public class EscapeUnescape {

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static void main(String[] args) {
		String tmp = "中文";
		System.out.println("testing escape : " + tmp);
		tmp = escape(tmp);
		System.out.println(tmp);
		System.out.println("testing unescape :" + tmp);
		System.out.println(unescape("%u6211%u4eec"));
	}
}