package com.wad.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wad.dao.MovieDAO;
import com.wad.model.Movie;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final MovieDAO movieDAO = new MovieDAO();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Movie> movies = movieDAO.getAllMovies();
		resp.setContentType("application/json");
		objectMapper.writeValue(resp.getOutputStream(), movies);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Movie movie = new Movie();
		movie.setName(req.getParameter("name"));
		movie.setDescription(req.getParameter("description"));
		System.out.println("hrllo");
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			movie.setReleaseDate(dateFormat.parse(req.getParameter("releaseDate")));
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
			return;
		}

		movieDAO.saveMovie(movie);
		resp.setStatus(HttpServletResponse.SC_CREATED);
		req.getSession().setAttribute("successMessage", "Movie successfully added!");
		resp.sendRedirect("admin.jsp");
		
	}

}
