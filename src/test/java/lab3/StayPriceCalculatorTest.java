package lab3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StayPriceCalculatorTest {

    private ReserveMyPark calculator;

    @BeforeEach
    void setUp() {
        calculator = new ReserveMyPark();
    }

    @Test
    void childGetsFiftyPercentOff() throws Exception {
        double price = calculator.calculateStayPrice(3, 10, false, false);
        assertEquals(75.0, price);
    }

    @Test
    void adultPaysFullPrice() throws Exception {
        double price = calculator.calculateStayPrice(2, 46, false, false);
        assertEquals(100.0, price);
    }

    @Test
    void seniorGetsTwentyPercentOff() throws Exception {
        double price = calculator.calculateStayPrice(4, 70, false, false);
        assertEquals(160.0, price);
    }

    // Boundary Value Analysis Tests

    @Test
    void minimumNightsAllowed() throws Exception {
        double price = calculator.calculateStayPrice(1, 10, false, false);
        assertEquals(25.0, price);
    }

    @Test
    void maximumNightsAllowed() throws Exception {
        double price = calculator.calculateStayPrice(14, 70, false, false);
        assertEquals(560.0, price);
    }

    @Test
    void nightsBelowMinimumThrowsException() {
        assertThrows(NightReservationException.class, () ->
                calculator.calculateStayPrice(0, 30, false, false)
        );
    }

    @Test
    void nightsAboveMaximumThrowsException() {
        assertThrows(NightReservationException.class, () ->
                calculator.calculateStayPrice(20, 55, false, false)
        );
    }

    @Test
    void childAdultBoundaryAtTwelve() throws Exception {
        double price = calculator.calculateStayPrice(4, 12, false, false);
        assertEquals(100.0, price);
    }

    @Test
    void adultStartsAtThirteen() throws Exception {
        double price = calculator.calculateStayPrice(2, 13, false, false);
        assertEquals(100.0, price);
    }

    @Test
    void adultSeniorBoundaryAtSixtyFour() throws Exception {
        double price = calculator.calculateStayPrice(4, 64, false, false);
        assertEquals(200.0, price);
    }

    @Test
    void seniorStartsAtSixtyFive() throws Exception {
        double price = calculator.calculateStayPrice(3, 65, false, false);
        assertEquals(120.0, price);
    }

    // Decision Table Tests

    @Test
    void noDiscountsApplied() throws Exception {
        double price = calculator.calculateStayPrice(4, 40, false, false);
        assertEquals(200.0, price);
    }

    @Test
    void residentGetsTenDollarsOff() throws Exception {
        double price = calculator.calculateStayPrice(4, 30, true, false);
        assertEquals(190.0, price);
    }

    @Test
    void veteranGetsTenPercentOff() throws Exception {
        double price = calculator.calculateStayPrice(4, 60, false, true);
        assertEquals(180.0, price);
    }

    @Test
    void residentAndVeteranDiscountAppliedInCorrectOrder() throws Exception {
        double price = calculator.calculateStayPrice(4, 23, true, true);
        assertEquals(171.0, price);
    }
}


