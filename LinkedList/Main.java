
public class Main {
	
	public static void main(String[] args) {
		LinkedListUsingDummyHead<Integer> linkedlist = new LinkedListUsingDummyHead<>();
		for(int i = 0; i < 5; i++) {
			linkedlist.addFirst(i);
			System.out.println(linkedlist);
		}
		
		linkedlist.add(2, 666);
		System.out.println(linkedlist);
		
		linkedlist.remove(2);
		System.out.println(linkedlist);
		
		linkedlist.removeFirst();
		System.out.println(linkedlist);
		
		linkedlist.removeLast();
		System.out.println(linkedlist);
	}
	
}
