package org.springframework.samples.sockjs;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.TextWebSocketHandlerAdapter;

public class EchoHandler extends TextWebSocketHandlerAdapter {


	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		session.sendMessage(message);
	}

}
