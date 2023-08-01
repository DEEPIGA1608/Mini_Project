package movie_ticket_booking.movie_booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
abstract class Abclass{
	abstract  public void insertMovie(Movie movie) throws Exception ;
}
public class DatabaseManager extends Abclass implements MovieDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movie";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "QW@12erty";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void insertMovie(Movie movie) throws Exception {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO movie (movie_name, genre, lang, duration, price_per_person, movieticket_type, venue) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, movie.getMovieName());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getLanguage());
                preparedStatement.setInt(4, movie.getDuration());
                preparedStatement.setInt(5, movie.getPricePerPerson());
                preparedStatement.setString(6, movie.getMovieTicketType());
                preparedStatement.setString(7, movie.getVenue());

                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int movieId = generatedKeys.getInt(1);
                        movie.setMovieId(movieId);
                    } else {
                        throw new SQLException("Failed to get the movie ID after insertion.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred while adding movie details to the database: " + e.getMessage());
        }
    }

    @Override
    public List<Movie> getAllMovies() throws Exception {
        List<Movie> movieList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM movie";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setMovieId(resultSet.getInt("movie_id"));
                        movie.setMovieName(resultSet.getString("movie_name"));
                        movie.setGenre(resultSet.getString("genre"));
                        movie.setLanguage(resultSet.getString("lang"));
                        movie.setDuration(resultSet.getInt("duration"));
                        movie.setPricePerPerson(resultSet.getInt("price_per_person"));
                        movie.setMovieTicketType(resultSet.getString("movieticket_type"));
                        movie.setVenue(resultSet.getString("venue"));

                        movieList.add(movie);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred while retrieving movie details from the database: " + e.getMessage());
        }

        return movieList;
    }


    public Movie getMovieByName(String movieName) throws Exception {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM movie WHERE movie_name = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, movieName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setMovieId(resultSet.getInt("movie_id"));
                        movie.setMovieName(resultSet.getString("movie_name"));
                        movie.setGenre(resultSet.getString("genre"));
                        movie.setLanguage(resultSet.getString("lang"));
                        movie.setDuration(resultSet.getInt("duration"));
                        movie.setPricePerPerson(resultSet.getInt("price_per_person"));
                        movie.setMovieTicketType(resultSet.getString("movieticket_type"));
                        movie.setVenue(resultSet.getString("venue"));

                        return movie;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred while retrieving movie details from the database: " + e.getMessage());
        }

        return null;
    }
    public User getUserByName(String userName) throws Exception {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM user WHERE user_name = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));

                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred while retrieving user details from the database: " + e.getMessage());
        }

        return null;
    }
    public int insertUser(User user) throws Exception {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO user (user_name) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getUserName());

                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        user.setUserId(userId);
                        return userId;
                    } else {
                        throw new SQLException("Failed to get the user ID after insertion.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred while adding user details to the database: " + e.getMessage());
        }
    }

   

    public void insertTicket(Ticket ticket) throws Exception {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO ticket (no_of_seats, total_amount, price_per_person, user_id) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ticket.getNo_of_seats());
                preparedStatement.setInt(2, ticket.getTotalAmount());
                preparedStatement.setInt(3, ticket.getPricePerPerson());
                preparedStatement.setInt(4, ticket.getUserId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred while adding ticket details to the database: " + e.getMessage());
        }
    }


}
