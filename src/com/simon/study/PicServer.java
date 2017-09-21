package com.simon.study;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 
 * @ClassName: PicServer
 * @Description:描述
 * @author: Simon
 * @date: 2017年9月20日 下午4:16:44
 */
@ServerEndpoint("/picServer")
public class PicServer {
	private  Session session;
	public static HashMap<String, PicServer> webSocket =new HashMap<String, PicServer>();
	@OnOpen
	public void onOpen(Session session){
		this.session=session;
		webSocket.put(session.getId(), this);
		System.out.println("有新连接接入！");
	}
	@OnClose
	public void onClose(Session session){
		webSocket.remove(this.session.getId());
		System.out.println("连接关闭！");
	}
	//客户端往服务端发送消息
	@OnMessage
	public void onMessage(String message,Session session){
		System.err.println("這是客戶端"+message+"請求");
		PicUtil.getPic(message,session.getId(),"D:\\OfficeAPP\\apache-tomcat-7.0.56-windows-i64\\apache-tomcat-7.0.56\\webapps\\TestAndStudy\\imgs");
		/*for(String key:webSocket.keySet()){
			try {
				webSocket.get(key).sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}*/
	}
	
	public synchronized void sendMessage(String message) throws IOException{
		System.err.println("這是服務器"+message+"請求");
	 this.session.getBasicRemote().sendText(message);
	}

}
