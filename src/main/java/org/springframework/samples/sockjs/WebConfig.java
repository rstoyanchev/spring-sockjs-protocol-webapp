package org.springframework.samples.sockjs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.socket.sockjs.SockJsException;
import org.springframework.web.socket.sockjs.SockJsHttpRequestHandler;
import org.springframework.web.socket.sockjs.transport.handler.DefaultSockJsService;

@Configuration
public class WebConfig {


	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		DefaultSockJsService mainService = new DefaultSockJsService(sockJsTaskScheduler());
		mainService.setValidSockJsPrefixes("/echo", "/close", "/cookie_needed_echo");
		mainService.setStreamBytesLimit(4096);
		mainService.setDummySessionCookieEnabled(true);

		DefaultSockJsService anotherService = new DefaultSockJsService(sockJsTaskScheduler());
		anotherService.setValidSockJsPrefixes("/disabled_websocket_echo");
		anotherService.setWebSocketsEnabled(false);

		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("/echo/**", new SockJsHttpRequestHandler(mainService, new EchoHandler()));
		urlMap.put("/close/**", new SockJsHttpRequestHandler(mainService, new CloseHandler()));
		urlMap.put("/cookie_needed_echo/**", new SockJsHttpRequestHandler(mainService, new EchoHandler()));
		urlMap.put("/disabled_websocket_echo/**", new SockJsHttpRequestHandler(anotherService, new EchoHandler()));

		SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();
		hm.setUrlMap(urlMap);

		return hm;
	}

	@Bean
	public HandlerExceptionResolver exceptionResolver() {
		return new SockJsExceptionResolver();
	}

	@Bean
	public ThreadPoolTaskScheduler sockJsTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadNamePrefix("SockJS-");
		return taskScheduler;
	}


	private static class SockJsExceptionResolver extends DefaultHandlerExceptionResolver {

		public ModelAndView doResolveException(HttpServletRequest rq, HttpServletResponse rs, Object h, Exception e) {
			if (e instanceof SockJsException) {
				rs.setStatus(500);
				return new ModelAndView();
			}
			return super.doResolveException(rq, rs, h, e);
		}
	}

}
