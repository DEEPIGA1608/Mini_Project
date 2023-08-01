package movie_ticket_booking.movie_booking;

public class Ticket {
    private int ticketId;
    private int no_of_seats;
    private int totalAmount;
    private int pricePerPerson;
    private int userId; // New attribute to store the user ID

    // Constructors
    public Ticket() {
    }

    public Ticket(int no_of_seats, int totalAmount, int pricePerPerson) {
        this.no_of_seats = no_of_seats;
        this.totalAmount = totalAmount;
        this.pricePerPerson = pricePerPerson;
    }

    // Getters and setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getNo_of_seats() {
        return no_of_seats;
    }

    public void setNo_of_seats(int no_of_seats) {
        this.no_of_seats = no_of_seats;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
