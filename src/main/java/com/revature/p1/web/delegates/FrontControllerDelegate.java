package com.revature.p1.web.delegates;

import java.io.IOException;

import com.revature.p1.web.exceptions.TradeAlreadyExistsException;
import com.revature.p1.web.exceptions.UsernameAlreadyExistsException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface FrontControllerDelegate {
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, UsernameAlreadyExistsException, TradeAlreadyExistsException;
}
