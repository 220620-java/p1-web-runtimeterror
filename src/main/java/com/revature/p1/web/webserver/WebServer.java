package com.revature.p1.web.webserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class WebServer {
	private static final WebServer WEBSERVER = new WebServer();
	private HttpServer httpServer;
	
	// public static getter method
		public static WebServer getWebServer() {
			return WEBSERVER;
		}
		
		// private constructor
		private WebServer() {
			try {
				// initialize the server on port 8080 (typical dev http port)
				httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
				
				// set up thread pool for the server with 8 threads in the pool
				httpServer.setExecutor(Executors.newFixedThreadPool(8));
				
				// create contexts (endpoints that you can send http requests to)
				// this code will respond when a request comes in for the address
				// "localhost:8080/hello"
				httpServer.createContext("/hello", http -> {
					String responseText = 
							"Response from thread: " 
							+ Thread.currentThread().getName();
					
					// set http response header for content type
					http.getResponseHeaders().set("Content-Type", "text/html");
					// send the initial headers so the browser can prepare:
					// this includes the http status code (200: OK) and the
					// length of the response body
					http.sendResponseHeaders(200, responseText.length());
					// then, we can set the response body
					http.getResponseBody().write(responseText.getBytes());
				});
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void start() {
			httpServer.start();
			System.out.println("Web server is listening at http://localhost:8080/");
		}
		
		public void shutdown() {
			System.out.println("Shutting down web server.");
			// waits for a max of 5 seconds before closing connections
			httpServer.stop(5);
		}
}
