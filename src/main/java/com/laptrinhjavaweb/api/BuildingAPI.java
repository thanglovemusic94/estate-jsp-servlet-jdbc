package com.laptrinhjavaweb.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.util.HttpUtil;

//@RestController
@WebServlet(urlPatterns = { "api-admin-building" })
public class BuildingAPI extends HttpServlet {

	private static final long serialVersionUID = -586755557016809661L;

	/*
	 * @PostMapping(value = { "api/building" })
	 * 
	 * public BuildingDTO saveBuilding(@RequestBody BuildingDTO model) { return
	 * model; }
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		// logic
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}

}
