package VerteilterTerminKalender.validators;

import org.junit.jupiter.api.Test;

import static VerteilterTerminKalender.validators.RegisterValidator.hasEnoughCharacters;
import static VerteilterTerminKalender.validators.RegisterValidator.isEmail;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testclass for the methods of class RegisterValidator
 *
 * @author Michelle Blau
 */

class RegisterValidatorTest {

    /**
     * Tests method 'isEmail()' by passing it an empty String-Object
     * Expected return value: false
     */
    @Test
    void TestIsEmailEmpty() {
        boolean result = isEmail("");
        assertEquals(false, result);
    }

    /**
     * Tests method 'isEmail()' by passing it an email: Härry_Pötter@web.de
     * Expected return value: true
     */
    @Test
    void TestIsEmailTrue() {
        boolean result = isEmail("Härry_Pötter@web.de");
        assertEquals(true, result);

    }

    /**
     * Tests method 'isEmail()' by passing it an email: H@rr%yPö$tter@web.de
     * Expected return value: false
     */
    @Test
    void TestIsEmailFalse() {
        boolean result = isEmail("H@rr%yPö$tter@web.de");
        assertEquals(false, result);

    }

    /**
     * Tests method 'hasEnoughCharacters()' by passing it "Bilderrahmen"
     * Expected return value: true
     */
    @Test
    void hasEnoughCharactersTrue() {
        boolean result = hasEnoughCharacters("Bilderrahmen");
        assertEquals(true, result);
    }

    /**
     * Tests method 'hasEnoughCharacters()' by passing it "hi"
     * Expected return value: false
     */
    @Test
    void hasEnoughCharactersFalse() {
        boolean result = hasEnoughCharacters("hi");
        assertEquals(false, result);
    }
}