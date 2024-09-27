package Encryption;
import java.util.Scanner;

	/**
	 * EncryptionMachine
	 * 
	 * This program implements a Caesar cipher for simple text encryption. It is based on the caesarian cipher where it encrypts 
	 * a user-provided key and message by shifting each letter(SHIFT) a fixed number of positions 
	 * in the alphabet (3 by default). The encrypted key and message are then displayed.
	 * 
	 * The program demonstrates basic encryption using substitution ciphers, working 
	 * with user input, and applying constants and methods effectively in Java.
	 * 
	 * Course: CSCI 717 - Software Construction
	 * Assignment: 1
	 * 
	 * Author: Akshay Jadhav
	 * Date: September 30, 2024
	 */
	public class EncryptionMachine {
		
		public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	    public static final int SHIFT = 3;
	    
	    /**
	     * The encryptSingleLetter method encrypts single letter entered by users
		 * @param letter  single letter entered by users for encryption
		 * @return cipherLetter returns the encrypted letter
		 */
	    public static void main(String[] args) {
	    	Introduction();

	    	Scanner Obj = new Scanner(System.in);
	    	
	        encryptMessage(Obj);
	        
	    }
	    /**
	     * Displays a welcome message introducing the user to the program.
	     * It explains the program's purpose and provides context for what the user will be doing.
	     */
	    public static void Introduction() {
	        System.out.println("Welcome to the CSCI717 Encryption Machine Construction");
	        System.out.println("The program lets you encrypt a message with a key for your recipient to decrypt!");
	    }
	    public static char encryptSingleLetter(char letter) {

	        int letterIndex = ALPHABET.indexOf(letter);

	        int shiftedletterIndex = (letterIndex + SHIFT) % 26;

	        char cipher = ALPHABET.charAt(shiftedletterIndex);

	        return cipher;
	        
	    }
	    
	    /**
	     * This is encryptWord method encrypts word entered by users
		 * @param word  the word the user enters for encryption
		 * @return String that includes the string "has been encrypted to" and the returned encrypted word
		 */
	    public static String encryptWord(String word) {

	        StringBuffer result = new StringBuffer();

	        for (int i = 0; i < word.length(); i++) {

	            char cipherword = encryptSingleLetter(word.charAt(i));

	            result.append(cipherword);

	        }

	        return ("has been encrypted to: " + result);     
	    }
	    
	    /**
	     * The encryptMessage method implements the welcome message, user message encryption
	     * and encryption completed message.
		 * @param obj accepts user input 
		 * @return void 
		 */
	    public static void encryptMessage(Scanner obj) {
	    	
	        
	       
	        System.out.println("Enter key: ");

	    	String key = obj.next(); 
	        
			String cypherKey = encryptWord(key);
			
			System.out.println("\"" + key + "\" " + cypherKey + "\n");
	    	
	        System.out.println("How many words is your message?:");
	       
	        String number = obj.next();
	        
	        int numberOfWords = Integer.parseInt(number);

	        for (int i = 0; i < numberOfWords; i++) {
	        	
	        	System.out.println("Next word:");
	        	
	            String plaintext = obj.next();  

	            String cipherword = encryptWord(plaintext);

	            System.out.println("\"" + plaintext + "\" " + cipherword + "\n");

	        }
	        
	        System.out.println("Message fully encrypted. Happy secret messaging!");
	    }


	}

