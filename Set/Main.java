import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> words = new ArrayList<>();
		words.add("I");
		words.add("I");
		words.add("Love");
		words.add("Love");
		words.add("Love");
		words.add("U");
		System.out.println(words);
		
		LinkedListSet<String> set1 = new LinkedListSet<>();
		for(String word: words) {
			set1.add(word);
		}
		System.out.println(set1.getSize());
		
		set1.remove("Love");
		System.out.println(set1.getSize());
	}
}
