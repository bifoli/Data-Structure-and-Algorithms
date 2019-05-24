
public class Main {
	
	public static void main(String[] args) {
		//demo1();
		//测试删除任意数
		ImprovedBST<Integer> bst = new ImprovedBST<>();
		int[] nums = {41, 22, 58, 15, 33, 50, 60, 42, 53, 59, 63};
		
		for(int num: nums) {
			bst.add(num);
		}
		bst.levelOrder();
		System.out.println("\n");
		bst.remove(58);
		bst.levelOrder();
	}

	/**
	 * 
	 */
	private static void demo1() {
		ImprovedBST<Integer> bst = new ImprovedBST<>();
		int[] nums = {5, 3, 6, 7, 8, 4, 2};
		
		for(int num: nums) {
			bst.add(num);
		}
		
		//////////////////////////
		//           5          //
		//        /    \        //
		//       3      6       //
		//      /  \   /  \     //
		//     2   4   7   8    //
		//////////////////////////
		
		System.out.println("\n");
		bst.removeMin();
		bst.levelOrder();
		System.out.println("\n");
		bst.removeMin();
		bst.levelOrder();
		System.out.println("\n");
		bst.removeMax();
		bst.levelOrder();
		
//		bst.preOrder();
//		System.out.println("\n");
//		
//		bst.preOrderNR();
//		System.out.println("\n");
//		
//		bst.inOrder();
//		System.out.println("\n");
//		bst.postOrder();
//		System.out.println("\n");
//		
//		System.out.println(bst);
	}
}
