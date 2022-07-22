package com.revature.p1.web.webserver;

public class Driver {
	public static void main (String[] args) {
		WebServer webserver = WebServer.getWebServer();
		
		webserver.start();
	}
}
