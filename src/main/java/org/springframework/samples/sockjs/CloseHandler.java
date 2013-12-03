package org.springframework.samples.sockjs;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class CloseHandler extends TextWebSocketHandler {


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		session.close();
	}

}
