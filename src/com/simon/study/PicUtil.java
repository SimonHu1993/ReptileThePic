package com.simon.study;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PicUtil {
	public static void getPic(String url,String socketId,String folder){
    	Connection conn = Jsoup
				.connect(url);
		Document doc;
		try {
			doc = conn.get();
			Elements elements = doc.getElementsByTag("img");
			int i = 0;
			for (Element element : elements) {
				String temUrl = element.attr("src");
				String fileName= String.valueOf(System.currentTimeMillis())+temUrl.substring(temUrl.lastIndexOf("."));
//				String fileName= element.attr("title")+temUrl.substring(temUrl.lastIndexOf("."));
			   DownImg thread =new DownImg(folder, fileName, temUrl, socketId);
			   thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static void main(String[] args) throws IOException {
		Connection conn = Jsoup
				.connect("https://www.dbmeinv.com/dbgroup/show.htm?cid=4&pager_offset=5");
		Document doc = conn.get();
		Elements elements = doc.getElementsByTag("img");
		int i = 0;
		for (Element element : elements) {
			String temUrl = element.attr("src");
			Response response = Jsoup.connect(temUrl).ignoreContentType(true)
					.execute();
			FileOutputStream out = new FileOutputStream(new File("c:/"
					+ element.attr("title") + i + ".jpg"));
			i++;
			out.write(response.bodyAsBytes());
			out.close();

		}
	}
}

class DownImg extends Thread {
	private String folder;
	private String fileName;
	private String url;
	private String socketId;
    
	public DownImg(String folder, String fileName, String url, String socketId) {
		this.folder = folder;
		this.fileName = fileName;
		this.url = url;
		this.socketId = socketId;
	}
 
	public void run() {
		try {
			Response response = Jsoup.connect(url).ignoreContentType(true)
					.execute();
			FileOutputStream out = new FileOutputStream(new File(folder + "/"
					+ fileName));
			out.write(response.bodyAsBytes());
			out.close();
			PicServer.webSocket.get(socketId).sendMessage("imgs/"+fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
