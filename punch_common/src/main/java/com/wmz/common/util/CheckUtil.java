package com.wmz.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
	
	
	public static void main(String[] args){
		System.out.println(checkParameter("33",2));
	}

	/**
	 * 验证参数是否有特殊字符
	 * 
	 * @param str
	 *            待验证的字符串
	 * @param type
	 *            验证类型 1：不能输入特殊字符 2：不能输入特殊字符+中文
	 * @return 没有特殊字符返回true
	 */
	public static boolean checkParameter(String str, int type) {

		boolean validate1 = false;
		boolean validate2 = false;
		// 1.正则表达式 验证 字符串只能包含 中文+英文+数字+下划线
		String pattern1 = "[\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\`\\-\\=\\_\\+\\<\\>\\?\\,\\.\\/\\|\\:\\\"\\;\\{\\}\\[\\]\\'\\\\【】~！￥……（）———·：“；‘《》？，。、]";
		
		Pattern pattern = Pattern.compile(pattern1);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find()) {
			validate1 = true;
		}
		// 2.正则表达式 验证 字符串只能包含 英文+数字+下划线
		Pattern chinesePattern = Pattern.compile("[\u4E00-\u9FA5]+");
		Matcher matcher2 = chinesePattern.matcher(str);
		if (!matcher2.find()) {
			validate2 = true;
		}

		if (type == 1) {
			// 不能输入特殊字符
			return validate1;
		} else if (type == 2) {
			// 不能输入特殊字符+中文
			return validate1 && validate2;
		} else {
			return false;
		}

	}

	/**
	 * 验证字符串是否合法
	 * 
	 * @param filePath
	 *            fileName
	 * @return boolean
	 * @Date 2018-08-27 15:49
	 * @author jwh
	 */
	public static boolean isValidString(String filePath) {
		boolean validate1 = false;
		boolean validate2 = false;
		// 1.正则表达式 验证 字符串只能包含 中文+英文+数字+下划线+.
		String pattern1 = "[\\+\\%\\&\\=\\`\\'\"\\ \\-\\~\\@\\#\\$\\^\\*\\(\\){\\}\\[\\]\\<\\>\\;（）【】！￥&……“”‘’；？、]";
		Pattern pattern = Pattern.compile(pattern1);
		Matcher matcher = pattern.matcher(filePath);
		if (!matcher.find()) {
			validate1 = true;
		}
		return validate1;
	}

	/**
	 * 验证文件名是否合法
	 * 
	 * @param fileName
	 *            fileName
	 * @return boolean
	 * @Date 2018-08-27 15:49
	 * @author jwh
	 */
	public static boolean isValidFileName(String fileName) {
		if (fileName == null || fileName.length() > 255) {
			return false;
		} else {
			return fileName.matches(
					"[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
		}
	}

	/**
	 * 验证文件路径是否合法
	 * 
	 * @param filePath
	 *            fileName
	 * @return boolean
	 * @Date 2018-08-27 15:49
	 * @author jwh
	 */
	public static boolean isValidFilePath(String filePath) {
		boolean validate1 = false;
		boolean validate2 = false;
		boolean validate3 = false;
		Map<String, String> strPath = new HashMap<String, String>();
		strPath.put("%2B", "");
		strPath.put("%20", "");
		strPath.put("%2F", "");
		strPath.put("%3F", "");
		strPath.put("%25", "");
		strPath.put("%23", "");
		strPath.put("%26", "");
		strPath.put("%30", "");
		strPath.put("%27", "");
		strPath.put("%26", "");
		strPath.put("%22", "");
		strPath.put("%28", "");
		strPath.put("%29", "");
		strPath.put("%2a", "");
		strPath.put("%2b", "");
		Set<String> aa = strPath.keySet();
		for (String sP : aa) {
			String pa = strPath.get(sP);
			filePath = filePath.replaceAll(sP, pa);
		}
		// 1.正则表达式 验证 字符串只能包含 中文+英文+数字+下划线+.
		String pattern0 = "[a-zA-Z]:((\\\\[0-9a-zA-Z_/.\u4e00-\u9fa5]*)*)|((\\/\\/[0-9a-zA-Z_./\u4e00-\u9fa5]*)*)";
		String pattern1 = "[\\+\\%\\&\\=\\`\\'\"\\ \\-\\~\\@\\#\\$\\^\\*\\(\\){\\}\\[\\]\\<\\>\\;（）【】！￥&……“”‘’；？、]";
		Pattern pattern = Pattern.compile(pattern1);
		Matcher matcher = pattern.matcher(filePath);
		if (!matcher.find()) {
			validate1 = true;
		}
		Pattern pattern2 = Pattern.compile(pattern0);
		Matcher matcher0 = pattern2.matcher(filePath);
		validate3 = matcher0.matches();
		// 2.不包含 ".." 符号
		if (filePath.indexOf("..") == -1) {
			validate2 = true;
		}
		return validate1 && validate2 && validate3;
	}

	/**
	 * cleanXSS
	 * 
	 * @param str
	 *            str
	 * @return String
	 * @Date 2018-08-27 15:49
	 * @author jwh
	 */
	public static String cleanXSS(String str) {
		if (str == null) {
			return str;
		}
		Map<String, String> xssMap = new HashMap<String, String>();
		// 防止输出有script的内容，执行恶意代码。
		// 含有script
		xssMap.put("[s|S][c|C][r|R][i|I][p|P][t|T]", "");
		// 含有space
		xssMap.put("[s|S][p|P][a|A][c|C][e|E]", "");
		// 含有style
		xssMap.put("[s|S][t|T][y|Y][l|L][e|E]", "");
		// 含有vbscript
		xssMap.put("[v|V][b|B][s|S][c|C][r|R][i|I][p|P][t|T]", "");
		// 含有JavaScript
		xssMap.put("[\\\"\\\'][\\s]*[j|J][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']", "\"\"");
		// 含有函数eval
		xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
		xssMap.put("<", "&lt;");
		xssMap.put(">", "&gt;");
		xssMap.put("&", "&amp;");
		xssMap.put("%", "");
		xssMap.put("\\(", "(");
		xssMap.put("\\)", ")");
		xssMap.put("\'", "&#x27;");
		xssMap.put("\"", "&quot;");
		xssMap.put("\\n", "");
		xssMap.put("[A|a][L|l][E|e][R|r][T|t]", "");
		xssMap.put("[O|o][N|n][E|e][R|r][R|r][O|o][R|r]", "");

		Set<String> ss = xssMap.keySet();
		for (String xss : ss) {
			String v = xssMap.get(xss);
			str = str.replaceAll(xss, v);
		}
		return str;
	}
}
