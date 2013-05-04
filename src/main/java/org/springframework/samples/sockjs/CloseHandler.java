package org.springframework.samples.sockjs;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.TextWebSocketHandlerAdapter;

public class CloseHandler extends TextWebSocketHandlerAdapter {


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		session.close();
	}

}
