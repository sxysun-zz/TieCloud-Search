package com.tiecloud.web;

import java.io.IOException;

import com.tiecloud.model.Model;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String c = request.getParameter("search_text");
		String r = request.getParameter("range");
		
		Model e = new Model();
		String s = e.getCloud(c, r);
		
		request.setAttribute("location", s);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	}

}
