package com.revature.p1.web.delegates;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.p1.web.models.Player;
import com.revature.p1.web.services.PlayerService;
import com.revature.p1.web.services.PlayerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PlayerDelegate implements FrontControllerDelegate {
	private PlayerService playerServ = new PlayerServiceImpl();
	//private AvatarService avatarServ = new AvatarServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getMethod();
		
		switch(method) {
		case "GET":
			get(req, resp);
			break;
		case "POST":
			post(req, resp);
			break;
		case "PUT":
			put(req, resp);
			break;
		case "DELETE":
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			break;
		default:
		}
	}
	public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			resp.getWriter().write(objMapper.writeValueAsString(playerServ.viewAllPlayers()));
		} else {
			try {
				int id = Integer.valueOf(path);
				Player player = playerServ.getPlayer(id);
				if (player!=null) {
					resp.getWriter().write(objMapper.writeValueAsString(player));
				} else {
					resp.sendError(404, "Player with that ID not found.");
				}
			} catch (NumberFormatException e) {
				resp.sendError(400, e.getMessage());
			}
		}
	}

	public void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			Player player = objMapper.readValue(req.getInputStream(), Player.class);
			if (player!=null) {
				player = playerServ.addPlayer(player);
				resp.getWriter().write(objMapper.writeValueAsString(player));
			} else {
				resp.sendError(400, "The request body was empty.");
			}
		} else {
			resp.sendError(400, "Cannot POST to this URI. Try sending the request to /pets.");
		}
	}
		
	public void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			resp.sendError(400, "Cannot PUT to this URI. Try sending the request to /pets/{id}.");
		} else {
			try {
				int id = Integer.valueOf(path);
				Player player = playerServ.getPlayer(id);
				if (player!=null) {
					player = objMapper.readValue(req.getInputStream(), Player.class);
					
					try {
						if (player==null) throw new RuntimeException();
						if (player.getId()==id) {
							player = playerServ.editPlayer(player);
							resp.getWriter().write(objMapper.writeValueAsString(player));
						} else {
							resp.sendError(409, "The ID in the URI did not match the ID in the body.");
						}
					} catch (MismatchedInputException | RuntimeException e) {
						resp.sendError(400, "The request body was empty.");
					}
				} else {
					resp.sendError(404, "Pet with that ID not found.");
				}
			} catch (NumberFormatException e) {
				resp.sendError(400, e.getMessage());
			}
		}
	}
}
