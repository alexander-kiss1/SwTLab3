package lab3;

public class NightReservationException extends Exception {
    public NightReservationException(String message) {
        super(message);
    }

    public NightReservationException(int nights) {
        super("Invalid number of nights: " + nights);
    }
}
