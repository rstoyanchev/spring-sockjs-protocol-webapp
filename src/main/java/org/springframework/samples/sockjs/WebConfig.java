package org.springframework.samples.sockjs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.socket.sockjs.support.DefaultSockJsService;
import org.springframework.web.socket.sockjs.support.SockJsHttpRequestHandler;

@Configuration
public class WebConfig {


	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		DefaultSockJsService mainService = new DefaultSockJsService(sockJsTaskScheduler());
		mainService.setValidSockJsPrefixes("/echo", "/close");
		mainService.setStreamBytesLimit(4096);

		DefaultSockJsService anotherService = new DefaultSockJsService(sockJsTaskScheduler());
		anotherService.setValidSockJsPrefixes("/disabled_websocket_echo");
		anotherService.setWebSocketsEnabled(false);

		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("/echo/**", new SockJsHttpRequestHandler(mainService, new EchoHandler()));
		urlMap.put("/close/**", new SockJsHttpRequestHandler(mainService, new CloseHandler()));
		urlMap.put("/disabled_websocket_echo/**", new SockJsHttpRequestHandler(anotherService, new EchoHandler()));
		urlMap.put("/cookie_needed_echo/**", new SockJsHttpRequestHandler(mainService, new EchoHandler()));

		SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();
		hm.setUrlMap(urlMap);

		return hm;
	}

	@Bean
	public ThreadPoolTaskScheduler sockJsTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadNamePrefix("SockJS-");
		return taskScheduler;
	}

}
