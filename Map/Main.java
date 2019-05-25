import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		words.add("I");
		words.add("I");
		words.add("I");
		words.add("love");
		words.add("you");
		words.add("love");
		words.add("you");
		words.add("you");
		words.add("I");
		
		BSTMap<String, Integer> map = new BSTMap<String, Integer>();
		for(String word: words) {
			if(map.contains(word)) {
				map.set(word, map.get(word) + 1);
			}else {
				map.add(word, 1);
			}
		}
		map.remove("you");
		System.out.println("Total different words: " + map.getSize());
		System.out.println("Frequency of I: " + map.get("I"));
		System.out.println("Frequency of love: " + map.get("love"));
		System.out.println("Frequency of you: " + map.get("you"));
	}
}
