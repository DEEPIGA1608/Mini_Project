package movie_ticket_booking.movie_booking;

public class Movie {
    private int movieId;
    private String movieName;
    private String genre;
    private String language;
    private int duration;
    private int pricePerPerson;
    private String movieTicketType;
    private String venue;

    // Constructors
    public Movie() {
    }

    public Movie(String movieName, String genre, String language, int duration, int pricePerPerson, String movieTicketType, String venue) {
        this.movieName = movieName;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.pricePerPerson = pricePerPerson;
        this.movieTicketType = movieTicketType;
        this.venue = venue;
    }

    // Getters and setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public String getMovieTicketType() {
        return movieTicketType;
    }

    public void setMovieTicketType(String movieTicketType) {
        this.movieTicketType = movieTicketType;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }


}
