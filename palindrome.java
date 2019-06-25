package palindrome;

import java.util.Scanner;

public class palindrome {
	public static int is_palindrome(String s) {
		int j = 0;
		String ls = s.toLowerCase();
		int tx = ls.length()-1;
		int t = ls.length()-1;
		for(int i=0; i<t; i++) {
			if (ls.charAt(i)==ls.charAt(t)) {
				j += 1;
			}
			t -= 1;
		}
		if(j != 0 && (j == ls.length()/2 ||  j == (tx/2)-1)) {
			System.out.println("The word " + ls + " is a palindrome.");
		}
		else {
			System.out.println("The word " + ls + " is NOT a palindrome.");
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.print("Set a palindrome : ");
		String pal = scn.nextLine();
		is_palindrome(pal);
	}

}
