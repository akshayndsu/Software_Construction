package Encryption;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class EncryptionMachineTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream originalIn = null;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        if (originalIn != null) {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testIntroduction() {
        EncryptionMachine.Introduction();
        String output = outContent.toString().trim();
        assertTrue(output.contains("Welcome to the CSCI717 Encryption Machine Construction"));
        assertTrue(output.contains("The program lets you encrypt a message"));
    }

    @Test
    public void testEncryptSingleLetter_BasicShift() {
        // Test basic shifts
        assertEquals('d', EncryptionMachine.encryptSingleLetter('a'));
        assertEquals('e', EncryptionMachine.encryptSingleLetter('b'));
        assertEquals('f', EncryptionMachine.encryptSingleLetter('c'));
    }

    @Test
    public void testEncryptSingleLetter_WrappingAround() {
        // Test wrapping around the alphabet
        assertEquals('a', EncryptionMachine.encryptSingleLetter('x'));
        assertEquals('b', EncryptionMachine.encryptSingleLetter('y'));
        assertEquals('c', EncryptionMachine.encryptSingleLetter('z'));
    }

    @Test
    public void testEncryptWord_SingleWord() {
        // Test single word encryption
        String result = EncryptionMachine.encryptWord("hello");
        assertTrue(result.contains("has been encrypted to: khoor"));
    }

    @Test
    public void testEncryptWord_EmptyString() {
        // Test empty string
        String result = EncryptionMachine.encryptWord("");
        assertTrue(result.contains("has been encrypted to: "));
    }

    @Test
    public void testEncryptMessage_SingleWord() {
        // Simulate user input for single word message
        String input = "test\n1\nhello\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        Scanner scanner = new Scanner(System.in);
        EncryptionMachine.encryptMessage(scanner);
        
        String output = outContent.toString();
        assertTrue(output.contains("Enter key:"));
        assertTrue(output.contains("has been encrypted to: whvw")); // "test" encrypted
        assertTrue(output.contains("has been encrypted to: khoor")); // "hello" encrypted
        assertTrue(output.contains("Message fully encrypted"));
    }

    @Test
    public void testEncryptMessage_MultipleWords() {
        // Simulate user input for multiple words
        String input = "key\n3\nfirst\nsecond\nthird\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        Scanner scanner = new Scanner(System.in);
        EncryptionMachine.encryptMessage(scanner);
        
        String output = outContent.toString();
        assertTrue(output.contains("has been encrypted to: nhb")); // "key" encrypted
        assertTrue(output.contains("has been encrypted to: iluvw")); // "first" encrypted
        assertTrue(output.contains("has been encrypted to: vhfrqg")); // "second" encrypted
        assertTrue(output.contains("has been encrypted to: wklug")); // "third" encrypted
    }

    @Test
    public void testConstants() {
        // Test that constants are correctly defined
        assertEquals("abcdefghijklmnopqrstuvwxyz", EncryptionMachine.ALPHABET);
        assertEquals(3, EncryptionMachine.SHIFT);
    }

    @Test
    public void testEncryptWord_AllLetters() {
        // Test encryption of all alphabet letters
        String result = EncryptionMachine.encryptWord("abcdefghijklmnopqrstuvwxyz");
        assertTrue(result.contains("has been encrypted to: defghijklmnopqrstuvwxyzabc"));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testEncryptSingleLetter_InvalidCharacter() {
        // Test handling of characters not in the alphabet
        EncryptionMachine.encryptSingleLetter('!');
    }

    @Test
    public void testEncryptMessage_ZeroWords() {
        // Test handling of zero words
        String input = "key\n0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        Scanner scanner = new Scanner(System.in);
        EncryptionMachine.encryptMessage(scanner);
        
        String output = outContent.toString();
        assertTrue(output.contains("Message fully encrypted"));
    }
}
