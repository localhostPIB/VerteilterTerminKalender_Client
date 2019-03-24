package VerteilterTerminKalender.validators;

import org.junit.jupiter.api.Test;

import static VerteilterTerminKalender.validators.RegisterValidator.isEmail;
import static VerteilterTerminKalender.validators.StringValidator.isTimeFormatted;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testclass for the methods of class StringValidator
 *
 * @author Michelle Blau
 */

class StringValidatorTest {

    /**
     * Tests method 'isTimeFormatted()' by passing it an empty String-Object
     * Expected return value: false
     */
    @Test
    void testIsTimeFormattedEmpty() {
        boolean result = isTimeFormatted("");
        assertEquals(false, result);
    }

    /**
     * Tests method 'isTimeFormatted()' by passing it "33:55"
     * Expected return value: false
     */
    @Test
    void testIsTimeFormattedIncorrect1() {
        boolean result = isTimeFormatted("33:55");
        assertEquals(false, result);
    }

    /**
     * Tests method 'isTimeFormatted()' by passing it "23:59"
     * Expected return value: true
     */
    @Test
    void testIsTimeFormattedCorrect1() {
        boolean result = isTimeFormatted("23:59");
        assertEquals(true, result);
    }

    /**
     * Tests method 'isTimeFormatted()' by passing it "00:59"
     * Expected return value: true
     */
    @Test
    void testIsTimeFormattedCorrect2() {
        boolean result = isTimeFormatted("00:59");
        assertEquals(true, result);
    }

    /**
     * Tests method 'isTimeFormatted()' by passing it "00:00"
     * Expected return value: true
     */
    @Test
    void testIsTimeFormattedCorrect3() {
        boolean result = isTimeFormatted("00:00");
        assertEquals(true, result);
    }

    /**
     * Tests method 'isTimeFormatted()' by passing it "3:55"
     * Expected return value: false
     */
    @Test
    void testIsTimeFormattedIncorrect2() {
        boolean result = isTimeFormatted("3:55");
        assertEquals(false, result);
    }
}