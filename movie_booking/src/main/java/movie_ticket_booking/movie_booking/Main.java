package movie_ticket_booking.movie_booking;

import java.util.Scanner;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();

        System.out.println("Welcome to Movie Ticket Booking!");

        System.out.print("Enter 'admin' to access movie details or 'user' to view movie list: ");
        String userType = scanner.nextLine();

        if ("admin".equalsIgnoreCase(userType)) {
        	System.out.print("Enter the movie name: ");
            String movieName = scanner.nextLine();

            System.out.print("Enter the movie genre: ");
            String genre = scanner.nextLine();

            System.out.print("Enter the movie language: ");
            String language = scanner.nextLine();

            System.out.print("Enter the movie duration in minutes: ");
            int duration = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer

            System.out.print("Enter the price per person: ");
            int pricePerPerson = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer

            System.out.print("Enter the movie ticket type: ");
            String movieTicketType = scanner.nextLine();

            System.out.print("Enter the venue: ");
            String venue = scanner.nextLine();

            // Code to insert data into the database using dbManager.
            // For example:
            try {
                // Create a Movie object with the collected movie details
                Movie movie = new Movie();
                movie.setMovieName(movieName);
                movie.setGenre(genre);
                movie.setLanguage(language);
                movie.setDuration(duration);
                movie.setPricePerPerson(pricePerPerson);
                movie.setMovieTicketType(movieTicketType);
                movie.setVenue(venue);

                // Code to insert the movie into the database using dbManager
                dbManager.insertMovie(movie);

                System.out.println("Movie details added successfully!");
            } catch (Exception e) {
                System.out.println("Error occurred while adding movie details: " + e.getMessage());
            }

        } else if ("user".equalsIgnoreCase(userType)) {
            // User Section: View Movie List and Book Tickets
        	
        	            System.out.println("\nMenu:");
        	            System.out.println("1. Show all movies");
        	            System.out.println("2. Search a particular movie");
        	            System.out.println("3. Exit");

        	            System.out.print("Enter your choice (1/2/3): ");
        	            int choice = scanner.nextInt();
        	            scanner.nextLine(); // Consume the newline character after reading the integer

        	            switch (choice) {
        	                case 1:
        	                    try {
        	                        List<Movie> movieList = dbManager.getAllMovies();
        	                        if (movieList.isEmpty()) {
        	                            System.out.println("No movies available.");
        	                        } else {
        	                            System.out.println("List of Movies:");
        	                            for (Movie movie : movieList) {
        	                                System.out.println("- " + movie.getMovieName());
        	                            }
        	                        }
        	                    } catch (Exception e) {
        	                        System.out.println("Error occurred: " + e.getMessage());
        	                    }
        	                    break;

        	                case 2:
        	                    System.out.print("Enter the movie name to get details: ");
        	                    String selectedMovie = scanner.nextLine();

        	                    try {
        	                        Movie movie = dbManager.getMovieByName(selectedMovie);
        	                        if (movie == null) {
        	                            System.out.println("Movie not found.");
        	                        } else {
        	                            System.out.println("Movie Details:");
        	                            System.out.println("Name: " + movie.getMovieName());
        	                            System.out.println("Genre: " + movie.getGenre());
        	                            System.out.println("Language: " + movie.getLanguage());
        	                            System.out.println("Duration: " + movie.getDuration() + " minutes");
        	                            System.out.println("Price per Person: " + movie.getPricePerPerson());
        	                            
        	                            System.out.print("Do you wish to book tickets? (yes/no): ");
        	                            String bookTicketsChoice = scanner.nextLine();
        	                            if ("yes".equalsIgnoreCase(bookTicketsChoice)) {
        	                            	System.out.print("Enter your username: ");
        	                                String userName = scanner.nextLine();
        	                            	User user = dbManager.getUserByName(userName);
        	                                if (user == null) {
        	                                    // If the user doesn't exist, create a new User object and insert it into the database
        	                                    user = new User();
        	                                    user.setUserName(userName);
        	                                    dbManager.insertUser(user);
        	                                }

        	                                // Display the user ID
        	                                System.out.println("Hello, " + user.getUserName() + "! Your User ID is: " + user.getUserId());
        	                                System.out.print("Enter the number of seats to book: ");
        	                                int numSeats = scanner.nextInt();
        	                                scanner.nextLine(); // Consume the newline character after reading the integer

        	                                int totalAmount = movie.getPricePerPerson() * numSeats;
        	                                System.out.println("Total Amount: " + totalAmount + " USD");
        	                                Ticket ticket = new Ticket();
        	                                ticket.setNo_of_seats(numSeats);
        	                                ticket.setPricePerPerson(movie.getPricePerPerson()); // Set the pricePerPerson attribute
        	                                ticket.setTotalAmount(totalAmount);
        	                                ticket.setUserId(user.getUserId());
        	                                dbManager.insertTicket(ticket);
        	                                System.out.println(numSeats + " Tickets Booked Successfully!!");

        	                            }
        	                        }
        	                    } catch (Exception e) {
        	                        System.out.println("Error occurred: " + e.getMessage());
        	                    }
        	                    break;

        	                case 3:
        	                    System.out.println("Thank you for using Movie Ticket Booking. Goodbye!");
        	                    scanner.close();
        	                    System.exit(0);
        	                    break;

        	                default:
        	                    System.out.println("Invalid choice. Please try again.");
        	                    break;
        	            }
        	        }
        	    else {
            System.out.println("Invalid user type. Exiting...");
        }

        scanner.close();
    }
}
