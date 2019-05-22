
public class Main {

	public static void main(String[] args) {
		//demo1();
		LoopQueue<Integer> queue = new LoopQueue<>();
		for(int i = 0; i < 10; i++) {
			queue.enqueue(i);
			System.out.println(queue);
			
			//每插入三个元素，就取出一个元素
			if(i % 3 == 2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}
		System.out.println(queue.getFront());
	}

	/**
	 * 测试ArrayQueue
	 */
	private static void demo1() {
		ArrayQueue<Integer> queue = new ArrayQueue<>();
		for(int i = 0; i < 10; i++) {
			queue.enqueue(i);
			System.out.println(queue);
			
			//每插入三个元素，就取出一个元素
			if(i % 3 == 2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}
	}

}
