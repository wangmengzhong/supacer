package com.wmz.web.util;

import java.util.ArrayList;
import java.util.List;

public class FileCleanUtil implements Runnable {

	private List<Object> list;

	private String oldContent;

	private String newContent;

	public FileCleanUtil(String oldContent, String newContent) {
		this.oldContent = oldContent;
		this.newContent = newContent;
	}

	public void run() {

	}

	public static void main(String[] args) {
		String con = "uuu<img src=\"/web/rest/blog/getImage?imgPath=5896417725102816.jpg\" />ddd<img src=\"/web/rest/blog/getImage?imgPath=9896417725102816.jpg\" />";

		int ind = con.indexOf("<img src=\"/web/rest/blog/getImage?imgPath=");

		System.out.println(con.substring(ind, ind + 66));
		System.out.println(con.substring(0, ind) + con.substring(ind + 66));

	}

	private static List<String> getImages(String content) {

		List<String> images = new ArrayList<String>();

		while (content.indexOf("<img src=\"/web/rest/blog/getImage?imgPath=") > 0) {
			int ind = content.indexOf("<img src=\"/web/rest/blog/getImage?imgPath=");

			images.add(content.substring(ind, ind + 66));
			content = content.substring(0, ind) + content.substring(ind + 66);
		}

		return images;
	}

}
