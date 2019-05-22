
public class Main {

	public static void main(String[] args) {
		//Demo1();
		//demo2();
		//demo3();
		DynamicArray<Integer> arr = new DynamicArray<Integer>();
		for(int i = 0; i < 10; i++) {
			arr.addLast(i);
		}
		System.out.println(arr);
		
		//扩容操作
		arr.add(1, 100);
		System.out.println(arr);
		
		arr.addFirst(-1);
		System.out.println(arr);
		
		//缩容操作
		arr.remove(2);
		arr.removeElement(4);
		arr.removeFirst();
		System.out.println(arr);
	}

	/**
	 * 测试泛型
	 */
	private static void demo3() {
		ArrayUsingGeneric<Integer> arr = new ArrayUsingGeneric<>(20);
		for(int i = 0; i < 10; i++) {
			arr.addLast(i);
		}
		System.out.println(arr);
		
		arr.add(1, 100);
		System.out.println(arr);
		
		arr.addFirst(-1);
		System.out.println(arr);
		
		arr.set(0, -2);
		System.out.println(arr);
		
		arr.remove(2);
		System.out.println(arr);
		
		arr.removeElement(-2);
		System.out.println(arr);
		
		arr.removeFirst();
		System.out.println(arr);
	}

	/**
	 * 用于Array的测试
	 */
	private static void demo2() {
		Array arr = new Array(20);
		for(int i = 0; i < 10; i++) {
			arr.addLast(i);
		}
		System.out.println(arr);
		
		arr.add(1, 100);
		System.out.println(arr);
		
		arr.addFirst(-1);
		System.out.println(arr);
		
		arr.set(0, -2);
		System.out.println(arr);
		
		arr.remove(2);
		System.out.println(arr);
		
		arr.removeElement(-2);
		System.out.println(arr);
		
		arr.removeFirst();
		System.out.println(arr);
	}

	/**
	 * 基础教学
	 */
	private static void Demo1() {
		//第一种创建数组的方法
		int[] arr = new int[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		
		//第二种创建数组的方法
		int[] scores = new int[]{100, 99, 66};
		for(int i = 0; i < scores.length; i++) {
			System.out.println(i);
		}
		
		//第二种遍历方法：迭代器foreach
		for(int score: scores) {
			System.out.println(score);
		}
		
		//数组也可以修改元素
		scores[0] = 88;
		for(int i = 0; i < scores.length; i++) {
			System.out.println(scores[i]);
		}
	}

}
