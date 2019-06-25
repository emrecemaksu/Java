package caesar;

public class caesar {
	public static int z;
	public static String cipheralphabet = "defghijklmnopqrstuvwxyzabc";
	public static String plainalphabet  = "abcdefghijklmnopqrstuvwxyz";
	public static void encrypt(String str) {
		String s = "";
		for(int i=0; i<str.length(); i++) {
			char x = str.charAt(i);
			z = plainalphabet.indexOf(x);
			if (z != -1) {
				s += cipheralphabet.charAt(z);
			}
		}
		System.out.println(s);
	}
public static void dencrypt(String str) {
	String s = "";
	for(int i=0; i<str.length(); i++) {
		char x = str.charAt(i);
		int z = cipheralphabet.indexOf(x);
		if (z != -1) {
			s += plainalphabet.charAt(z);
		}
	}
	System.out.println(s);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		caesar.encrypt("edcba");
		caesar.dencrypt("hgfed");
	}

}
