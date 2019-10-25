import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BreakVignere {
	private static char[] cipherarr;
	private static ArrayList<Integer> shift = new ArrayList<Integer>();
	private static int keylength = 0;
	
	public static void main(String[] args) {
		String cipherstr;
		ArrayList<Character> column;
		
		// get cipher text from user
		File ciphertext = new File(args[0]);
		
		try {
			Scanner scnr = new Scanner(ciphertext);
			cipherstr = scnr.next();
			cipherarr = cipherstr.toCharArray();
			
			keylength = maxIC();
			
			keylength = 9;
			System.out.println("key length:" + keylength);
			
		} catch(FileNotFoundException e) {
			System.out.println("error");
		}
		
		column = new ArrayList<Character>();
		for (int i = 0; i < keylength; i++) {
			column.clear();
			
			getColumn(keylength, i, column);
			
			// break shift
			shift.add(BreakShift.breakShift(column));
			
		}
		
		decode();
	}
	
	private static int maxIC() {
		final int MAX_LENGTH = 100;
		ArrayList<Character> column = new ArrayList<Character>();
		int[] numLetters = new int[26];
		double maxIC = 0;
		int keylength = 0;
		
		column = new ArrayList<Character>();
		
		for (int i = 1; i < MAX_LENGTH; i++) {
			column.clear();
			
			getColumn(i, 0, column);
			
			numLetters = countLetters(column);

			int numerator = 0;
			int denominator = 0;
			double tempIC;
			
			for (int j = 0; j < numLetters.length; j++) {
				if (numLetters[j] > 0) {
					numerator += numLetters[j] * (numLetters[j] - 1);
				}
				denominator += numLetters[j];
			}
			denominator = denominator * (denominator - 1);
			tempIC = numerator / (double)denominator;
			
			if (tempIC > maxIC && tempIC > 0.066) {
				maxIC = tempIC;
				keylength = i;
			}
			else if (tempIC < maxIC)
				break;
		}
		
		return keylength;
	}
	
	private static int[] countLetters(ArrayList<Character> arr) {
		int[] numLetters = new int[26];
		
		for (int i = 0; i < arr.size(); i++) {
			numLetters[(int)arr.get(i) - 65]++;
		}
		return numLetters;
	}
	
	private static void getColumn(int length, int col, ArrayList<Character> list) {
		for (int j = 0; j < cipherarr.length; j++) {
			if ((j % length) != col)
				continue;
			list.add(cipherarr[j]);
		}
	}
	
	private static void decode() {
		char[] plaintext = new char[cipherarr.length];

		int q = 0;
		int text;
		for (int i = 0; i < plaintext.length; i++) {
			text = (int)(cipherarr[i] - shift.get(q % keylength));
			if (text > 90) {
				text = text - 91 + 65;
			}
			if (text < 65) {
				text = 91 - (65 - text);
			}
			plaintext[i] = (char)text;
			q++;
		}
		
		for (int i = 0; i < plaintext.length; i++) {
			System.out.print(plaintext[i]);
		}
	}
}
