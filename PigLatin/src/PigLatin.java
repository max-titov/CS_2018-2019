
//name:      date:  
import java.util.*;
import java.io.*;

public class PigLatin {
	public static void main(String[] args) {
		part_1_using_pig();
		// part_2_using_piglatenizeFile();
	}

	public static void part_1_using_pig() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("\nWhat word? ");
			String s = sc.next();
			if (s.equals("-1"))
				System.exit(0);
			String p = pig(s);
			System.out.println(p);
		}
	}

	public static String pig(String s) {
		if (s.length() == 0) { // if the remaining string contains no letters
			return "****INVALID****";
		}

		String ending = "";// ending to add to word at the end

		String beforePunct = ""; // punctuation before the word
		String afterPunct = ""; // Punctuation after the word

		for (int i = 0; i < s.length(); i++) { // for loop to remove punct before the word
			char c = s.charAt(0);
			if (isPunct(c)) {
				s = s.substring(1);// remove first letter of the string
				beforePunct = beforePunct + c;
			} else {
				break;
			}
		}

		for (int i = s.length() - 1; i >= 0; i--) { // for loop to remove punct after the word
			char c = s.charAt(s.length() - 1);
			if (isPunct(c)) {
				s = s.substring(0, s.length() - 1);// remove last letter of the string
				afterPunct = c + afterPunct;
			} else {
				break;
			}
		}

		boolean firstLetterUpperCase = false;
		if (Character.isUpperCase(s.charAt(0))) {
			firstLetterUpperCase = true;
			s = Character.toLowerCase(s.charAt(0)) + s.substring(1);// lowercases first letter
		}

		boolean finished = false;

		char firstChar = s.charAt(0);
		if (isVowel(firstChar)) { // if the first letter is a vowel
			ending = ending + "way";
			finished = true;
		}

		if (isY(firstChar)) { // if the first letter is a 'y'
			s = s.substring(1);// remove first letter of the string
			ending = ending + 'y';
		}

		while (finished == false) { // loop for moving the consonants to end
			if (s.length() == 0) { // if the remaining string contains no letters
				return "****INVALID****";
			}

			char c = s.charAt(0);
			if (s.length() >= 2 && s.substring(0, 2).equals("qu")) { // if the remaining string is 2 or more letters and
																		// the first two letters are "qu"
				s = s.substring(2);// remove first 2 letters of the string
				ending = ending + "quay";
				finished = true;
				break;
			}

			if (!isVowel(c) && !isY(c)) { // if the letter is not a vowel or a y
				s = s.substring(1);// remove first letter of the string
				ending = ending + c; // add letter to ending

			} else { // the letter is a vowel
				ending = ending + "ay";
				finished = true;
				break;
			}
		}
		s = s + ending;
		if (firstLetterUpperCase) {
			s = Character.toUpperCase(s.charAt(0)) + s.substring(1);// Capitalizes first letter
		}
		s = beforePunct + s + afterPunct;
		return s;

	}

	public static boolean isVowel(char ch) {
		ch = Character.toLowerCase(ch);
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
	}

	public static boolean isY(char ch) {
		ch = Character.toLowerCase(ch);
		return ch == 'y';
	}

	public static boolean isPunct(char ch) {
		return ch == '.' || ch == ',' || ch == '!' || ch == '?' || ch == '"' || ch == '(' || ch == ')' || ch == ';'
				|| ch == ':';
	}

	public static void part_2_using_piglatenizeFile() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input Filename (Including .txt)? Example: PigLatin.txt:");
		String filename = sc.next();
		Scanner infile = null;
		try {
			infile = new Scanner(new File(filename)); // PigLatin.txt
		} catch (IOException e) {
			System.out.println("oops");
		}
		System.out.print("Output Filename (Including .txt)? Example: PigLatinOut.txt:");
		String filenameOut = sc.next();
		piglatenizeFile(infile, filenameOut);
		System.out.println("Piglatin done!");
		sc.close();
	}

	/******************************
	 * precondition: filename has .txt postcondition: output a piglatinized .txt
	 * file .
	 ******************************/
	public static void piglatenizeFile(Scanner infile, String filename) {
		/***************************
		 *
		 * enter your code here: Scanner, try-catch, nested loops, file output
		 *
		 ****************************/
	}
}
