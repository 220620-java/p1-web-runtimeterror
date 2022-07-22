package com.revature.p1.web.delegates;

import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.services.AvatarServImpl;
import com.revature.p1.web.services.AvatarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AvatarDelegate implements FrontControllerDelegate {
	private AvatarService avatarServ = new AvatarServImpl();
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
			try {
				post(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "PUT":
			put(req, resp);
			break;
		case "DELETE":
			delete(req, resp);
			break;
		default:
		}
	}
	public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			resp.getWriter().write(objMapper.writeValueAsString(avatarServ.findAll()));
		} else {
			try {
				int id = Integer.valueOf(path);
				Avatar avatar = avatarServ.findById(id);
				if (avatar!=null) {
					resp.getWriter().write(objMapper.writeValueAsString(avatar));
				} else {
					resp.sendError(404, "Avatar with that ID not found.");
				}
			} catch (NumberFormatException e) {
				resp.sendError(400, e.getMessage());
			}
		}
	}

	public void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			Avatar avatar = objMapper.readValue(req.getInputStream(), Avatar.class);
			if (avatar!=null) {
				avatar = avatarServ.create(avatar);
				resp.getWriter().write(objMapper.writeValueAsString(avatar));
			} else {
				resp.sendError(400, "The request body was empty.");
			}
		} else {
			resp.sendError(400, "Cannot POST to this URI. Try sending the request to /avatars.");
		}
	}
		
	public void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			resp.sendError(400, "Cannot PUT to this URI. Try sending the request to /pets/{id}.");
		} else {
			try {
				int id = Integer.valueOf(path);
				Avatar avatar = avatarServ.findById(id);
				if (avatar!=null) {
					avatar = objMapper.readValue(req.getInputStream(), Avatar.class);
					
					try {
						if (avatar==null) throw new RuntimeException();
						if (avatar.getId()==id) {
							avatarServ.update(avatar);
							avatar = avatarServ.findById(id);
							resp.getWriter().write(objMapper.writeValueAsString(avatar));
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
	
	public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//need more
	}
}
