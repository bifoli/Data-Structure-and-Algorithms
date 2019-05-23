
public class Main {

	public static void main(String[] args) {
		
		//Demo1();
		
		//LinkedListStack测试
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		
		for(int i = 0; i < 5; i++) {
			stack.push(i);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
	}

	/**
	 * ArrayStack测试
	 */
	private static void Demo1() {
		ArrayStack<Integer> stack = new ArrayStack<>();
		
		for(int i = 0; i < 5; i++) {
			stack.push(i);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
	}

}
