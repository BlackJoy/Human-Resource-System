package com.sys.hr.train.util;

public class ParseHtml {
public static String htmlspecialchars(String str) {
		
		str = str.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("\"", "&quot;")
				.replaceAll("'", "&apos;");
		return str;
	}
}
