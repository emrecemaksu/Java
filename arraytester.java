import java.util.Scanner;

public class ArrayTester {
	public static void areFactors(int n) {
		int[] array1 = {5, 10, 20, 50};
		for(int i=0; i<array1.length; i++) {
			if(n/array1[i] %= 0) {
				
			}
		}
	}
	public static int arraySearch(int target) {
		return target;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.println("Set n : ");
		int n = key.nextInt();
		System.out.println("Set target : ");
		int target = key.nextInt();
		System.out.println("Testing areFactors() with n = " + n + "and an array of 5, 10, 20, 50:");
		System.out.println("Testing areFactors() with n = " + n + "and an array of 5, 10, 20, 50:");
		System.out.println("Testing arraySeach() with target = " + target + "and an array of 5, 10, 20, 50:");
		System.out.println("Testing arraySeach() with target = " + target + "and an array of 5, 10, 20, 50:");
	}
}
