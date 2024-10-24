package com.wad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wad.dao.MovieDAO;
import com.wad.dao.RatingDAO;
import com.wad.model.Movie;
import com.wad.model.Rating;
import com.wad.model.User;

@WebServlet("/api/ratings/*")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final RatingDAO ratingDAO = new RatingDAO();
	private final MovieDAO movieDAO = new MovieDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		try {
			Long movieId = Long.parseLong(req.getParameter("movieId"));
			double ratingValue = Double.parseDouble(req.getParameter("rating"));

			if (ratingValue < 1 || ratingValue > 5) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Rating must be between 1 and 5");
				return;
			}

			Movie movie = movieDAO.getMovie(movieId);
			if (movie == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
				return;
			}

			Rating rating = new Rating();
			rating.setMovie(movie);
			rating.setUser((User) session.getAttribute("user"));
			rating.setValue(ratingValue);

			ratingDAO.saveRating(rating);
			resp.setStatus(HttpServletResponse.SC_CREATED);
		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
		}
	}

}
