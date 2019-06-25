package nextleapyr;

import java.util.Scanner;

public class nextleapyr {
	public static boolean is_leapyear(int yr) {
		if(yr % 4 == 0) {
			if (yr % 100 == 0) {
				if (yr % 400 == 0) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.print("Enter the current year : ");
		int year = key.nextInt();
		int next = year + 4 - (year % 4);
		if (is_leapyear(next)) {
			System.out.println("The next leap year is " + next);
		}
		else {
			System.out.println("The next leap year is " + next);
		}
	}

}
