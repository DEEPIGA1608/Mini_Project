package movie_ticket_booking.movie_booking;

import java.util.List;

public interface MovieDatabase {

    List<Movie> getAllMovies() throws Exception;
}
