package com.revature.p1.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AvatarServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//petapp code; needs to be updated when service files are finished
		StringBuilder uriString = new StringBuilder(req.getRequestURI()); // /pet-app1/hello/6
		uriString.replace(0, req.getContextPath().length()+1, ""); // hello/6
		
		// if there is a slash
		if (uriString.indexOf("/") != -1) {
			uriString.replace(0, uriString.indexOf("/")+1, ""); // 6
			
			PrintWriter writer = resp.getWriter();
			writer.write("Hello! :) Path variable: " + uriString.toString());
		} else {
			// gets the response body writer object so that we can write to the response body
			PrintWriter writer = resp.getWriter();
			writer.write("Hello! :)");
		}
	}
	//petapp code; needs to be updated when service files are finished
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// query/request parameters
		// localhost:8080/pet-app1/hello?language=en
		String language = req.getParameter("language");
		
		// this gets a reader that will read the HTTP request body
		BufferedReader reader = req.getReader();
	
		String requestBody = "";
		String line = "";
		while ((line=reader.readLine())!=null) {
			requestBody += line;
		}
		
		PrintWriter writer = resp.getWriter();
		
		if (language==null) language="";
		switch (language) {
		case "en":
			writer.write("Hello, " + requestBody + "! :)");
			break;
		case "fr":
			writer.write("Bonjour, " + requestBody + "! :)");
			break;
		case "es":
			writer.write("Hola, " + requestBody + "! :)");
			break;
		default:
			writer.write(requestBody + "! :)");
		}
	}
}
