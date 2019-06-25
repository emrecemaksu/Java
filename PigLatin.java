import java.util.Scanner;
//File: piglatin.java
//Author: Gokce Gokmenoglu
//Description:  Converts words and sentences entered on the command line to
//Pig Latin

public class PigLatin {
	public static int vowel_count;
	public static String sx = "";
	public static String xy = "";
	public static char[] c_arr;
	public static boolean is_not_vowel(char s){
		if(s!='a' || s!='e' || s!='i' || s!='o' || s!='u'){
			return true;
			}
		else {
			return false;
			}
		}
	// Returns the index of the first vowel
	public static int index_first_vowel(String s){
		c_arr = s.toCharArray();
		for(int i=0; i<c_arr.length; i++){
			if(is_vowel(c_arr[i])){
				vowel_count = i;
				ex(vowel_count);
				return vowel_count;
				}
			else if(is_not_vowel(c_arr[i])){
				xy += c_arr[i];
				}
			}
		return vowel_count;
		}
	// Returns the index of the first vowel
	public static void ex(int vc){
		for(int j=vc; j<c_arr.length; j++){
			sx = sx + c_arr[j];
			}
		}
	// Converts the word, s, into Pig Latin
	public static void pig_latin(String s){
	 index_first_vowel(s);
	 int first_vowel = vowel_count;
	 if(first_vowel == -1){
		 System.out.println(s + "yay");
		 }
	 else if (first_vowel == 0){
		 System.out.println(sx + xy + "yay");
		 }
	 else {
		 System.out.println(sx + xy + "ay");
		 }
	 }
	// Returns True if s is a vowel
	public static boolean is_vowel(char s){
		if(s=='a' || s=='e' || s=='i' || s=='o' || s=='u'){
			return true;
			}
		else {
			return false;
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner write = new Scanner(System.in);
		 System.out.print("Write a Text : ");
		 String a = write.nextLine();
		 /*
		 for(int i=0; i<argvs.length; i++){
				System.out.println(args[i]);
				}
				*/
		 pig_latin(a);
	}
}