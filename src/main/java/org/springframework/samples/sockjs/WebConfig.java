package org.springframework.samples.sockjs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
			.addHandler(echoHandler(), "/echo", "/cookie_needed_echo")
			.addHandler(closeHandler(), "/close")
			.withSockJS()
			.setStreamBytesLimit(4096)
			.setSessionCookieNeeded(true);
		registry
			.addHandler(echoHandler(), "/disabled_websocket_echo")
			.withSockJS()
			.setWebSocketEnabled(false);
	}

	@Bean
	public EchoHandler echoHandler() {
		return new EchoHandler();
	}

	@Bean
	public CloseHandler closeHandler() {
		return new CloseHandler();
	}

}
