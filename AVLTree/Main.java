import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> words = new ArrayList<>();
		
		words.add(12);
		words.add(8);
		words.add(5);
		words.add(4);
		words.add(2);
		words.add(7);
		words.add(11);
		words.add(18);
		words.add(17);
		
		AVLTree<Integer, Integer> map = new AVLTree<>();
		for(Integer word: words) {
			System.out.println(word);
			map.add(word, 1);
		}
		
		System.out.println("is BST: " + map.isBST());
		System.out.println("is Balanced: " + map.isBalanced());
	}

}
