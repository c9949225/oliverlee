package net.oliver.olframework.util.filesystem.file.modify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddLineNumberForXML {

	public static final Pattern XML_TAG_PATTERN = Pattern
			.compile("\\s*<[^!|/].+?>\\s*");
	public static final Pattern XML_TAG_SELFEND_PATTERN = Pattern
			.compile("\\s*<[^!|/].+?/>\\s*");
	public static final Pattern XML_COMMENT = Pattern
			.compile("\\s*<!--.+-->\\s*");

	public static final String XML_TAG_END = ">";
	public static final String XML_TAG_SELF_END = "/>";
	public static final String XML_LINE_ATTRIBUTE = " line=\"$\" ";

	/**
	 * 在字符串指定位置插入一段其他字符串,insertAt(1,"abcde","xxx")会输出axxxbcde
	 * 
	 * @param position
	 * @param input
	 * @param part
	 * @return
	 */
	public static String insertAt(int position, String input, String part) {
		StringBuffer sb = new StringBuffer();
		sb.append(input.substring(0, position));
		sb.append(part);
		sb.append(input.substring(position));
		return sb.toString();
	}

	public static String addAttributeForTag(String input, String linenumber) {
		List comments = new ArrayList();
		List formal = new ArrayList();

		Matcher matcher = XML_COMMENT.matcher(input);
		while (matcher.find()) {
			comments.add(matcher.group());
		}

		matcher = XML_TAG_PATTERN.matcher(input);
		while (matcher.find()) {
			formal.add(matcher.group());
		}

		matcher = XML_TAG_SELFEND_PATTERN.matcher(input);
		while (matcher.find()) {
			formal.add(matcher.group());
		}

		StringBuffer sb = new StringBuffer();
		if (formal.size() > 0) {
			String formaltag = ((String) formal.get(0)).trim();
			if (formaltag.endsWith(XML_TAG_SELF_END)) {
				sb.append(insertAt(formaltag.length() - 2, formaltag,
						XML_LINE_ATTRIBUTE.replace("$", linenumber)));

			} else if (formaltag.endsWith(XML_TAG_END)) {
				sb.append(insertAt(formaltag.length() - 1, formaltag,
						XML_LINE_ATTRIBUTE.replace("$", linenumber)));
			}
		}

		for (int i = 0; i < comments.size(); i++) {
			sb.append((String) comments.get(i));
		}

		return sb.toString();
	}

	public static boolean addLineNumberFor(File file) {
		boolean succ = false;
		try {
			Reader reader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(reader);

			File target = new File(file.getAbsolutePath() + ".tmp");
			Writer writer = new FileWriter(target);

			BufferedWriter bufWriter = new BufferedWriter(writer);

			String line = null;
			int i = 0;
			Matcher matcher = null;
			while ((line = bufReader.readLine()) != null) {
				i++;
				matcher = XML_TAG_PATTERN.matcher(line);
				if (matcher.find()) {
					bufWriter
							.write(addAttributeForTag(line, String.valueOf(i)));
				} else {
					bufWriter.write(line);
				}
				bufWriter.newLine();
			}

			bufWriter.flush();
			bufReader.close();
			bufWriter.close();

			String path = file.getAbsolutePath();
			file.delete();
			succ = target.renameTo(new File(path));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return succ;
	}

	public static boolean addLineNumberFor(String file) {
		File lfile = new File(file);
		return addLineNumberFor(lfile);
	}

	public static void main(String[] args) {
		addLineNumberFor("D:/test.xml");
	}
}
