package countchars;

public class countchars {
	public static int count_chars_i(String str, char chr) {
		int count = 0; 
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)==chr) {
				count += 1;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("There are " + count_chars_i("hello", 'h') + 
				" h\'s in hello");
		System.out.println("There are " + count_chars_i("hello", 'l') + 
				" l\'s in hello");
		System.out.println("There are " + count_chars_i("hello", 'b') + 
				" b\'s in hello");
	}

}
